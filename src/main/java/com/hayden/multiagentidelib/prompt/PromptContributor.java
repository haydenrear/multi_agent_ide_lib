package com.hayden.multiagentidelib.prompt;

import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactHashing;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import com.hayden.utilitymodule.acp.events.Templated;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface PromptContributor extends Templated {

    String name();

    Set<AgentType> applicableAgents();

    String contribute(PromptContext context);

    String template();

    default Map<String, Object> args() {
        return Map.of();
    }

    int priority();

    @Override
    default String templateStaticId() {
        return name();
    }

    @Override
    default String templateText() {
        return template();
    }

    @Override
    default Optional<String> contentHash() {
        String text = templateText();
        return text == null ? Optional.empty() : Optional.of(ArtifactHashing.hashText(text));
    }

    @Override
    default ArtifactKey templateArtifactKey() {
        return ArtifactKey.createRoot();
    }

    @Override
    default ArtifactKey artifactKey() {
        return templateArtifactKey();
    }

    @Override
    default String artifactType() {
        return "PromptContributionTemplate";
    }

    @Override
    default Map<String, String> metadata() {
        return Map.of();
    }

    @Override
    default List<Artifact> children() {
        return List.of();
    }

    default boolean isApplicable(AgentType agentType) {
        if (agentType == null) {
            return false;
        }
        Set<AgentType> targets = applicableAgents();
        return targets != null && (targets.contains(agentType) || targets.contains(AgentType.ALL));
    }
}
