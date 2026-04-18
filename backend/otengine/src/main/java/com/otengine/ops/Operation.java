package com.otengine.ops;

public interface Operation {
    String operationId();
    int documentId();
    int userId();
    int index();
    int revisionId();
}