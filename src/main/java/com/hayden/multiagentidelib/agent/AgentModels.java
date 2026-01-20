package com.hayden.multiagentidelib.agent;

import com.embabel.agent.api.common.SomeOf;
import com.hayden.multiagentidelib.template.ConsolidationTemplate;
import com.hayden.multiagentidelib.template.DelegationTemplate;
import com.hayden.multiagentidelib.template.DiscoveryReport;
import com.hayden.multiagentidelib.template.MemoryReference;
import com.hayden.multiagentidelib.template.PlanningTicket;
import com.hayden.utilitymodule.acp.events.Events;
import lombok.Builder;

import java.util.List;
import java.util.Map;

public interface AgentModels {

    enum InteractionType {
        AGENT_MESSAGE,
        USER_MESSAGE,
        INTERRUPT_REQUEST,
        RESULT_HANDOFF
    }

    interface InterruptDescriptor {
        Events.InterruptType type();
        String reason();
    }

    record InterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record OrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record OrchestratorCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record DiscoveryOrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record DiscoveryAgentInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record DiscoveryCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record DiscoveryAgentDispatchInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record PlanningOrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record PlanningAgentInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record PlanningCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record PlanningAgentDispatchInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record TicketOrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record TicketAgentInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record TicketCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record TicketAgentDispatchInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record ReviewInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record MergerInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextOrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextAgentInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextAgentDispatchInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder(toBuilder=true)
    record CollectorDecision(
            Events.CollectorDecisionType decisionType,
            String rationale,
            String requestedPhase
    ) {
    }

    /**
     * Shared data models describing agent interactions, results, and interrupts.
     * These are intentionally transport-agnostic and can be serialized as needed.
     */
    @Builder(toBuilder=true)
    record AgentInteraction(
            InteractionType interactionType,
            String message
    ) {
    }

    /**
     * Defines how to kick off sub-agents for orchestrated work.
     */
    @Builder(toBuilder=true)
    record DelegationPlan(
            String summary,
            Map<String, String> subAgentGoals
    ) {
    }

    /**
     * Orchestrator-specific results.
     * Note: Results do NOT implement DelegationTemplate - delegation templates are the
     * *AgentRequests types that contain multiple sub-agent requests.
     */
    @Builder(toBuilder=true)
    record OrchestratorAgentResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String output
    ) {
        public OrchestratorAgentResult(String output) {
            this(null, null, null, output);
        }
    }

    @Builder(toBuilder=true)
    record DiscoveryOrchestratorResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String output
    ) {
        public DiscoveryOrchestratorResult(String output) {
            this(null, null, null, output);
        }
    }

    @Builder(toBuilder=true)
    record PlanningOrchestratorResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String output
    ) {
        public PlanningOrchestratorResult(String output) {
            this(null, null, null, output);
        }
    }

    @Builder(toBuilder=true)
    record TicketOrchestratorResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String output
    ) {
        public TicketOrchestratorResult(String output) {
            this(null, null, null, output);
        }
    }

    /**
     * Results for non-orchestrator agents.
     */
//    TODO: this should be a code-map-like report result -
//        something cool about this
    @Builder(toBuilder=true)
    record DiscoveryAgentResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            DiscoveryReport report,
            String output
    ) {
        public DiscoveryAgentResult(String output) {
            this(null, null, null, null, output);
        }
    }

    @Builder(toBuilder=true)
    record PlanningAgentResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            ContextId discoveryResultId,
            List<PlanningTicket> tickets,
            String output
    ) {
        public PlanningAgentResult(String output) {
            this(null, null, null, null, null, output);
        }
    }

    @Builder(toBuilder=true)
    record TicketAgentResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String ticketId,
            ContextId discoveryResultId,
            String implementationSummary,
            List<String> filesModified,
            List<String> testResults,
            List<String> commits,
            String verificationStatus,
            List<ContextId> upstreamContextChain,
            List<MemoryReference> memoryReferences,
            String output
    ) {
        public TicketAgentResult(String output) {
            this(null, null, null, null, null, null, null, null, null, null, null, null, output);
        }
    }

    interface ReviewEvaluation {
        String schemaVersion();

        ContextId resultId();

        ContextId upstreamContextId();

        String assessmentStatus();

        String feedback();

        List<String> suggestions();

        List<String> contentLinks();
    }

    @Builder(toBuilder=true)
    record ReviewAgentResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String assessmentStatus,
            String feedback,
            List<String> suggestions,
            List<String> contentLinks,
            String output
    ) implements ReviewEvaluation {
        public ReviewAgentResult(String output) {
            this(null, null, null, null, output, null, null, output);
        }
    }

    interface MergerValidation {
        String schemaVersion();

        ContextId resultId();

        ContextId upstreamContextId();

        String acceptability();

        List<String> conflictDetails();

        List<String> resolutionGuidance();
    }

    @Builder(toBuilder=true)
    record MergerAgentResult(
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String acceptability,
            List<String> conflictDetails,
            List<String> resolutionGuidance,
            String output
    ) implements MergerValidation {
        public MergerAgentResult(String output) {
            this(null, null, null, null, null, null, output);
        }
    }

    @Builder(toBuilder=true)
    record SummaryAgentResult(
            String output
    ) {
    }

    @Builder(toBuilder=true)
    record ContextAgentResult(
            String output
    ) {
    }

    /**
     * Results for collector agents.
     */
//    TODO: this should be a code-map-like report returned
    @Builder(toBuilder=true)
    record DiscoveryCollectorResult(
            String schemaVersion,
            ContextId resultId,
            List<ConsolidationTemplate.InputReference> inputs,
            String mergeStrategy,
            List<ConsolidationTemplate.ConflictResolution> conflictResolutions,
            Map<String, Double> aggregatedMetrics,
            String consolidatedOutput,
            CollectorDecision collectorDecision,
            List<ContextId> upstreamContextChain,
            Map<String, String> metadata,
            CodeMap unifiedCodeMap,
            List<Recommendation> recommendations,
            Map<String, QueryFindings> querySpecificFindings,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration
    ) implements ConsolidationTemplate {
        public DiscoveryCollectorResult(String consolidatedOutput, CollectorDecision collectorDecision) {
            this(null, null, List.of(), null, List.of(), Map.of(), consolidatedOutput, collectorDecision, List.of(), Map.of(), null, List.of(), Map.of(), null);
        }

        @Override
        public List<Curation> curations() {
            return discoveryCuration == null ? List.of() : List.of(discoveryCuration);
        }

        @Override
        public CollectorDecision decision() {
            return collectorDecision;
        }
    }

    @Builder(toBuilder=true)
    record PlanningCollectorResult(
            String schemaVersion,
            ContextId resultId,
            List<ConsolidationTemplate.InputReference> inputs,
            String mergeStrategy,
            List<ConsolidationTemplate.ConflictResolution> conflictResolutions,
            Map<String, Double> aggregatedMetrics,
            String consolidatedOutput,
            CollectorDecision collectorDecision,
            List<ContextId> upstreamContextChain,
            Map<String, String> metadata,
            List<PlanningTicket> finalizedTickets,
            List<TicketDependency> dependencyGraph,
            ContextId discoveryResultId,
            UpstreamContext.PlanningCollectorContext planningCuration
    ) implements ConsolidationTemplate {
        public PlanningCollectorResult(String consolidatedOutput, CollectorDecision collectorDecision) {
            this(null, null, List.of(), null, List.of(), Map.of(), consolidatedOutput, collectorDecision, List.of(), Map.of(), List.of(), List.of(), null, null);
        }

        @Override
        public List<Curation> curations() {
            return planningCuration == null ? List.of() : List.of(planningCuration);
        }

        @Override
        public CollectorDecision decision() {
            return collectorDecision;
        }
    }

    @Builder(toBuilder=true)
    record ContextCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    @Builder(toBuilder=true)
    record OrchestratorCollectorResult(
            String schemaVersion,
            ContextId resultId,
            List<ConsolidationTemplate.InputReference> inputs,
            String mergeStrategy,
            List<ConsolidationTemplate.ConflictResolution> conflictResolutions,
            Map<String, Double> aggregatedMetrics,
            String consolidatedOutput,
            CollectorDecision collectorDecision,
            List<ContextId> upstreamContextChain,
            Map<String, String> metadata,
            DiscoveryCollectorResult discoveryCollectorResult,
            PlanningCollectorResult planningCollectorResult,
            TicketCollectorResult ticketCollectorResult
    ) implements ConsolidationTemplate {
        public OrchestratorCollectorResult(String consolidatedOutput, CollectorDecision collectorDecision) {
            this(null, null, List.of(), null, List.of(), Map.of(), consolidatedOutput, collectorDecision, List.of(), Map.of(), null, null, null);
        }

        @Override
        public List<Curation> curations() {
            return List.of();
        }

        @Override
        public CollectorDecision decision() {
            return collectorDecision;
        }
    }

    @Builder(toBuilder=true)
    record TicketCollectorResult(
            String schemaVersion,
            ContextId resultId,
            List<ConsolidationTemplate.InputReference> inputs,
            String mergeStrategy,
            List<ConsolidationTemplate.ConflictResolution> conflictResolutions,
            Map<String, Double> aggregatedMetrics,
            String consolidatedOutput,
            CollectorDecision collectorDecision,
            List<ContextId> upstreamContextChain,
            Map<String, String> metadata,
            String completionStatus,
            List<String> followUps,
            UpstreamContext.TicketCollectorContext ticketCuration
    ) implements ConsolidationTemplate {
        public TicketCollectorResult(String consolidatedOutput, CollectorDecision collectorDecision) {
            this(null, null, List.of(), null, List.of(), Map.of(), consolidatedOutput, collectorDecision, List.of(), Map.of(), null, List.of(), null);
        }

        @Override
        public List<Curation> curations() {
            return ticketCuration == null ? List.of() : List.of(ticketCuration);
        }

        @Override
        public CollectorDecision decision() {
            return collectorDecision;
        }
    }

    @Builder(toBuilder=true)
    record ModuleOverview(
            String moduleName,
            String summary,
            List<String> relatedFiles,
            List<String> dependencies
    ) {
    }

    @Builder(toBuilder=true)
    record CodeMap(
            List<ModuleOverview> modules,
            List<DiscoveryReport.FileReference> deduplicatedReferences,
            DiscoveryReport.DiagramRepresentation unifiedDiagram,
            List<DiscoveryReport.SemanticTag> mergedTags
    ) {
    }

    enum RecommendationPriority {
        CRITICAL,
        HIGH,
        MEDIUM,
        LOW
    }

    @Builder(toBuilder=true)
    record Recommendation(
            String recommendationId,
            String title,
            String description,
            RecommendationPriority priority,
            List<ContextId> supportingDiscoveryIds,
            List<String> relatedFilePaths,
            String estimatedImpact
    ) {
    }

    @Builder(toBuilder=true)
    record QueryFindings(
            String originalQuery,
            List<DiscoveryReport.FileReference> results,
            double confidenceScore,
            String summary
    ) {
    }

    @Builder(toBuilder=true)
    record TicketDependency(
            String fromTicketId,
            String toTicketId,
            String dependencyType
    ) {
    }

    @Builder(toBuilder=true)
    record DiscoveryCuration(
            List<DiscoveryReport> discoveryReports,
            CodeMap unifiedCodeMap,
            List<Recommendation> recommendations,
            Map<String, QueryFindings> querySpecificFindings,
            List<MemoryReference> memoryReferences,
            ConsolidationTemplate.ConsolidationSummary consolidationSummary
    ) {
    }

    @Builder(toBuilder=true)
    record PlanningCuration(
            List<PlanningAgentResult> planningAgentResults,
            List<PlanningTicket> finalizedTickets,
            List<TicketDependency> dependencyGraph,
            ContextId discoveryResultId,
            List<MemoryReference> memoryReferences,
            ConsolidationTemplate.ConsolidationSummary consolidationSummary
    ) {
    }

    @Builder(toBuilder=true)
    record TicketCuration(
            List<TicketAgentResult> ticketAgentResults,
            String completionStatus,
            List<String> followUps,
            List<MemoryReference> memoryReferences,
            ConsolidationTemplate.ConsolidationSummary consolidationSummary
    ) {
    }

    @Builder(toBuilder=true)
    record OrchestratorRequest(
            ContextId contextId,
            String goal,
            String phase,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            UpstreamContext.PlanningCollectorContext planningCuration,
            UpstreamContext.TicketCollectorContext ticketCuration,
            PreviousContext.OrchestratorPreviousContext previousContext
    ) {
        public OrchestratorRequest(ContextId contextId, String goal, String phase) {
            this(contextId, goal, phase, null, null, null, null);
        }

        public OrchestratorRequest(String goal, String phase) {
            this(null, goal, phase, null, null, null, null);
        }
    }

    @Builder(toBuilder=true)
    record OrchestratorCollectorRequest(
            ContextId contextId,
            String goal,
            String phase,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            UpstreamContext.PlanningCollectorContext planningCuration,
            UpstreamContext.TicketCollectorContext ticketCuration,
            PreviousContext.OrchestratorCollectorPreviousContext previousContext
    ) {
        public OrchestratorCollectorRequest(String goal, String phase) {
            this(null, goal, phase, null, null, null, null);
        }
    }

    /**
     * Routing type for orchestrator - routes to interrupt, collector, or discovery orchestrator.
     * Note: Routing types do NOT implement DelegationTemplate - they are routing containers.
     */
    @Builder(toBuilder=true)
    record OrchestratorRouting(
            OrchestratorInterruptRequest interruptRequest,
            OrchestratorCollectorRequest collectorRequest,
            DiscoveryOrchestratorRequest orchestratorRequest
    ) implements SomeOf {
        public OrchestratorRouting(OrchestratorInterruptRequest interruptRequest, OrchestratorCollectorRequest collectorRequest) {
            this(interruptRequest, collectorRequest, null);
        }
    }

    @Builder(toBuilder=true)
    record OrchestratorCollectorRouting(
            OrchestratorCollectorInterruptRequest interruptRequest,
            OrchestratorCollectorResult collectorResult,
            OrchestratorRequest orchestratorRequest,
            DiscoveryOrchestratorRequest discoveryRequest,
            PlanningOrchestratorRequest planningRequest,
            TicketOrchestratorRequest ticketRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest
    ) implements SomeOf {
        public OrchestratorCollectorRouting(OrchestratorCollectorResult collectorResult) {
            this(null, collectorResult, null, null, null, null, null, null);
        }
    }

    @Builder(toBuilder=true)
    record DiscoveryOrchestratorRequested(DiscoveryOrchestratorRequest request) {

    }

    /**
     * Request for discovery orchestrator. Uses typed curation fields from upstream collectors.
     * The model does not set upstream context - it receives curated context from the framework.
     */
    @Builder(toBuilder=true)
    record DiscoveryOrchestratorRequest(
            ContextId contextId,
            String goal,
            PreviousContext.DiscoveryOrchestratorPreviousContext previousContext
    ) {
        public DiscoveryOrchestratorRequest(String goal) {
            this(null, goal, null);
        }

        public DiscoveryOrchestratorRequested to() {
            return new DiscoveryOrchestratorRequested(this);
        }

    }

    /**
     * Request for discovery agent. No upstream context field - discovery agents are at the start of the pipeline.
     */
    @Builder(toBuilder=true)
    record DiscoveryAgentRequest(
            ContextId contextId,
            String goal,
            String subdomainFocus,
            PreviousContext.DiscoveryAgentPreviousContext previousContext
    ) {
        public DiscoveryAgentRequest(String goal, String subdomainFocus) {
            this(null, goal, subdomainFocus, null);
        }
    }

    /**
     * DelegationTemplate for discovery orchestrator - contains multiple sub-agent requests.
     * The model returns this to delegate work to discovery agents.
     */
    @Builder(toBuilder=true)
    record DiscoveryAgentRequests(
            List<DiscoveryAgentRequest> requests,
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String goal,
            String delegationRationale,
            List<DelegationTemplate.AgentAssignment> assignments,
            List<DelegationTemplate.ContextSelection> contextSelections,
            Map<String, String> metadata
    ) implements DelegationTemplate {
        public DiscoveryAgentRequests(List<DiscoveryAgentRequest> requests) {
            this(requests, null, null, null, null, null, null, null, null);
        }
    }

    /**
     * Request for discovery collector. No upstream context field - receives discovery agent results directly.
     */
    @Builder(toBuilder=true)
    record DiscoveryCollectorRequest(
            ContextId contextId,
            String goal,
            String discoveryResults,
            PreviousContext.DiscoveryCollectorPreviousContext previousContext
    ) {
        public DiscoveryCollectorRequest(String goal, String discoveryResults) {
            this(null, goal, discoveryResults, null);
        }
    }

    /**
     * Routing type for discovery orchestrator - routes to interrupt, agent requests, or collector.
     * Note: Routing types do NOT implement DelegationTemplate - the agentRequests field IS the DelegationTemplate.
     */
    @Builder(toBuilder=true)
    record DiscoveryOrchestratorRouting(
            DiscoveryOrchestratorInterruptRequest interruptRequest,
            DiscoveryAgentRequests agentRequests,
            DiscoveryCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    @Builder(toBuilder=true)
    record DiscoveryAgentRouting(
            DiscoveryAgentInterruptRequest interruptRequest,
            DiscoveryAgentResult agentResult,
            AgentModels.PlanningOrchestratorRequest planningOrchestratorRequest
    ) implements SomeOf {
        public DiscoveryAgentRouting(DiscoveryAgentInterruptRequest interruptRequest, DiscoveryAgentResult agentResult) {
            this(interruptRequest, agentResult, null);
        }
    }

    @Builder(toBuilder=true)
    record DiscoveryCollectorRouting(
            DiscoveryCollectorInterruptRequest interruptRequest,
            DiscoveryCollectorResult collectorResult,
            OrchestratorRequest orchestratorRequest,
            DiscoveryOrchestratorRequest discoveryRequest,
            PlanningOrchestratorRequest planningRequest,
            TicketOrchestratorRequest ticketRequest,
            ContextOrchestratorRequest contextRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest
    ) implements SomeOf {
    }

    @Builder(toBuilder=true)
    record DiscoveryAgentDispatchRouting(DiscoveryAgentDispatchInterruptRequest interruptRequest,
                                         DiscoveryCollectorRequest collectorRequest) implements SomeOf { }

    /**
     * Request for planning orchestrator. Uses typed curation field from discovery collector.
     */
    @Builder(toBuilder=true)
    record PlanningOrchestratorRequest(
            ContextId contextId,
            String goal,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            PreviousContext.PlanningOrchestratorPreviousContext previousContext
    ) {
        public PlanningOrchestratorRequest(String goal) {
            this(null, goal, null, null);
        }
    }

    /**
     * Request for planning agent. Uses typed curation field from discovery collector.
     */
    @Builder(toBuilder=true)
    record PlanningAgentRequest(
            ContextId contextId,
            String goal,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            PreviousContext.PlanningAgentPreviousContext previousContext
    ) {
        public PlanningAgentRequest(String goal) {
            this(null, goal, null, null);
        }
    }

    /**
     * DelegationTemplate for planning orchestrator - contains multiple sub-agent requests.
     * The model returns this to delegate work to planning agents.
     */
    @Builder(toBuilder=true)
    record PlanningAgentRequests(
            List<PlanningAgentRequest> requests,
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String goal,
            String delegationRationale,
            List<DelegationTemplate.AgentAssignment> assignments,
            List<DelegationTemplate.ContextSelection> contextSelections,
            Map<String, String> metadata
    ) implements DelegationTemplate {
        public PlanningAgentRequests(List<PlanningAgentRequest> requests) {
            this(requests, null, null, null, null, null, null, null, null);
        }
    }

    /**
     * Request for planning collector. Uses typed curation field from discovery collector.
     */
    @Builder(toBuilder=true)
    record PlanningCollectorRequest(
            ContextId contextId,
            String goal,
            String planningResults,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            PreviousContext.PlanningCollectorPreviousContext previousContext
    ) {
        public PlanningCollectorRequest(String goal, String planningResults) {
            this(null, goal, planningResults, null, null);
        }
    }

    /**
     * Routing type for planning orchestrator - routes to interrupt, agent requests, or collector.
     * Note: Routing types do NOT implement DelegationTemplate - the agentRequests field IS the DelegationTemplate.
     */
    @Builder(toBuilder=true)
    record PlanningOrchestratorRouting(
            PlanningOrchestratorInterruptRequest interruptRequest,
            PlanningAgentRequests agentRequests,
            PlanningCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    @Builder(toBuilder=true)
    record PlanningAgentRouting(
            PlanningAgentInterruptRequest interruptRequest,
            PlanningAgentResult agentResult
    ) implements SomeOf {
    }

    @Builder(toBuilder=true)
    record PlanningCollectorRouting(
            PlanningCollectorInterruptRequest interruptRequest,
            PlanningCollectorResult collectorResult,
            PlanningOrchestratorRequest planningRequest,
            DiscoveryOrchestratorRequest discoveryOrchestratorRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest,
            TicketOrchestratorRequest ticketOrchestratorRequest
    ) implements SomeOf {
        public PlanningCollectorRouting(PlanningCollectorInterruptRequest interruptRequest, PlanningCollectorResult collectorResult, PlanningOrchestratorRequest planningRequest, DiscoveryOrchestratorRequest discoveryOrchestratorRequest, ReviewRequest reviewRequest, MergerRequest mergerRequest) {
            this(interruptRequest, collectorResult, planningRequest, discoveryOrchestratorRequest, reviewRequest, mergerRequest, null);
        }
    }

    @Builder(toBuilder=true)
    record PlanningAgentDispatchRouting(
            PlanningAgentDispatchInterruptRequest interruptRequest,
            PlanningCollectorRequest planningCollectorRequest
    ) implements SomeOf {
    }

    /**
     * Request for ticket orchestrator. Uses typed curation fields from discovery and planning collectors.
     */
    @Builder(toBuilder=true)
    record TicketOrchestratorRequest(
            ContextId contextId,
            String goal,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            UpstreamContext.PlanningCollectorContext planningCuration,
            PreviousContext.TicketOrchestratorPreviousContext previousContext
    ) {
        public TicketOrchestratorRequest(String goal) {
            this(null, goal, null, null, null);
        }
    }

    /**
     * Request for ticket agent. Uses typed curation fields from discovery and planning collectors.
     */
    @Builder(toBuilder=true)
    record TicketAgentRequest(
            ContextId contextId,
            String ticketDetails,
            String ticketDetailsFilePath,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            UpstreamContext.PlanningCollectorContext planningCuration,
            PreviousContext.TicketAgentPreviousContext previousContext
    ) {
        public TicketAgentRequest(String ticketDetails, String ticketDetailsFilePath) {
            this(null, ticketDetails, ticketDetailsFilePath, null, null, null);
        }
    }

    /**
     * DelegationTemplate for ticket orchestrator - contains multiple sub-agent requests.
     * The model returns this to delegate work to ticket agents.
     */
    @Builder(toBuilder=true)
    record TicketAgentRequests(
            List<TicketAgentRequest> requests,
            String schemaVersion,
            ContextId resultId,
            ContextId upstreamContextId,
            String goal,
            String delegationRationale,
            List<DelegationTemplate.AgentAssignment> assignments,
            List<DelegationTemplate.ContextSelection> contextSelections,
            Map<String, String> metadata
    ) implements DelegationTemplate {
    }

    /**
     * Request for ticket collector. Uses typed curation fields from discovery and planning collectors.
     */
    @Builder(toBuilder=true)
    record TicketCollectorRequest(
            ContextId contextId,
            String goal,
            String ticketResults,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            UpstreamContext.PlanningCollectorContext planningCuration,
            PreviousContext.TicketCollectorPreviousContext previousContext
    ) {
        public TicketCollectorRequest(String goal, String ticketResults) {
            this(null, goal, ticketResults, null, null, null);
        }
    }

    /**
     * Routing type for ticket orchestrator - routes to interrupt, agent requests, or collector.
     * Note: Routing types do NOT implement DelegationTemplate - the agentRequests field IS the DelegationTemplate.
     */
    @Builder(toBuilder=true)
    record TicketOrchestratorRouting(
            TicketOrchestratorInterruptRequest interruptRequest,
            TicketAgentRequests agentRequests,
            TicketCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    @Builder(toBuilder=true)
    record TicketAgentRouting(
            TicketAgentInterruptRequest interruptRequest,
            TicketAgentResult agentResult
    ) implements SomeOf {
    }

    @Builder(toBuilder=true)
    record TicketCollectorRouting(
            TicketCollectorInterruptRequest interruptRequest,
            TicketCollectorResult collectorResult,
            TicketOrchestratorRequest ticketRequest,
            AgentModels.OrchestratorCollectorRequest orchestratorCollectorRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest
    ) implements SomeOf {
    }

    @Builder(toBuilder=true)
    record TicketAgentDispatchRouting(
            TicketAgentDispatchInterruptRequest interruptRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

    /**
     * Request for review agent. No upstream context - review content is passed directly.
     */
    @Builder(toBuilder=true)
    record ReviewRequest(
            ContextId contextId,
            String content,
            String criteria,
            PreviousContext.ReviewPreviousContext previousContext,
            OrchestratorCollectorRequest returnToOrchestratorCollector,
            DiscoveryCollectorRequest returnToDiscoveryCollector,
            PlanningCollectorRequest returnToPlanningCollector,
            TicketCollectorRequest returnToTicketCollector
    ) {
        public ReviewRequest(String content, String criteria, OrchestratorCollectorRequest returnToOrchestratorCollector, DiscoveryCollectorRequest returnToDiscoveryCollector, PlanningCollectorRequest returnToPlanningCollector, TicketCollectorRequest returnToTicketCollector) {
            this(null, content, criteria, null, returnToOrchestratorCollector, returnToDiscoveryCollector, returnToPlanningCollector, returnToTicketCollector);
        }
    }

    @Builder(toBuilder=true)
    record ReviewRouting(
            ReviewInterruptRequest interruptRequest,
            ReviewAgentResult reviewResult,
            OrchestratorCollectorRequest orchestratorCollectorRequest,
            DiscoveryCollectorRequest discoveryCollectorRequest,
            PlanningCollectorRequest planningCollectorRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

    /**
     * Request for merger agent. No upstream context - merge content is passed directly.
     */
    @Builder(toBuilder=true)
    record MergerRequest(
            ContextId contextId,
            String mergeContext,
            String mergeSummary,
            String conflictFiles,
            PreviousContext.MergerPreviousContext previousContext,
            OrchestratorCollectorRequest returnToOrchestratorCollector,
            DiscoveryCollectorRequest returnToDiscoveryCollector,
            PlanningCollectorRequest returnToPlanningCollector,
            TicketCollectorRequest returnToTicketCollector
    ) {
        public MergerRequest(String mergeContext, String mergeSummary, String conflictFiles, OrchestratorCollectorRequest returnToOrchestratorCollector, DiscoveryCollectorRequest returnToDiscoveryCollector, PlanningCollectorRequest returnToPlanningCollector, TicketCollectorRequest returnToTicketCollector) {
            this(null, mergeContext, mergeSummary, conflictFiles, null, returnToOrchestratorCollector, returnToDiscoveryCollector, returnToPlanningCollector, returnToTicketCollector);
        }
    }

    @Builder(toBuilder=true)
    record MergerRouting(
            MergerInterruptRequest interruptRequest,
            MergerAgentResult mergerResult,
            OrchestratorCollectorRequest orchestratorCollectorRequest,
            DiscoveryCollectorRequest discoveryCollectorRequest,
            PlanningCollectorRequest planningCollectorRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

    /**
     * Request for context orchestrator. Uses typed curation fields from collectors.
     */
    @Builder(toBuilder=true)
    record ContextOrchestratorRequest(
            ContextId contextId,
            String goal,
            String phase,
            UpstreamContext.DiscoveryCollectorContext discoveryCuration,
            UpstreamContext.PlanningCollectorContext planningCuration,
            UpstreamContext.TicketCollectorContext ticketCuration,
            PreviousContext previousContext
    ) {
        public ContextOrchestratorRequest(String goal, String phase) {
            this(null, goal, phase, null, null, null, null);
        }
    }

//    record ContextAgentRequest(String goal, String phase) {
//    }

//    record ContextAgentRequests(List<ContextAgentRequest> requests) {
//    }

//    record ContextCollectorRequest(String goal, String phase) {
//    }

//    record ContextOrchestratorRouting(
//            ContextOrchestratorInterruptRequest interruptRequest,
//            ContextAgentRequests agentRequests,
//            ContextCollectorRequest collectorRequest
//    ) implements SomeOf {
//    }

//    record ContextAgentRouting(
//            ContextAgentInterruptRequest interruptRequest,
//            ContextAgentResult agentResult
//    ) implements SomeOf {
//    }

//    record ContextCollectorRouting(
//            ContextCollectorInterruptRequest interruptRequest,
//            ContextCollectorResult collectorResult,
//            OrchestratorRequest orchestratorRequest,
//            DiscoveryOrchestratorRequest discoveryRequest,
//            PlanningOrchestratorRequest planningRequest,
//            TicketOrchestratorRequest ticketRequest,
//            ContextOrchestratorRequest contextRequest,
//            ReviewRequest reviewRequest,
//            MergerRequest mergerRequest
//    ) implements SomeOf {
//    }

    //    TODO: Need to add all of the ...AgentDispatchResult, routing to their appropriate collectors
//    record ContextAgentDispatchRouting(
//            ContextAgentDispatchInterruptRequest interruptRequest,
//    ) implements SomeOf {
//    }
}
