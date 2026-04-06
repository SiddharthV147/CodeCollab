package com.otengine.ops;

public record InsertOperation(
        int documentId,
        int userId,
        int revision,
        int index,
        String word
) implements Operation {}
