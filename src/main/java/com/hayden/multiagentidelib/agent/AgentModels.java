package com.hayden.multiagentidelib.agent;

import com.embabel.agent.api.common.SomeOf;
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

    @Builder
    record OrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record OrchestratorCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record DiscoveryOrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record DiscoveryAgentInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record DiscoveryCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record DiscoveryAgentDispatchInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record PlanningOrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record PlanningAgentInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record PlanningCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record PlanningAgentDispatchInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record TicketOrchestratorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record TicketAgentInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record TicketCollectorInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
    record TicketAgentDispatchInterruptRequest(
            Events.InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    @Builder
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

    @Builder
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
    @Builder
    record AgentInteraction(
            InteractionType interactionType,
            String message
    ) {
    }

    /**
     * Defines how to kick off sub-agents for orchestrated work.
     */
    @Builder
    record DelegationPlan(
            String summary,
            Map<String, String> subAgentGoals
    ) {
    }

    /**
     * Orchestrator-specific results.
     */
    @Builder
    record OrchestratorAgentResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    @Builder
    record DiscoveryOrchestratorResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    @Builder
    record PlanningOrchestratorResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    @Builder
    record TicketOrchestratorResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    /**
     * Results for non-orchestrator agents.
     */
    @Builder
    record DiscoveryAgentResult(
            String output
    ) {
    }

    @Builder
    record PlanningAgentResult(
            String output
    ) {
    }

    @Builder
    record TicketAgentResult(
            String output
    ) {
    }

    @Builder
    record ReviewAgentResult(
            String output
    ) {
    }

    @Builder
    record MergerAgentResult(
            String output
    ) {
    }

    @Builder
    record SummaryAgentResult(
            String output
    ) {
    }

    @Builder
    record ContextAgentResult(
            String output
    ) {
    }

    /**
     * Results for collector agents.
     */
    @Builder
    record DiscoveryCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    @Builder
    record PlanningCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    @Builder
    record ContextCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    @Builder
    record OrchestratorCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    @Builder
    record TicketCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    @Builder
    record OrchestratorRequest(String goal, String phase) {
    }

    @Builder
    record OrchestratorCollectorRequest(String goal, String phase) {
    }

    @Builder
    record OrchestratorRouting(
            OrchestratorInterruptRequest interruptRequest,
            OrchestratorCollectorRequest collectorRequest,
            DiscoveryOrchestratorRequest orchestratorRequest
    ) implements SomeOf {
        public OrchestratorRouting(OrchestratorInterruptRequest interruptRequest, OrchestratorCollectorRequest collectorRequest) {
            this(interruptRequest, collectorRequest, null);
        }
    }

    @Builder
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

    @Builder
    record DiscoveryOrchestratorRequested(DiscoveryOrchestratorRequest request) {

    }

    @Builder
    record DiscoveryOrchestratorRequest(String goal) {

        public DiscoveryOrchestratorRequested to() {
            return new DiscoveryOrchestratorRequested(this);
        }

    }

    @Builder
    record DiscoveryAgentRequest(String goal, String subdomainFocus) {
    }

    @Builder
    record DiscoveryAgentRequests(List<DiscoveryAgentRequest> requests) {
    }

    @Builder
    record DiscoveryCollectorRequest(String goal, String discoveryResults) {
    }

    @Builder
    record DiscoveryOrchestratorRouting(
            DiscoveryOrchestratorInterruptRequest interruptRequest,
            DiscoveryAgentRequests agentRequests,
            DiscoveryCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    @Builder
    record DiscoveryAgentRouting(
            DiscoveryAgentInterruptRequest interruptRequest,
            DiscoveryAgentResult agentResult,
            AgentModels.PlanningOrchestratorRequest planningOrchestratorRequest
    ) implements SomeOf {
        public DiscoveryAgentRouting(DiscoveryAgentInterruptRequest interruptRequest, DiscoveryAgentResult agentResult) {
            this(interruptRequest, agentResult, null);
        }
    }

    @Builder
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

    @Builder
    record DiscoveryAgentDispatchRouting(DiscoveryAgentDispatchInterruptRequest interruptRequest,
                                         DiscoveryCollectorRequest collectorRequest) implements SomeOf { }

    @Builder
    record PlanningOrchestratorRequest(String goal) {
    }

    @Builder
    record PlanningAgentRequest(String goal) {
    }

    @Builder
    record PlanningAgentRequests(List<PlanningAgentRequest> requests) {
    }

    @Builder
    record PlanningCollectorRequest(String goal, String planningResults) {
    }

    @Builder
    record PlanningOrchestratorRouting(
            PlanningOrchestratorInterruptRequest interruptRequest,
            PlanningAgentRequests agentRequests,
            PlanningCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    @Builder
    record PlanningAgentRouting(
            PlanningAgentInterruptRequest interruptRequest,
            PlanningAgentResult agentResult
    ) implements SomeOf {
    }

    @Builder
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

    @Builder
    record PlanningAgentDispatchRouting(
            PlanningAgentDispatchInterruptRequest interruptRequest,
            PlanningCollectorRequest planningCollectorRequest
    ) implements SomeOf {
    }

    @Builder
    record TicketOrchestratorRequest(
            String goal,
            String tickets,
            String discoveryContext,
            String planningContext
    ) {
    }

    @Builder
    record TicketAgentRequest(
            String ticketDetails,
            String ticketDetailsFilePath,
            String discoveryContext,
            String planningContext
    ) {
    }

    @Builder
    record TicketAgentRequests(List<TicketAgentRequest> requests) {
    }

    @Builder
    record TicketCollectorRequest(String goal, String ticketResults) {
    }

    @Builder
    record TicketOrchestratorRouting(
            TicketOrchestratorInterruptRequest interruptRequest,
            TicketAgentRequests agentRequests,
            TicketCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    @Builder
    record TicketAgentRouting(
            TicketAgentInterruptRequest interruptRequest,
            TicketAgentResult agentResult
    ) implements SomeOf {
    }

    @Builder
    record TicketCollectorRouting(
            TicketCollectorInterruptRequest interruptRequest,
            TicketCollectorResult collectorResult,
            TicketOrchestratorRequest ticketRequest,
            AgentModels.OrchestratorCollectorRequest orchestratorCollectorRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest
    ) implements SomeOf {
    }

    @Builder
    record TicketAgentDispatchRouting(
            TicketAgentDispatchInterruptRequest interruptRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

    @Builder
    record ReviewRequest(
            String content,
            String criteria,
            OrchestratorCollectorRequest returnToOrchestratorCollector,
            DiscoveryCollectorRequest returnToDiscoveryCollector,
            PlanningCollectorRequest returnToPlanningCollector,
            TicketCollectorRequest returnToTicketCollector
    ) {
    }

    @Builder
    record ReviewRouting(
            ReviewInterruptRequest interruptRequest,
            ReviewAgentResult reviewResult,
            OrchestratorCollectorRequest orchestratorCollectorRequest,
            DiscoveryCollectorRequest discoveryCollectorRequest,
            PlanningCollectorRequest planningCollectorRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

    @Builder
    record MergerRequest(
            String mergeContext,
            String mergeSummary,
            String conflictFiles,
            OrchestratorCollectorRequest returnToOrchestratorCollector,
            DiscoveryCollectorRequest returnToDiscoveryCollector,
            PlanningCollectorRequest returnToPlanningCollector,
            TicketCollectorRequest returnToTicketCollector
    ) {
    }

    @Builder
    record MergerRouting(
            MergerInterruptRequest interruptRequest,
            MergerAgentResult mergerResult,
            OrchestratorCollectorRequest orchestratorCollectorRequest,
            DiscoveryCollectorRequest discoveryCollectorRequest,
            PlanningCollectorRequest planningCollectorRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

    @Builder
    record ContextOrchestratorRequest(String goal, String phase) {
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
