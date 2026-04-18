package com.otengine;

import com.otengine.ops.DeleteOperation;
import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;
import com.otengine.transformers.DeleteTransform;
import com.otengine.transformers.InsertTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OTEngine {
    private static final Logger logger = LoggerFactory.getLogger(OTEngine.class);

    public String checkHealth() {
        return "OTEngine running well";
    }

    public Operation transform(List<Operation> previousEdits, Operation newOperation, int latest) {
        if(newOperation instanceof InsertOperation) {
            return InsertTransform.transform(previousEdits, (InsertOperation) newOperation, latest);
        } else {
            return DeleteTransform.transform(previousEdits, (DeleteOperation)newOperation, latest);
        }
    }
}
