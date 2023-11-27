package com.nhnacademy.annotation;

import java.util.ArrayList;
import java.util.List;

public class Example04 {
    public static void main(String[] args) {
        Machine machine = new Machine<>();
        machine.safe(Integer.valueOf(1), String.valueOf("1.1"));
    }

    static class Machine<T> {
        private final List<T> versions = new ArrayList<>();

        @SafeVarargs
        public final void safe(T... toAdd) {
            for (T version : toAdd) {
                versions.add(version);
            }

            for (T t : versions) {
                print((String) t);
            }
        }

        void print(String t) {
            System.out.println(t);
        }
    }
}