package io.myzticbean.wordle.loader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class WordleWordsLoader {

    private final List<String> wordleWords = new ArrayList<>();

    public WordleWordsLoader(@Value("classpath:wordle-words.properties") Resource resource) {
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("words=")) {
                    String[] wordsArray = line.substring("words=".length()).split(",");
                    for (String word : wordsArray) {
                        wordleWords.add(word.trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getWordleWords() {
        return wordleWords;
    }
}

