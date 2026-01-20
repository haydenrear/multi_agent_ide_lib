package com.hayden.multiagentidelib.template;

import com.hayden.multiagentidelib.agent.ContextId;

import java.util.List;

public record PlanningTicket(
        String schemaVersion,
        ContextId resultId,
        String ticketId,
        String title,
        String description,
        List<TicketTask> tasks,
        List<String> dependencies,
        List<String> acceptanceCriteria,
        String effortEstimate,
        List<DiscoveryLink> discoveryLinks,
        int priority,
        List<MemoryReference> memoryReferences
) {

    public record TicketTask(
            String taskId,
            String description,
            Double estimatedHours,
            List<String> relatedFiles
    ) {
    }

    public record DiscoveryLink(
            ContextId discoveryResultId,
            String referenceId,
            String linkRationale
    ) {
    }
}
