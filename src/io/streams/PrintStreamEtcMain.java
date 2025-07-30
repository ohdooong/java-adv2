package io.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamEtcMain {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("temp/print.txt");

        PrintStream ps = new PrintStream(fos);

        ps.println("Hello");
        Thread.sleep(1000);
        ps.println(10);
        Thread.sleep(1000);
        ps.println(true);
        Thread.sleep(1000);
        ps.printf("Hello %s", "java");
        Thread.sleep(1000);
        ps.close();

    }
}
