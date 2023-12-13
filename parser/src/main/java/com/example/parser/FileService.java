package com.example.parser;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class FileService {


  public List<FileParserResponse> parseFile(MultipartFile file) throws IllegalAccessException {
    try {
      InputStream stream = file.getInputStream();
      var text = new String(stream.readAllBytes());
      return ParserUtils.parseTxt(text);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      throw new IllegalAccessException();
    }

  }

}
