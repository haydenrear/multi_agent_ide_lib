package com.hayden.multiagentidelib.prompt.contributor;

import com.google.common.collect.Lists;
import com.hayden.multiagentidelib.prompt.PromptContext;
import com.hayden.multiagentidelib.prompt.PromptContributor;
import com.hayden.multiagentidelib.prompt.PromptContributorFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IntellijPromptContributorFactory implements PromptContributorFactory {

    @Override
    public List<PromptContributor> create(PromptContext context) {
        if (context == null || context.currentRequest() == null) {
            return List.of();
        }

        return Lists.newArrayList(GoosePromptContributor.INSTANCE);
    }

    public record GoosePromptContributor() implements PromptContributor {

        public static final GoosePromptContributor INSTANCE = new GoosePromptContributor();

        @Override
        public String name() {
            return this.getClass().getSimpleName();
        }

        @Override
        public boolean include(PromptContext promptContext) {
            return true;
        }

        @Override
        public String contribute(PromptContext context) {
            return template();
        }

        @Override
        public String template() {
            return """
                    When you are using Intellij, you must reference the project from which this worktree was created.
                   
                    Worktree: {{worktree_path}}
                    Project: {{project_path}}
                    
                    This worktree was created from this project, so when you request information, you request it
                    from the associated project repository.
                    """;
        }

        @Override
        public int priority() {
            return 10_000;
        }
    }

}
