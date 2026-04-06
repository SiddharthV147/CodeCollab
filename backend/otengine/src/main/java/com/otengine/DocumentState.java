package com.otengine;

import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;

public class DocumentState {
    StringBuilder document;

    public DocumentState(String content) {
        this.document = new StringBuilder(content);
    }

    public void apply(Operation op) {
        if(op instanceof InsertOperation) {
//            System.out.println("Applying Operation : "+op+" \nto doc :"+document);
//            if(op.index() >= document.length()) {
//                document.append(((InsertOperation) op).word());
//            } else {
//                this.document.insert(op.index(), ((InsertOperation) op).word());
//            }

            document.insert(op.index(), ((InsertOperation) op).word());
        }
    }

    public String getDocument() {
        return document.toString();
    }
}
