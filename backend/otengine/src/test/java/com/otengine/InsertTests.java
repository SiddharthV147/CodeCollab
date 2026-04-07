package com.otengine;

import com.otengine.ops.InsertOperation;
import com.otengine.ops.Operation;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class InsertTests {
    OTEngine otEngine = new OTEngine();

    @Test
    public void test1() {
        String s = "";
        InsertOperation op = new InsertOperation(1, 1, 1, 0, "hello world");
        List<Operation> prevs = new ArrayList<>();
        DocumentState ds = new DocumentState(s);
        ds.apply(otEngine.transform(prevs, op));
        assertEquals("hello world", ds.getDocument());
    }

    @Test
    public void test2() {
        InsertOperation prev = new InsertOperation(1, 1, 2, 0, "hello");
        List<Operation> ops = new ArrayList<>();
        ops.add(prev);
        Operation operation2 = new InsertOperation(1, 2, 1,0, " world");
        DocumentState ds = new DocumentState("hello");
        ds.apply(otEngine.transform(ops, operation2));
        assertEquals("hello world", ds.getDocument());
    }

    @Test
    public void test3() {
        DocumentState ds = new DocumentState("hello world");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 2, "X");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 2, 1, 8, "YZ");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("heXllo woYZrld", ds.getDocument());
    }

    @Test
    public void test4() {
        DocumentState ds = new DocumentState("hello world");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 9, "ABC");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 2, 1, 3,"K");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("helKlo worABCld", ds.getDocument());
    }

    @Test
    public void test5() {
        DocumentState ds = new DocumentState("hello world");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 5, "!!");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 2, 1, 5, "@");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("hello!!@ world", ds.getDocument());
    }

    @Test
    public void test6() {
        DocumentState ds = new DocumentState("hello world");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 6, "XY");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 2, 1, 6, "ZZZ");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("hello XYZZZworld", ds.getDocument());
    }

    @Test
    public void test7() {
        DocumentState ds = new DocumentState("");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 0, "ABC");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 2, 1, 0, "XYZ");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("ABCXYZ", ds.getDocument());
    }

    @Test
    public void test8() {
        DocumentState ds = new DocumentState("hello");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 5, " world");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 2, 1, 5, "!!!");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("hello world!!!", ds.getDocument());
    }

    @Test
    public void test9() {
        DocumentState ds = new DocumentState("");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 5, "X");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 1, 1, 5, "Y");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("XY", ds.getDocument());
    }

    @Test
    public void test10() {
        DocumentState ds = new DocumentState("");
        List<Operation> prev = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(1, 1, 1, 3, "");
        InsertOperation edited1 = (InsertOperation) otEngine.transform(prev, op1);
        ds.apply(edited1);
        prev.add(edited1);

        InsertOperation op2 = new InsertOperation(1, 2, 1, 3, "X");
        InsertOperation edited2 = (InsertOperation) otEngine.transform(prev, op2);
        ds.apply(edited2);

        assertEquals("X", ds.getDocument());
    }

    @Test
    public void test11() {
        DocumentState ds = new DocumentState("");
        List<Operation> history = new ArrayList<>();
        List<Operation> transformedOps = new ArrayList<>();

        InsertOperation op1 = new InsertOperation(0, 0, 0, 0, "h");
        InsertOperation t1 = (InsertOperation) otEngine.transform(history, op1);
        history.add(t1);
        transformedOps.add(t1);

        InsertOperation op2 = new InsertOperation(1, 1, 1, 1, "e");
        InsertOperation t2 = (InsertOperation) otEngine.transform(history, op2);
        history.add(t2);
        transformedOps.add(t2);

        InsertOperation op3 = new InsertOperation(2, 2, 2, 2, "l");
        InsertOperation t3 = (InsertOperation) otEngine.transform(history, op3);
        history.add(t3);
        transformedOps.add(t3);

        InsertOperation op4 = new InsertOperation(3, 3, 3, 3, "l");
        InsertOperation t4 = (InsertOperation) otEngine.transform(history, op4);
        history.add(t4);
        transformedOps.add(t4);

        InsertOperation op5 = new InsertOperation(4, 4, 4, 4, "o");
        InsertOperation t5 = (InsertOperation) otEngine.transform(history, op5);
        history.add(t5);
        transformedOps.add(t5);

        InsertOperation op6 = new InsertOperation(0, 0, 0, 5, " ");
        InsertOperation t6 = (InsertOperation) otEngine.transform(history, op6);
        history.add(t6);
        transformedOps.add(t6);

        InsertOperation op7 = new InsertOperation(1, 0, 0, 6, "w");
        InsertOperation t7 = (InsertOperation) otEngine.transform(history, op7);
        history.add(t7);
        transformedOps.add(t7);

        InsertOperation op8 = new InsertOperation(2, 0, 0, 7, "o");
        InsertOperation t8 = (InsertOperation) otEngine.transform(history, op8);
        history.add(t8);
        transformedOps.add(t8);

        InsertOperation op9 = new InsertOperation(3, 0, 0, 8, "r");
        InsertOperation t9 = (InsertOperation) otEngine.transform(history, op9);
        history.add(t9);
        transformedOps.add(t9);

        InsertOperation op10 = new InsertOperation(4, 0, 0, 9, "l");
        InsertOperation t10 = (InsertOperation) otEngine.transform(history, op10);
        history.add(t10);
        transformedOps.add(t10);

        InsertOperation op11 = new InsertOperation(5, 0, 0, 10, "d");
        InsertOperation t11 = (InsertOperation) otEngine.transform(history, op11);
        history.add(t11);
        transformedOps.add(t11);

        for (Operation op : transformedOps) {
            ds.apply(op);
        }

        assertEquals("hello world", ds.getDocument());
    }
}
