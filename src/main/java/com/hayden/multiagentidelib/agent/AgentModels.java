package com.hayden.multiagentidelib.agent;

import com.embabel.agent.api.common.SomeOf;
import java.util.List;
import java.util.Map;

public interface AgentModels {

    enum InteractionType {
        AGENT_MESSAGE,
        USER_MESSAGE,
        INTERRUPT_REQUEST,
        RESULT_HANDOFF
    }

    enum InterruptType {
        HUMAN_REVIEW,
        AGENT_REVIEW,
        PAUSE,
        STOP,
        BRANCH,
        PRUNE
    }

    interface InterruptDescriptor {
        InterruptType type();
        String reason();
    }

    record InterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record OrchestratorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record OrchestratorCollectorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record DiscoveryOrchestratorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record DiscoveryAgentInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record DiscoveryCollectorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record DiscoveryAgentDispatchInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record PlanningOrchestratorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record PlanningAgentInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record PlanningCollectorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record PlanningAgentDispatchInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record TicketOrchestratorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record TicketAgentInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record TicketCollectorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record TicketAgentDispatchInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ReviewInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record MergerInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextOrchestratorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextAgentInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextCollectorInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    record ContextAgentDispatchInterruptRequest(
            InterruptType type,
            String reason
    ) implements InterruptDescriptor {
    }

    enum CollectorDecisionType {
        ROUTE_BACK,
        ADVANCE_PHASE,
        STOP
    }

    record CollectorDecision(
            CollectorDecisionType decisionType,
            String rationale,
            String requestedPhase
    ) {
    }

    /**
     * Shared data models describing agent interactions, results, and interrupts.
     * These are intentionally transport-agnostic and can be serialized as needed.
     */
    record AgentInteraction(
            InteractionType interactionType,
            String message
    ) {
    }

    /**
     * Defines how to kick off sub-agents for orchestrated work.
     */
    record DelegationPlan(
            String summary,
            Map<String, String> subAgentGoals
    ) {
    }

    /**
     * Orchestrator-specific results.
     */
    record OrchestratorAgentResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    record DiscoveryOrchestratorResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    record PlanningOrchestratorResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    record TicketOrchestratorResult(
            DelegationPlan delegation,
            String output
    ) {
    }

    /**
     * Results for non-orchestrator agents.
     */
    record DiscoveryAgentResult(
            String output
    ) {
    }

    record PlanningAgentResult(
            String output
    ) {
    }

    record TicketAgentResult(
            String output
    ) {
    }

    record ReviewAgentResult(
            String output
    ) {
    }

    record MergerAgentResult(
            String output
    ) {
    }

    record SummaryAgentResult(
            String output
    ) {
    }

    record ContextAgentResult(
            String output
    ) {
    }

    /**
     * Results for collector agents.
     */
    record DiscoveryCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    record PlanningCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    record ContextCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    record OrchestratorCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    record TicketCollectorResult(
            String consolidatedOutput,
            CollectorDecision collectorDecision
    ) {
    }

    record OrchestratorRequest(String goal, String phase) {
    }

    record OrchestratorCollectorRequest(String goal, String phase) {
    }

    record OrchestratorRouting(
            OrchestratorInterruptRequest interruptRequest,
            OrchestratorCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    record OrchestratorCollectorRouting(
            OrchestratorCollectorInterruptRequest interruptRequest,
            OrchestratorCollectorResult collectorResult,
            DiscoveryOrchestratorRequest discoveryRequest,
            PlanningOrchestratorRequest planningRequest,
            TicketOrchestratorRequest ticketRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest
    ) implements SomeOf {
    }

    record DiscoveryOrchestratorRequest(String goal) {
    }

    record DiscoveryAgentRequest(String goal, String subdomainFocus) {
    }

    record DiscoveryAgentRequests(List<DiscoveryAgentRequest> requests) {
    }

    record DiscoveryCollectorRequest(String goal, String discoveryResults) {
    }

    record DiscoveryOrchestratorRouting(
            DiscoveryOrchestratorInterruptRequest interruptRequest,
            DiscoveryAgentRequests agentRequests,
            DiscoveryCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    record DiscoveryAgentRouting(
            DiscoveryAgentInterruptRequest interruptRequest,
            DiscoveryAgentResult agentResult
    ) implements SomeOf {
    }

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

    record DiscoveryAgentDispatchRouting(DiscoveryAgentDispatchInterruptRequest interruptRequest,
                                         DiscoveryCollectorRequest collectorRequest) implements SomeOf { }

    record PlanningOrchestratorRequest(String goal) {
    }

    record PlanningAgentRequest(String goal) {
    }

    record PlanningAgentRequests(List<PlanningAgentRequest> requests) {
    }

    record PlanningCollectorRequest(String goal, String planningResults) {
    }

    record PlanningOrchestratorRouting(
            PlanningOrchestratorInterruptRequest interruptRequest,
            PlanningAgentRequests agentRequests,
            PlanningCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    record PlanningAgentRouting(
            PlanningAgentInterruptRequest interruptRequest,
            PlanningAgentResult agentResult
    ) implements SomeOf {
    }

    record PlanningCollectorRouting(
            PlanningCollectorInterruptRequest interruptRequest,
            PlanningCollectorResult collectorResult,
            PlanningOrchestratorRequest planningRequest,
            DiscoveryOrchestratorRequest discoveryOrchestratorRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest
    ) implements SomeOf {
    }

    record PlanningAgentDispatchRouting(
            PlanningAgentDispatchInterruptRequest interruptRequest,
            PlanningCollectorRequest planningCollectorRequest
    ) implements SomeOf {
    }

    record TicketOrchestratorRequest(
            String goal,
            String tickets,
            String discoveryContext,
            String planningContext
    ) {
    }

    record TicketAgentRequest(
            String ticketDetails,
            String ticketDetailsFilePath,
            String discoveryContext,
            String planningContext
    ) {
    }

    record TicketAgentRequests(List<TicketAgentRequest> requests) {
    }

    record TicketCollectorRequest(String goal, String ticketResults) {
    }

    record TicketOrchestratorRouting(
            TicketOrchestratorInterruptRequest interruptRequest,
            TicketAgentRequests agentRequests,
            TicketCollectorRequest collectorRequest
    ) implements SomeOf {
    }

    record TicketAgentRouting(
            TicketAgentInterruptRequest interruptRequest,
            TicketAgentResult agentResult
    ) implements SomeOf {
    }

    record TicketCollectorRouting(
            TicketCollectorInterruptRequest interruptRequest,
            TicketCollectorResult collectorResult,
            TicketOrchestratorRequest ticketRequest,
            ReviewRequest reviewRequest,
            MergerRequest mergerRequest
    ) implements SomeOf {
    }

    record TicketAgentDispatchRouting(
            TicketAgentDispatchInterruptRequest interruptRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

    record ReviewRequest(
            String content,
            String criteria,
            OrchestratorCollectorRequest returnToOrchestratorCollector,
            DiscoveryCollectorRequest returnToDiscoveryCollector,
            PlanningCollectorRequest returnToPlanningCollector,
            TicketCollectorRequest returnToTicketCollector
    ) {
    }

    record ReviewRouting(
            ReviewInterruptRequest interruptRequest,
            ReviewAgentResult reviewResult,
            OrchestratorCollectorRequest orchestratorCollectorRequest,
            DiscoveryCollectorRequest discoveryCollectorRequest,
            PlanningCollectorRequest planningCollectorRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

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

    record MergerRouting(
            MergerInterruptRequest interruptRequest,
            MergerAgentResult mergerResult,
            OrchestratorCollectorRequest orchestratorCollectorRequest,
            DiscoveryCollectorRequest discoveryCollectorRequest,
            PlanningCollectorRequest planningCollectorRequest,
            TicketCollectorRequest ticketCollectorRequest
    ) implements SomeOf {
    }

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
