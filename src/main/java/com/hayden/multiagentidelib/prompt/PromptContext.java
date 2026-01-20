package com.hayden.multiagentidelib.prompt;

import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.multiagentidelib.agent.ContextId;
import com.hayden.multiagentidelib.agent.PreviousContext;
import com.hayden.multiagentidelib.agent.UpstreamContext;
import com.hayden.multiagentidelib.agent.BlackboardHistory;

import java.util.List;
import java.util.Map;

/**
 * Context for prompt assembly containing agent type, context identifiers,
 * upstream contexts from prior workflow phases, and optional metadata.
 */
public record PromptContext(
        AgentType agentType,
        ContextId currentContextId,
        List<UpstreamContext> upstreamContexts,
        PreviousContext previousContext,
        BlackboardHistory.History blackboardHistory,
        Map<String, Object> metadata
) {
    /**
     * Constructor that normalizes null upstream contexts to empty list.
     */
    public PromptContext {
        if (upstreamContexts == null) {
            upstreamContexts = List.of();
        }
    }
}
