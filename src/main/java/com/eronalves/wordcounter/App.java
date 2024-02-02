package com.eronalves.wordcounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      throw new Error("A file is required to continue!");
    }

    File file = Paths.get(args[0]).toFile();

    if (file.isDirectory()) {
      throw new Error("Directory detected. Need to be a file!");
    }

    try (Stream<String> lineStream = Files.lines(Paths.get(file.getPath()))) {
      lineStream
          .map(l -> l.split(" "))
          .flatMap(Arrays::stream)
          .collect(Collectors.groupingBy(Function.identity(),
              Collectors.counting()))
          .forEach((w, c) -> System.out.printf("%s: %d\n", w, c));
    }
  }
}
