package com.hayden.multiagentidelib.agent;

import com.hayden.multiagentidelib.template.DiscoveryReport;
import lombok.Builder;

import java.time.Instant;

public sealed interface PreviousContext permits
       PreviousContext.OrchestratorPreviousContext,
       PreviousContext.OrchestratorCollectorPreviousContext,
       PreviousContext.DiscoveryOrchestratorPreviousContext,
       PreviousContext.PlanningOrchestratorPreviousContext,
       PreviousContext.TicketOrchestratorPreviousContext,
       PreviousContext.DiscoveryAgentPreviousContext,
       PreviousContext.PlanningAgentPreviousContext,
       PreviousContext.TicketAgentPreviousContext,
       PreviousContext.DiscoveryCollectorPreviousContext,
       PreviousContext.PlanningCollectorPreviousContext,
       PreviousContext.TicketCollectorPreviousContext,
       PreviousContext.ReviewPreviousContext,
       PreviousContext.MergerPreviousContext {

    ContextId previousContextId();

    String serializedOutput();

    String errorMessage();

    String errorStackTrace();

    int attemptNumber();

    Instant previousAttemptAt();

    @Builder
    record OrchestratorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            UpstreamContext.DiscoveryCollectorContext previousDiscoveryCuration,
            UpstreamContext.PlanningCollectorContext previousPlanningCuration,
            UpstreamContext.TicketCollectorContext previousTicketCuration
    ) implements PreviousContext {
    }

    @Builder
    record OrchestratorCollectorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            UpstreamContext.DiscoveryCollectorContext previousDiscoveryCuration,
            UpstreamContext.PlanningCollectorContext previousPlanningCuration,
            UpstreamContext.TicketCollectorContext previousTicketCuration
    ) implements PreviousContext {
    }

    @Builder
    record DiscoveryOrchestratorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            UpstreamContext.DiscoveryCollectorContext previousDiscoveryCuration,
            UpstreamContext.PlanningCollectorContext previousPlanningCuration,
            UpstreamContext.TicketCollectorContext previousTicketCuration
    ) implements PreviousContext {
    }

    @Builder
    record PlanningOrchestratorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            UpstreamContext.DiscoveryCollectorContext previousDiscoveryCuration,
            UpstreamContext.PlanningCollectorContext previousPlanningCuration
    ) implements PreviousContext {
    }

    @Builder
    record TicketOrchestratorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            UpstreamContext.DiscoveryCollectorContext previousDiscoveryCuration,
            UpstreamContext.PlanningCollectorContext previousPlanningCuration,
            UpstreamContext.TicketCollectorContext previousTicketCuration
    ) implements PreviousContext {
    }

    @Builder
    record DiscoveryAgentPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            DiscoveryReport previousDiscoveryResult
    ) implements PreviousContext {
    }

    @Builder
    record PlanningAgentPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            AgentModels.PlanningAgentResult previousPlanningResult
    ) implements PreviousContext {
    }

    @Builder
    record TicketAgentPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            AgentModels.TicketAgentResult previousTicketResult
    ) implements PreviousContext {
    }

    @Builder
    record DiscoveryCollectorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            AgentModels.DiscoveryCollectorResult previousDiscoveryResult,
            UpstreamContext.DiscoveryCollectorContext previousDiscoveryCuration
    ) implements PreviousContext {
    }

    @Builder
    record PlanningCollectorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            AgentModels.PlanningCollectorResult previousPlanningResult,
            UpstreamContext.PlanningCollectorContext previousPlanningCuration
    ) implements PreviousContext {
    }

    @Builder
    record TicketCollectorPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            AgentModels.TicketCollectorResult previousTicketResult,
            UpstreamContext.TicketCollectorContext previousTicketCuration
    ) implements PreviousContext {
    }

    @Builder
    record ReviewPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            AgentModels.ReviewEvaluation previousReviewEvaluation
    ) implements PreviousContext {
    }

    @Builder
    record MergerPreviousContext(
            ContextId previousContextId,
            String serializedOutput,
            String errorMessage,
            String errorStackTrace,
            int attemptNumber,
            Instant previousAttemptAt,
            AgentModels.MergerValidation previousMergerValidation
    ) implements PreviousContext {
    }
}
