package com.hayden.multiagentidelib.prompt;

import com.hayden.multiagentidelib.agent.AgentType;
import com.hayden.utilitymodule.acp.events.Artifact;
import com.hayden.utilitymodule.acp.events.ArtifactKey;
import org.springframework.stereotype.Service;

@Service
public class ContextIdService {

    public ArtifactKey generate(String workflowRunId, AgentType agentType, Artifact.AgentModel parent) {
        if (workflowRunId != null && ArtifactKey.isValid(workflowRunId)) {
            return new ArtifactKey(workflowRunId).createChild();
        }

        if (parent == null)
            return ArtifactKey.createRoot();

        return parent.key().createChild();
    }

    public ArtifactKey generate(String workflowRunId, AgentType agentType) {
        return generate(workflowRunId, agentType, null);
    }

}
