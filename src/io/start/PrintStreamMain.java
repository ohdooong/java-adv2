package io.start;

import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamMain {
    public static void main(String[] args) throws IOException {
        System.out.println("hello");
        PrintStream printStream = System.out;

        byte[] bytes = "Hello!\n".getBytes();
        printStream.write(bytes);
        printStream.println("Print!");

    }
}
