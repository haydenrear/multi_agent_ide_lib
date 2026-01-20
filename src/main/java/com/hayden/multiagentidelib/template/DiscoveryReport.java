package com.hayden.multiagentidelib.template;

import com.hayden.multiagentidelib.agent.ContextId;

import java.util.List;
import java.util.Map;

//TODO: add report type, specific to a particular thing, such as architecture, etc.
public record DiscoveryReport(
        String schemaVersion,
        ContextId resultId,
        ContextId upstreamContextId,
        List<FileReference> fileReferences,
        List<CrossLink> crossLinks,
        List<SemanticTag> semanticTags,
        List<GeneratedQuery> generatedQueries,
        List<DiagramRepresentation> diagrams,
        String architectureOverview,
        List<String> keyPatterns,
        List<String> integrationPoints,
        Map<String, Double> relevanceScores,
        List<MemoryReference> memoryReferences
) {

    public record FileReference(
            String filePath,
            int startLine,
            int endLine,
            double relevanceScore,
            String snippet,
            List<String> tags
    ) {
    }

    public record CrossLink(
            String linkId,
            String sourceReferenceId,
            String targetReferenceId,
            LinkType linkType,
            String description
    ) {
    }

    public enum LinkType {
        CALLS,
        DEPENDS_ON,
        IMPLEMENTS,
        EXTENDS,
        RELATED_TO,
        DATA_FLOW
    }

    public record SemanticTag(
            String tagId,
            String tagName,
            TagCategory tagCategory,
            double confidence,
            List<String> appliedTo
    ) {
    }

    public enum TagCategory {
        ARCHITECTURE,
        PATTERN,
        DATA_FLOW,
        ENTRY_POINT,
        TEST,
        INTEGRATION,
        SECURITY,
        PERFORMANCE
    }

    public record GeneratedQuery(
            String queryId,
            String queryText,
            List<String> expectedResultPaths,
            QueryType queryType,
            Map<String, Double> groundTruthRelevance
    ) {
    }

    public enum QueryType {
        CODE_SEARCH,
        PATTERN_FIND,
        DEPENDENCY_TRACE,
        API_LOOKUP,
        TEST_FIND
    }

    public record DiagramRepresentation(
            String diagramId,
            DiagramType diagramType,
            String title,
            List<Map<String, Object>> nodes,
            List<Map<String, Object>> edges,
            String mermaidSource
    ) {
    }

    public enum DiagramType {
        CLASS,
        SEQUENCE,
        COMPONENT,
        DATA_FLOW,
        DEPENDENCY
    }
}
