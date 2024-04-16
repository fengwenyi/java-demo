package com.fengwenyi.demo.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SourceCodeReader {
    public static void main(String[] args) {
        String sourceDir = ""; // 源代码所在目录
        String outputFilePath = "out.txt"; // 输出文件的路径

        try {
            List<Path> files = listSourceCodeFiles(sourceDir);
            writeToTextFile(files, outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Path> listSourceCodeFiles(String dir) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            return paths.filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".java"))
                        .collect(Collectors.toList());
        }
    }

    private static void writeToTextFile(List<Path> files, String outputFile) throws IOException {
        Path outputPath = Paths.get(outputFile);
        // 创建或清空输出文件
        Files.write(outputPath, new byte[0], StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        for (Path file : files) {
            // String fileName = file.getFileName().toString();
            // if (!fileName.endsWith("ServiceImpl.java")) {
            //     continue;
            // }
            String fileName = file.getFileName().toString();
            if (fileName.endsWith("Test.java")) {
                continue;
            }
            // System.out.println(file.getFileName().toString());
            List<String> lines = Files.readAllLines(file);
            Files.write(outputPath, lines, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            Files.write(outputPath, System.lineSeparator().getBytes(), StandardOpenOption.APPEND);
        }
    }
}
