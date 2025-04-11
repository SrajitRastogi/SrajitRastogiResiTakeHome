package com.Srajit.exercise.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Srajit.exercise.utils.DateParser;

@Service
public class DateService {
    public  List<String> getDates(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream("dates.txt")
        ))) {
            return reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(DateParser::parseDate)
                    .filter(date -> date != null) 
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read dates.txt", e);
        }
    }
}
