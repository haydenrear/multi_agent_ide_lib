package com.hayden.multiagentidelib.agent;

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
            List<InterruptType> interruptsRequested,
            String output
    ) {
    }

    record DiscoveryOrchestratorResult(
            DelegationPlan delegation,
            List<InterruptType> interruptsRequested,
            String output
    ) {
    }

    record PlanningOrchestratorResult(
            DelegationPlan delegation,
            List<InterruptType> interruptsRequested,
            String output
    ) {
    }

    record TicketOrchestratorResult(
            DelegationPlan delegation,
            List<InterruptType> interruptsRequested,
            String output
    ) {
    }

    /**
     * Results for non-orchestrator agents.
     */
    record DiscoveryAgentResult(
            String output,
            List<InterruptType> interruptsRequested
    ) {
    }

    record PlanningAgentResult(
            String output,
            List<InterruptType> interruptsRequested
    ) {
    }

    record TicketAgentResult(
            String output,
            List<InterruptType> interruptsRequested
    ) {
    }

    record ReviewAgentResult(
            String output,
            List<InterruptType> interruptsRequested
    ) {
    }

    record MergerAgentResult(
            String output,
            List<InterruptType> interruptsRequested
    ) {

        public boolean isNeedingHumanReview() {
            return interruptsRequested.contains(InterruptType.HUMAN_REVIEW);
        }


        public boolean isNeedingAgentReview() {
            return interruptsRequested.contains(InterruptType.AGENT_REVIEW);
        }
    }

    record SummaryAgentResult(
            String output,
            List<InterruptType> interruptsRequested
    ) {
    }

    record ContextAgentResult(
            String output,
            List<InterruptType> interruptsRequested
    ) {
    }

    /**
     * Results for collector agents.
     */
    record DiscoveryCollectorResult(
            String consolidatedOutput,
            List<InterruptType> interruptsRequested,
            CollectorDecision collectorDecision
    ) {
    }

    record PlanningCollectorResult(
            String consolidatedOutput,
            List<InterruptType> interruptsRequested,
            CollectorDecision collectorDecision
    ) {
    }

    record ContextCollectorResult(
            String consolidatedOutput,
            List<InterruptType> interruptsRequested,
            CollectorDecision collectorDecision
    ) {
    }

    record OrchestratorCollectorResult(
            String consolidatedOutput,
            List<InterruptType> interruptsRequested,
            CollectorDecision collectorDecision
    ) {
    }

    record TicketCollectorResult(
            String consolidatedOutput,
            List<InterruptType> interruptsRequested,
            CollectorDecision collectorDecision
    ) {
    }
}
