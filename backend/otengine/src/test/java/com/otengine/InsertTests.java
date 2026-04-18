package com.otengine;

import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class InsertTests {
    private final OTEngine otEngine = new OTEngine();

    @Test
    public void test1() {
        DocumentState serverDS = new DocumentState("");
        DocumentState u1DS = new DocumentState("");
        List<Operation> previous = new ArrayList<>();

        InsertOperation op1 = new InsertOperation("1-1", 1,1, 1, 0, "hello");
        Operation rev1 = otEngine.transform(previous, op1, 1);

        serverDS.apply(rev1);
        u1DS.apply(rev1);

        assertEquals(u1DS.getDocument(), serverDS.getDocument());
    }

    @Test
    public void test2() {
        DocumentState serverDS = new DocumentState("hello");
        DocumentState u1DS = new DocumentState("hXXello");
        DocumentState u2DS = new DocumentState("hYYello");

        List<Operation> previous = new ArrayList<>();

        int latestRevision = 1;
        InsertOperation op1 = new InsertOperation("1-1", 1,1, 1, 1, "XX");
        InsertOperation op2 = new InsertOperation("2-1", 1,2, 1, 1, "YY");

        Operation rev1 = otEngine.transform(previous, op1, 1);
        previous.add(rev1);
        Operation rev2 = otEngine.transform(previous, op2, rev1.revisionId());

        u1DS.apply(rev2);
        u2DS.apply(rev1);

        assertEquals(u1DS.getDocument(), u2DS.getDocument());
    }

    @Test
    public void test3() {
        DocumentState serverDS = new DocumentState("hello");
        DocumentState u1DS = new DocumentState("hXXello");
        DocumentState u2DS = new DocumentState("hYYello");

        List<Operation> previous = new ArrayList<>();

        int latestRevision = 1;
        InsertOperation op1 = new InsertOperation("2-1", 1, 2, 1, 1, "YY");
        Operation rev1 = otEngine.transform(previous, op1, 1);
        previous.add(rev1);

        InsertOperation op2 = new InsertOperation("1-1", 1, 1, 1, 1, "XX");
        Operation rev2 = otEngine.transform(previous, op2, rev1.revisionId());

//        System.out.println(rev1);
//        System.out.println(rev2);
        serverDS.apply(rev1);
        serverDS.apply(rev2);
        u2DS.apply(rev2);

        System.out.println(serverDS.getDocument());
        System.out.println(u2DS.getDocument());
//        assertEquals(u1DS.getDocument(), serverDS.getDocument());
    }
}
