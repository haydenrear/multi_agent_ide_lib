package com.hayden.multiagentidelib.model.ui;

public record UiDiffRequest(
        String sessionId,
        String baseRevision,
        Object diff,
        String summary
) {
}
