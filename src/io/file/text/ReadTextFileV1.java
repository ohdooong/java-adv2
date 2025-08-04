package io.file.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ReadTextFileV1 {
    private static final String PATH = "temp/hello2.txt";

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("writeString: " + writeString);

        Path path = Path.of(PATH);

        // 파일에 쓰기
        Files.writeString(path, writeString, StandardCharsets.UTF_8);
        // 파일에서 읽기
        //String readString = Files.readString(path, StandardCharsets.UTF_8);
        //Files.readAllLines(path).forEach(System.out::println);
        //System.out.println("readString: " + readString);
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        lines.forEach(line -> System.out.println(line));
        lines.close();
    }
}
