package com.hayden.multiagentidelib.agent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Blackboard history management for tracking action inputs and preventing
 * unwanted state accumulation through clearing.
 */
public class BlackboardHistory {

    /**
     * Functional interface for providing contextual prompts based on history.
     * Implementations can augment base prompts with retry/loop information.
     */
    @FunctionalInterface
    public interface PromptProvider {
        String providePrompt(String basePrompt);
        
        static PromptProvider identity() {
            return basePrompt -> basePrompt;
        }
        
        default PromptProvider andThen(PromptProvider after) {
            return basePrompt -> after.providePrompt(this.providePrompt(basePrompt));
        }
    }

    /**
     * Tracks a single historical blackboard state entry.
     * Each entry represents a previous action's input that has been archived.
     */
    public record Entry(
            Instant timestamp,
            String actionName,
            Object input,
            Class<?> inputType
    ) {
    }

    /**
     * The main history container.
     * Instead of clearing the blackboard, inputs are transferred here.
     */
    public record History(
            List<Entry> entries
    ) {
        public History() {
            this(new ArrayList<>());
        }

        /**
         * Add an entry to the history
         */
        public History withEntry(String actionName, Object input) {
            List<Entry> newEntries = new ArrayList<>(entries);
            newEntries.add(new Entry(
                    Instant.now(),
                    actionName,
                    input,
                    input != null ? input.getClass() : null
            ));
            return new History(newEntries);
        }

        /**
         * Check if we've seen this input type before (indicating a retry/loop)
         */
        public boolean hasSeenType(Class<?> type) {
            return entries.stream()
                    .anyMatch(entry -> entry.inputType() != null && entry.inputType().equals(type));
        }

        /**
         * Count how many times we've seen this input type
         */
        public long countType(Class<?> type) {
            return entries.stream()
                    .filter(entry -> entry.inputType() != null && entry.inputType().equals(type))
                    .count();
        }

        /**
         * Get all entries of a specific type
         */
        @SuppressWarnings("unchecked")
        public <T> List<T> getEntriesOfType(Class<T> type) {
            return entries.stream()
                    .filter(entry -> entry.inputType() != null && entry.inputType().equals(type))
                    .map(entry -> (T) entry.input())
                    .collect(Collectors.toList());
        }

        /**
         * Get the most recent entry of a specific type
         */
        @SuppressWarnings("unchecked")
        public <T> Optional<T> getLastOfType(Class<T> type) {
            return entries.stream()
                    .filter(entry -> entry.inputType() != null && entry.inputType().equals(type))
                    .map(entry -> (T) entry.input())
                    .reduce((first, second) -> second);
        }

        /**
         * Check if this is a retry of a specific action
         */
        public boolean isRetry(String actionName) {
            return entries.stream()
                    .anyMatch(entry -> entry.actionName().equals(actionName));
        }

        /**
         * Get retry count for a specific action
         */
        public long getRetryCount(String actionName) {
            return entries.stream()
                    .filter(entry -> entry.actionName().equals(actionName))
                    .count();
        }

        /**
         * Generate a PromptProvider for a specific input type.
         * This uses type-specific logic to augment prompts with historical context.
         */
        public <T> PromptProvider generatePromptProvider(Class<T> type) {
            List<T> previousInputs = getEntriesOfType(type);
            
            if (previousInputs.isEmpty()) {
                return PromptProvider.identity();
            }

            return basePrompt -> {
                StringBuilder augmented = new StringBuilder(basePrompt);
                augmented.append("\n\n--- Historical Context ---\n");
                
                // Type-specific prompt augmentation
                if (type.equals(AgentModels.OrchestratorRequest.class)) {
                    augmented.append(buildOrchestratorContext(previousInputs));
                } else if (type.equals(AgentModels.OrchestratorCollectorRequest.class)) {
                    augmented.append(buildOrchestratorCollectorContext(previousInputs));
                } else if (type.equals(AgentModels.DiscoveryOrchestratorRequest.class)) {
                    augmented.append(buildDiscoveryOrchestratorContext(previousInputs));
                } else if (type.equals(AgentModels.DiscoveryCollectorRequest.class)) {
                    augmented.append(buildDiscoveryCollectorContext(previousInputs));
                } else if (type.equals(AgentModels.PlanningOrchestratorRequest.class)) {
                    augmented.append(buildPlanningOrchestratorContext(previousInputs));
                } else if (type.equals(AgentModels.PlanningCollectorRequest.class)) {
                    augmented.append(buildPlanningCollectorContext(previousInputs));
                } else if (type.equals(AgentModels.TicketOrchestratorRequest.class)) {
                    augmented.append(buildTicketOrchestratorContext(previousInputs));
                } else if (type.equals(AgentModels.TicketCollectorRequest.class)) {
                    augmented.append(buildTicketCollectorContext(previousInputs));
                } else if (type.equals(AgentModels.ReviewRequest.class)) {
                    augmented.append(buildReviewContext(previousInputs));
                } else if (type.equals(AgentModels.MergerRequest.class)) {
                    augmented.append(buildMergerContext(previousInputs));
                } else {
                    // Generic context for unknown types
                    augmented.append(buildGenericContext(previousInputs));
                }
                
                augmented.append("--- End Historical Context ---\n\n");
                
                return augmented.toString();
            };
        }

        private <T> String buildOrchestratorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("This workflow orchestration has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.OrchestratorRequest req = (AgentModels.OrchestratorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s', Phase='%s'\n", i + 1, req.goal(), req.phase()));
            }
            sb.append("Please consider why previous attempts did not complete successfully.\n");
            return sb.toString();
        }

        private <T> String buildOrchestratorCollectorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Workflow consolidation has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.OrchestratorCollectorRequest req = (AgentModels.OrchestratorCollectorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s', Phase='%s'\n", i + 1, req.goal(), req.phase()));
            }
            sb.append("Review the previous routing decisions and ensure progress is being made.\n");
            return sb.toString();
        }

        private <T> String buildDiscoveryOrchestratorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Discovery orchestration has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.DiscoveryOrchestratorRequest req = (AgentModels.DiscoveryOrchestratorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s'\n", i + 1, req.goal()));
            }
            sb.append("Consider whether the discovery scope needs adjustment or if different subdomains should be explored.\n");
            return sb.toString();
        }

        private <T> String buildDiscoveryCollectorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Discovery consolidation has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.DiscoveryCollectorRequest req = (AgentModels.DiscoveryCollectorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s', Discovery Results Length=%d chars\n", 
                    i + 1, req.goal(), req.discoveryResults() != null ? req.discoveryResults().length() : 0));
            }
            sb.append("Ensure the consolidated discovery output is comprehensive and addresses all aspects of the goal.\n");
            return sb.toString();
        }

        private <T> String buildPlanningOrchestratorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Planning orchestration has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.PlanningOrchestratorRequest req = (AgentModels.PlanningOrchestratorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s'\n", i + 1, req.goal()));
            }
            sb.append("Review if the planning approach needs to be broken down differently.\n");
            return sb.toString();
        }

        private <T> String buildPlanningCollectorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Planning consolidation has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.PlanningCollectorRequest req = (AgentModels.PlanningCollectorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s', Planning Results Length=%d chars\n",
                    i + 1, req.goal(), req.planningResults() != null ? req.planningResults().length() : 0));
            }
            sb.append("Ensure tickets are well-defined, have clear dependencies, and cover all necessary work.\n");
            return sb.toString();
        }

        private <T> String buildTicketOrchestratorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ticket orchestration has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.TicketOrchestratorRequest req = (AgentModels.TicketOrchestratorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s', Tickets Length=%d chars\n",
                    i + 1, req.goal(), req.tickets() != null ? req.tickets().length() : 0));
            }
            sb.append("Consider if ticket execution order or dependencies need adjustment.\n");
            return sb.toString();
        }

        private <T> String buildTicketCollectorContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ticket consolidation has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.TicketCollectorRequest req = (AgentModels.TicketCollectorRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Goal='%s', Ticket Results Length=%d chars\n",
                    i + 1, req.goal(), req.ticketResults() != null ? req.ticketResults().length() : 0));
            }
            sb.append("Review ticket execution results and determine if any tickets need to be retried or if the workflow can proceed.\n");
            return sb.toString();
        }

        private <T> String buildReviewContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Review has been requested ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.ReviewRequest req = (AgentModels.ReviewRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Criteria='%s', Content Length=%d chars\n",
                    i + 1, req.criteria(), req.content() != null ? req.content().length() : 0));
            }
            sb.append("Consider if the content has improved since the last review attempt.\n");
            return sb.toString();
        }

        private <T> String buildMergerContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("Merge has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                AgentModels.MergerRequest req = (AgentModels.MergerRequest) inputs.get(i);
                sb.append(String.format("Attempt %d: Summary='%s', Conflict Files='%s'\n",
                    i + 1, req.mergeSummary(), req.conflictFiles()));
            }
            sb.append("Ensure merge conflicts are properly resolved before proceeding.\n");
            return sb.toString();
        }

        private <T> String buildGenericContext(List<T> inputs) {
            StringBuilder sb = new StringBuilder();
            sb.append("This action has been attempted ").append(inputs.size()).append(" time(s) before.\n");
            for (int i = 0; i < inputs.size(); i++) {
                sb.append(String.format("Attempt %d: %s\n", i + 1, inputs.get(i).toString()));
            }
            return sb.toString();
        }

        /**
         * Check if we're in a loop by detecting repeated patterns
         */
        public boolean detectLoop(Class<?> type, int threshold) {
            return countType(type) >= threshold;
        }

        /**
         * Get a summary of all historical entries
         */
        public String getSummary() {
            if (entries.isEmpty()) {
                return "No historical entries";
            }

            Map<String, Long> actionCounts = entries.stream()
                    .collect(Collectors.groupingBy(
                            Entry::actionName,
                            Collectors.counting()
                    ));

            StringBuilder summary = new StringBuilder("History Summary:\n");
            actionCounts.forEach((action, count) ->
                    summary.append(String.format("  - %s: %d attempts\n", action, count))
            );

            return summary.toString();
        }
    }
}
