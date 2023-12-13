package com.example.parser;

import org.springframework.data.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {

  static List<FileParserResponse> parseTxt(String text) {
    System.out.println(text);
    List<FileParserResponse> fileParserResponseList = new ArrayList<>();
    Pattern pattern = Pattern.compile("(\\d.*\\d)");
    Matcher matcher = pattern.matcher(text);
    FileParserResponse response = new FileParserResponse();
    while (matcher.find()) {
      if (matcher.group().contains(" ")) {
        String[] ar = matcher.group().split(" ");
        response.setPoints(Pair.of(Double.parseDouble(ar[0]), Double.parseDouble(ar[1])));
      } else {
        response.setDensity(Double.parseDouble(matcher.group()));
        fileParserResponseList.add(response);
        response = new FileParserResponse();
      }
    }

    fileParserResponseList.stream()
            .forEach((s) -> System.out.println(s.getDensity() + " and " + s.getPoints()));

    return fileParserResponseList;
  }
}
