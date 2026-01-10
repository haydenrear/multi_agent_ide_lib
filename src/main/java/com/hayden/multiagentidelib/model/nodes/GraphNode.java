package com.hayden.multiagentidelib.model.nodes;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Sealed interface representing a node in the computation graph.
 * Nodes can implement capability mixins (Branchable, Editable, etc.) for optional behaviors.
 * This is a data-oriented interface that composes capabilities.
 */
public sealed interface GraphNode
        permits AskPermissionNode, CollectorNode, DiscoveryCollectorNode, DiscoveryNode, DiscoveryOrchestratorNode, InterruptNode, MergeNode, OrchestratorNode, PlanningCollectorNode, PlanningNode, PlanningOrchestratorNode, ReviewNode, SummaryNode, TicketCollectorNode, TicketNode, TicketOrchestratorNode {

    GraphNode withStatus(NodeStatus nodeStatus);

    /**
     * Unique identifier for this node.
     */
    String nodeId();

    /**
     * Human-readable title/name.
     */
    String title();

    /**
     * Goal or description of work for this node.
     */
    String goal();

    /**
     * Current status of this node.
     */
    NodeStatus status();

    /**
     * ID of parent node, or null if root.
     */
    String parentNodeId();

    /**
     * Child node IDs.
     */
    List<String> childNodeIds();

    /**
     * Metadata and annotations.
     */
    Map<String, String> metadata();

    /**
     * Timestamp when node was created.
     */
    Instant createdAt();

    /**
     * Timestamp when node status last changed.
     */
    Instant lastUpdatedAt();

    /**
     * Type/kind of this node for pattern matching.
     */
    NodeType nodeType();

    /**
     * Check if this node implements a specific capability.
     */
    default boolean hasCapability(Class<?> capabilityClass) {
        return this.getClass().isAssignableFrom(capabilityClass)
                || capabilityClass.isAssignableFrom(this.getClass());
    }

    /**
     * Node status values.
     */
    enum NodeStatus {
        PENDING,           // Not yet ready
        READY,             // Ready to execute
        RUNNING,           // Currently executing
        WAITING_REVIEW,    // Awaiting human/agent review
        WAITING_INPUT,     // Awaiting user input
        COMPLETED,         // Successfully completed
        FAILED,            // Execution failed
        CANCELED,          // Manually canceled
        PRUNED,            // Removed from graph
    }

    /**
     * Node type for classification.
     */
    enum NodeType {
        ORCHESTRATOR,
        PLANNING,
        WORK,
        HUMAN_REVIEW,
        AGENT_REVIEW,
        SUMMARY,
        INTERRUPT,
        PERMISSION
    }
}
