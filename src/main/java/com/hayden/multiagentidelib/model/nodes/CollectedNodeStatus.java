package com.hayden.multiagentidelib.model.nodes;

/**
 * Snapshot of a collected node's identity and status at collection time.
 */
public record CollectedNodeStatus(
        String nodeId,
        String title,
        GraphNode.NodeType nodeType,
        GraphNode.NodeStatus status
) {
}
