package io.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class NewFilesMain {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("temp/example.txt");
        Path directory = Path.of("temp/exampleDir");


        System.out.println("File exists: " + Files.exists(file));

        try {
            Files.createFile(file);
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists! " + file.getFileName());
        }

        try {
            Files.createDirectory(directory);
        } catch (FileAlreadyExistsException e) {
            System.out.println("Directory already exists! " + directory.getFileName());
        }

//        Files.delete(file);
//        System.out.println("File deleted: " + file.getFileName());

        System.out.println("Is regular file: " + Files.isRegularFile(file));
        System.out.println("Is directory: " + Files.isDirectory(directory));
        System.out.println("File name: " + file.getFileName());
        System.out.println("File size: " + Files.size(file) + "bytes");
        Path newFile = Path.of("temp/newExample.txt");
        Files.move(file, newFile, REPLACE_EXISTING);
        System.out.println("File moved/renamed: " + newFile.getFileName());

        System.out.println("Last modified: " + Files.getLastModifiedTime(newFile));


        BasicFileAttributes attrs = Files.readAttributes(newFile, BasicFileAttributes.class);
        System.out.println("===== Attributes =====");
        System.out.println("Creation time: " + attrs.creationTime());
        System.out.println("is directory: " + attrs.isDirectory());
        System.out.println("is file: " + attrs.isRegularFile());
        System.out.println("is symbolic: " + attrs.isSymbolicLink());
        System.out.println("size: " + attrs.size());

    }
}
