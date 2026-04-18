package com.otengine.ops;

public record InsertOperation(
        String operationId,
        int documentId,
        int userId,
        int revisionId,
        int index,
        String word
) implements Operation {}
