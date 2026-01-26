package com.hayden.multiagentidelib.template;

import com.hayden.multiagentidelib.agent.AgentModels;
import com.hayden.utilitymodule.acp.events.ArtifactKey;

import java.util.List;
import java.util.Map;

public interface ConsolidationTemplate {

    String schemaVersion();

    ArtifactKey resultId();

    List<InputReference> inputs();

    String mergeStrategy();

    List<ConflictResolution> conflictResolutions();

    Map<String, Double> aggregatedMetrics();

    String consolidatedOutput();

    AgentModels.CollectorDecision decision();

    List<ArtifactKey> upstreamContextChain();

    Map<String, String> metadata();

    default List<Curation> curations() {
        return List.of();
    }

    record InputReference(
            ArtifactKey inputContextId,
            String inputType,
            String inputSummary
    ) {
    }

    record ConflictResolution(
            String conflictDescription,
            String resolutionApproach,
            List<ArtifactKey> conflictingInputs
    ) {
    }

    interface Curation {
        ArtifactKey artifactKey();

        ArtifactKey sourceResultId();

        String selectionRationale();
    }

    record ConsolidationSummary(
            String consolidatedOutput,
            Map<String, String> metadata
    ) {
    }
}
