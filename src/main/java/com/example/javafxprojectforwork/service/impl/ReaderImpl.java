package com.example.javafxprojectforwork.service.impl;

import com.example.javafxprojectforwork.exception.FileNotFoundException;
import com.example.javafxprojectforwork.service.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReaderImpl implements Reader {
    public String read(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException ex) {
            throw new FileNotFoundException("Can't read data from the file: " + fileName, ex);
        }
    }
}
