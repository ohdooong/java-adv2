package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");

        byte[] input = {65, 66, 67, 68,};


        fos.write(input);
        fos.close();


        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] buffer = new byte[input.length];
        int readCount = fis.read(buffer);
        System.out.println("readCount = " + readCount);
        System.out.println(Arrays.toString(buffer));
        fis.close();

    }
}
