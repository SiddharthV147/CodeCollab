package com.otengine.transformers;

import com.otengine.ops.DeleteOperation;
import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;

import java.util.Comparator;
import java.util.List;

public class InsertTransform {

    public static InsertOperation transform(List<Operation> previousEdits, InsertOperation insertOperation, int latest) throws RuntimeException {
        if(previousEdits.isEmpty()) {
            return new InsertOperation(
                    insertOperation.operationId(),
                    insertOperation.documentId(),
                    insertOperation.userId(),
                    insertOperation.revisionId()+1,
                    insertOperation.index(),
                    insertOperation.word()
            );
        }
        previousEdits.sort(Comparator.comparing(Operation::revisionId));
        InsertOperation revised;
        if(previousEdits.getFirst() instanceof InsertOperation) {
            revised = handleInsertInsert((InsertOperation) previousEdits.getFirst(), insertOperation, latest);
        } else {
            revised = handleDeleteInsert((DeleteOperation) previousEdits.getFirst(), insertOperation, latest);
        }
        previousEdits.removeFirst();
        for(Operation op: previousEdits) {
            if(op instanceof InsertOperation) {
                revised = handleInsertInsert((InsertOperation) op, revised, latest);
            } else {
                revised = handleDeleteInsert((DeleteOperation) op, revised, latest);
            }
        }
        return revised;
    }

    public static InsertOperation handleInsertInsert(InsertOperation previous, InsertOperation operation, int latest) {
        InsertOperation newOperation;
        System.out.println("Transforming : "+ operation + " to \n" + previous);
        if(operation.revisionId() < previous.revisionId()) {
            if(operation.index() < previous.index()) {
                newOperation = new InsertOperation(
                        operation.operationId(),
                        operation.documentId(),
                        operation.userId(),
                        previous.revisionId()+1,
                        operation.index(),
                        operation.word()
                );
            } else if(operation.index() > previous.index()) {
                int newIndex = operation.index() + previous.word().length();
                newOperation = new InsertOperation(
                        operation.operationId(),
                        operation.documentId(),
                        operation.userId(),
                        previous.revisionId()+1,
                        newIndex,
                        operation.word()
                );
            } else {
                if(operation.userId() > previous.userId()) {
                    int newIndex = operation.index() + previous.word().length();
                    newOperation = new InsertOperation(
                            operation.operationId(),
                            operation.documentId(),
                            operation.userId(),
                            previous.revisionId()+1,
                            newIndex,
                            operation.word()
                    );
                } else {
                    newOperation = new InsertOperation(
                            operation.operationId(),
                            operation.documentId(),
                            operation.userId(),
                            previous.revisionId()+1,
                            operation.index(),
                            operation.word()
                    );
                }
        }
        } else {
            if(operation.index() >= previous.index()) {
                int newIndex = operation.index() + previous.word().length();
                newOperation = new InsertOperation(
                        operation.operationId(),
                        operation.documentId(),
                        operation.userId(),
                        previous.revisionId()+1,
                        newIndex,
                        operation.word()
                );
            } else {
                newOperation = new InsertOperation(
                        operation.operationId(),
                        operation.documentId(),
                        operation.userId(),
                        previous.revisionId()+1,
                        operation.index(),
                        operation.word()
                );
            }
        }
        return newOperation;
    }

    public static InsertOperation handleDeleteInsert(DeleteOperation previous, InsertOperation operation, int latest) {
        return new InsertOperation(
                operation.operationId(),
                operation.documentId(),
                operation.userId(),
                previous.revisionId()+1,
                operation.index(),
                operation.word()
        );
    }
}
