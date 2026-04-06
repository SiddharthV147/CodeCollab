package com.otengine.ops;

public record DeleteOperation(
        int documentId,
        int userId,
        int revision,
        int index,
        int length
) implements Operation {}
