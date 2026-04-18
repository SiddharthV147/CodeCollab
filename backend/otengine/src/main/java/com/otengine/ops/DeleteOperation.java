package com.otengine.ops;

public record DeleteOperation(
        String operationId,
        int documentId,
        int userId,
        int revisionId,
        int index,
        int length
) implements Operation {}
