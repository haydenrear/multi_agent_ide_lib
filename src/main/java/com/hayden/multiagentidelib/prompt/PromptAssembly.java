package com.hayden.multiagentidelib.prompt;

import java.util.List;

public class PromptAssembly {

    private final PromptContributorRegistry registry;

    public PromptAssembly(PromptContributorRegistry registry) {
        this.registry = registry;
    }

    public String assemble(String basePrompt, PromptContext context) {
        String prompt = basePrompt == null ? "" : basePrompt;
        if (registry == null) {
            return prompt;
        }
        List<PromptContributor> contributors = getContributors(context);
        if (contributors.isEmpty()) {
            return prompt;
        }
        StringBuilder assembled = new StringBuilder(prompt);
        assembled.append("\n\n--- Tool Prompt Contributions ---\n");
        for (PromptContributor contributor : contributors) {
            if (contributor == null) {
                continue;
            }
            String contribution = null;
            try {
                contribution = contributor.contribute(context);
            } catch (RuntimeException ignored) {
                continue;
            }
            if (contribution == null || contribution.isBlank()) {
                continue;
            }
            assembled.append("\n[")
                    .append(contributor.name())
                    .append("]\n")
                    .append(contribution.trim())
                    .append("\n");
        }
        assembled.append("--- End Tool Prompt Contributions ---");
        return assembled.toString();
    }

    public List<PromptContributor> getContributors(PromptContext context) {
        return registry.getContributors(context);
    }
}
