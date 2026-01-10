package com.hayden.multiagentidelib.model.ui;

public record UiEventFeedback(
        String eventId,
        String sessionId,
        String message,
        UiStateSnapshot snapshot
) {
}
