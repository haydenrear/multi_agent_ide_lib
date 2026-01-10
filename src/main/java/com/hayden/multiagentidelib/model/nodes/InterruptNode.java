package com.hayden.multiagentidelib.model.nodes;

import lombok.Builder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node representing an interrupt request and its resolution lifecycle.
 */
@Builder(toBuilder = true)
public record InterruptNode(
        String nodeId,
        String title,
        String goal,
        GraphNode.NodeStatus status,
        String parentNodeId,
        List<String> childNodeIds,
        Map<String, String> metadata,
        Instant createdAt,
        Instant lastUpdatedAt,
        InterruptContext interruptContext
) implements GraphNode, Viewable<String>, InterruptRecord {

    public InterruptNode {
        if (nodeId == null || nodeId.isEmpty()) throw new IllegalArgumentException("nodeId required");
        if (interruptContext == null) throw new IllegalArgumentException("interruptContext required");
        if (childNodeIds == null) childNodeIds = new ArrayList<>();
        if (metadata == null) metadata = new HashMap<>();
    }

    @Override
    public NodeType nodeType() {
        return NodeType.INTERRUPT;
    }

    @Override
    public String getView() {
        return interruptContext.reason();
    }

    public InterruptNode withStatus(GraphNode.NodeStatus newStatus) {
        return toBuilder()
                .status(newStatus)
                .lastUpdatedAt(Instant.now())
                .build();
    }
}
