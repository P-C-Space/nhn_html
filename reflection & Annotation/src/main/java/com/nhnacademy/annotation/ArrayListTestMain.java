package com.nhnacademy.annotation;

public class ArrayListTestMain {
    public static void main(String[] args) {
        ArrayListTest arrayListTest = new ArrayListTest();
//        arrayListTest.test();

        ArrayListTestProxy arrayListTestProxy = new ArrayListTestProxy(arrayListTest);
        arrayListTestProxy.test();
    }
}
