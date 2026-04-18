package com.otengine.transformers;

import com.otengine.OTEngine;
import com.otengine.ops.DeleteOperation;
import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;

import java.util.Comparator;
import java.util.List;

public class DeleteTransform {
    public static DeleteOperation transform(List<Operation> previous, DeleteOperation operation, int latest) {
        return new DeleteOperation(
                operation.operationId(),
                operation.documentId(),
                operation.userId(),
                operation.revisionId()+1,
                operation.index(),
                operation.length()
        );
    }
}
