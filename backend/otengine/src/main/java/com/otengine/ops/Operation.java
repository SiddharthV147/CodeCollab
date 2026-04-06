package com.otengine.ops;

public interface Operation {
    int documentId();
    int userId();
    int index();
    int revision();
}