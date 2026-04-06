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
}
