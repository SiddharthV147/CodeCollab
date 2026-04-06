package com.otengine;

import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;
import com.otengine.transformers.InsertTransform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OTEngine {
    private static final Logger logger = LoggerFactory.getLogger(OTEngine.class);

    public String checkHealth() {
        return "OTEngine running well";
    }

    public Operation transform(List<Operation> previousEdits, Operation newOperation) {
        return InsertTransform.transform(previousEdits, (InsertOperation) newOperation);
    }
}
