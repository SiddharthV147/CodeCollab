package com.otengine.transformers;

import com.otengine.ops.DeleteOperation;
import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

public class InsertTransform {
    private static final Logger log = LoggerFactory.getLogger(InsertTransform.class);

    public static InsertOperation transform(List<Operation> previousEdits, InsertOperation insertOperation) throws RuntimeException {

        if(previousEdits.isEmpty()) {
            try {
                return new InsertOperation(
                        insertOperation.documentId(),
                        insertOperation.userId(),
                        insertOperation.revision()+1,
                        insertOperation.index(),
                        insertOperation.word());
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            previousEdits.sort(Comparator.comparing(Operation::revision));
            InsertOperation revised;
            if(previousEdits.getFirst() instanceof InsertOperation) {
                revised = handleInsert((InsertOperation) previousEdits.getFirst(), insertOperation);
            } else {
                revised = handleDelete((DeleteOperation) previousEdits.getFirst(), insertOperation);
            }
            if(previousEdits.size() > 1) {
                for(int i = 1; i < previousEdits.size(); i++) {
                    if(previousEdits.get(i) instanceof InsertOperation) {
                        revised = handleInsert((InsertOperation) previousEdits.get(i), revised);
                    } else {
                        revised = handleDelete((DeleteOperation) previousEdits.get(i), revised);
                    }
                }
            }
            return revised;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static InsertOperation handleInsert(InsertOperation previous, InsertOperation newInsert) {
        try {
            InsertOperation newOperation;
            if(newInsert.revision() == previous.revision()) {
                newOperation = new InsertOperation(
                        newInsert.documentId(),
                        newInsert.userId(),
                        newInsert.revision()+1,
                        newInsert.index(),
                        newInsert.word()
                );
            }
            else {
                if(newInsert.index() < previous.index()) {
                    newOperation = new InsertOperation(
                            newInsert.documentId(),
                            newInsert.userId(),
                            previous.revision()+1,
                            newInsert.index(),
                            newInsert.word()
                    );
                }
                else if (newInsert.index() > previous.index()) {
                    int newIndex = newInsert.index() + previous.word().length();
                    newOperation = new InsertOperation(
                            newInsert.documentId(),
                            newInsert.userId(),
                            previous.revision()+1,
                            newIndex,
                            newInsert.word()
                    );
                }
                else {
                    if(previous.userId() < newInsert.userId()) {
                        int newIndex = newInsert.index() + previous.word().length();
                        newOperation = new InsertOperation(
                                newInsert.documentId(),
                                newInsert.userId(),
                                previous.revision()+1,
                                newIndex,
                                newInsert.word()
                        );
                    }
                    else {
                        newOperation = new InsertOperation(
                                newInsert.documentId(),
                                newInsert.userId(),
                                previous.revision()+1,
                                newInsert.index(),
                                newInsert.word()
                        );
                    }
                }
            }
//            System.out.println("Previous: " + previous);
//            System.out.println("Transformed: " + newOperation);
            return newOperation;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public static InsertOperation handleDelete(DeleteOperation deleteOperation, InsertOperation insertOperation) {
        return new InsertOperation(1,1,1,1,"");
    }
}
