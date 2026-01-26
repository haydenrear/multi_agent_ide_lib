package com.hayden.multiagentidelib.prompt;

import com.hayden.multiagentidelib.agent.AgentType;

import java.util.Set;

/**
 * Listener interface for prompt contribution events.
 * 
 * Implementations can capture contribution data for artifact emission,
 * logging, or other observability purposes.
 */
public interface PromptContributionListener {
    
    /**
     * Called when a prompt contributor produces a contribution.
     * 
     * @param contributorName The name of the contributor
     * @param priority The priority of the contributor
     * @param applicableAgents The agent types this contributor applies to
     * @param contributedText The text produced by the contributor
     * @param orderIndex The order in which this contribution was assembled (0-based)
     * @param context The prompt context that was used
     */
    void onContribution(
            String contributorName,
            int priority,
            Set<AgentType> applicableAgents,
            String contributedText,
            int orderIndex,
            PromptContext context
    );
    
    /**
     * Called when prompt assembly is complete.
     * 
     * @param basePrompt The base prompt before contributions
     * @param assembledPrompt The final assembled prompt with all contributions
     * @param contributionCount The total number of contributions applied
     * @param context The prompt context that was used
     */
    default void onAssemblyComplete(
            String basePrompt,
            String assembledPrompt,
            int contributionCount,
            PromptContext context
    ) {
        // Default no-op
    }
}
