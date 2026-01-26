package com.hayden.multiagentidelib.prompt;

import java.util.ArrayList;
import java.util.List;

/**
 * Assembles prompts from a base prompt and contributions from registered contributors.
 * 
 * Supports listeners for observability and artifact emission.
 */
public class PromptAssembly {

    private final PromptContributorRegistry registry;
    private final List<PromptContributionListener> listeners;

    public PromptAssembly(PromptContributorRegistry registry) {
        this(registry, List.of());
    }
    
    public PromptAssembly(PromptContributorRegistry registry, List<PromptContributionListener> listeners) {
        this.registry = registry;
        this.listeners = listeners != null ? new ArrayList<>(listeners) : new ArrayList<>();
    }
    
    /**
     * Adds a listener for prompt contribution events.
     */
    public void addListener(PromptContributionListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }
    
    /**
     * Removes a listener.
     */
    public void removeListener(PromptContributionListener listener) {
        listeners.remove(listener);
    }

    public String assemble(String basePrompt, PromptContext context) {
        String prompt = basePrompt == null ? "" : basePrompt;
        if (registry == null) {
            notifyAssemblyComplete(prompt, prompt, 0, context);
            return prompt;
        }
        List<PromptContributor> contributors = getContributors(context);
        if (contributors.isEmpty()) {
            notifyAssemblyComplete(prompt, prompt, 0, context);
            return prompt;
        }
        StringBuilder assembled = new StringBuilder(prompt);
        assembled.append("\n\n--- Tool Prompt Contributions ---\n");
        int orderIndex = 0;
        for (PromptContributor contributor : contributors) {
            if (contributor == null) {
                continue;
            }
            String contribution = null;
            try {
                contribution = contributor.contribute(context);
            } catch (RuntimeException ignored) {
                continue;
            }
            if (contribution == null || contribution.isBlank()) {
                continue;
            }
            
            // Notify listeners of this contribution
            notifyContribution(
                    contributor.name(),
                    contributor.priority(),
                    contributor.applicableAgents(),
                    contribution.trim(),
                    orderIndex,
                    context
            );
            orderIndex++;
            
            assembled.append("\n[")
                    .append(contributor.name())
                    .append("]\n")
                    .append(contribution.trim())
                    .append("\n");
        }
        assembled.append("--- End Tool Prompt Contributions ---");
        
        String result = assembled.toString();
        notifyAssemblyComplete(prompt, result, orderIndex, context);
        return result;
    }

    public List<PromptContributor> getContributors(PromptContext context) {
        return registry.getContributors(context);
    }
    
    /**
     * Assemble just the prompt contributions without the base prompt.
     * This is useful for injecting contributions into Jinja templates.
     * 
     * @param context The prompt context
     * @return The assembled contributions as a string, or empty string if no contributions
     */
    public String assembleContributions(PromptContext context) {
        if (registry == null) {
            return "";
        }
        List<PromptContributor> contributors = getContributors(context);
        if (contributors.isEmpty()) {
            return "";
        }
        StringBuilder assembled = new StringBuilder();
        assembled.append("--- Tool Prompt Contributions ---\n");
        int orderIndex = 0;
        for (PromptContributor contributor : contributors) {
            if (contributor == null) {
                continue;
            }
            String contribution = null;
            try {
                contribution = contributor.contribute(context);
            } catch (RuntimeException ignored) {
                continue;
            }
            if (contribution == null || contribution.isBlank()) {
                continue;
            }
            
            // Notify listeners of this contribution
            notifyContribution(
                    contributor.name(),
                    contributor.priority(),
                    contributor.applicableAgents(),
                    contribution.trim(),
                    orderIndex,
                    context
            );
            orderIndex++;
            
            assembled.append("\n[")
                    .append(contributor.name())
                    .append("]\n")
                    .append(contribution.trim())
                    .append("\n");
        }
        assembled.append("--- End Tool Prompt Contributions ---");
        
        String result = assembled.toString();
        notifyAssemblyComplete(null, result, orderIndex, context);
        return result;
    }
    
    private void notifyContribution(
            String contributorName,
            int priority,
            java.util.Set<com.hayden.multiagentidelib.agent.AgentType> applicableAgents,
            String contributedText,
            int orderIndex,
            PromptContext context
    ) {
        for (PromptContributionListener listener : listeners) {
            try {
                listener.onContribution(
                        contributorName,
                        priority,
                        applicableAgents,
                        contributedText,
                        orderIndex,
                        context
                );
            } catch (Exception e) {
                // Don't let listener exceptions break assembly
            }
        }
    }
    
    private void notifyAssemblyComplete(
            String basePrompt,
            String assembledPrompt,
            int contributionCount,
            PromptContext context
    ) {
        for (PromptContributionListener listener : listeners) {
            try {
                listener.onAssemblyComplete(basePrompt, assembledPrompt, contributionCount, context);
            } catch (Exception e) {
                // Don't let listener exceptions break assembly
            }
        }
    }
}
