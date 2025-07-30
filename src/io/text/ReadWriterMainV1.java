package io.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.*;

public class ReadWriterMainV1 {
    public static void main(String[] args) throws IOException {
        String writeString = "ABC";

        // 문자 utf 8 인코딩
        byte[] writeStringBytes = writeString.getBytes(UTF_8);
        System.out.println("writeString = " + writeString);
        System.out.println("Arrays.toString() = " + Arrays.toString(writeStringBytes));

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeStringBytes);
        fos.close();

        // 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readAllBytes = fis.readAllBytes();
        fis.close();


        // byte -> String UTF8 디코딩
        String readString = new String(readAllBytes, UTF_8);
        System.out.println("readString = " + readString);
        System.out.println("Arrays.toString() = " + Arrays.toString(readAllBytes));


    }
}
