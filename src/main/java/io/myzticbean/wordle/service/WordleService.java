package io.myzticbean.wordle.service;

import io.myzticbean.wordle.loader.WordleWordsLoader;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class WordleService {

    Random random = new Random();
    private final WordleWordsLoader wordleWordsLoader;
    private String wordOfTheDay;

    public WordleService(WordleWordsLoader wordleWordsLoader) {
        this.wordleWordsLoader = wordleWordsLoader;
        this.wordOfTheDay = generateRandomWord();
    }

    private String generateRandomWord() {
        String word = wordleWordsLoader.getWordleWords().get(random.nextInt(wordleWordsLoader.getWordleWords().size()));
        // protection against in case the word fetched is not 5 characters long
        if(word.length() != 5)
            return generateRandomWord();
        return word;
    }

    public String getWordOfTheDay(boolean generateNew) {
        if(generateNew)
            this.wordOfTheDay = generateRandomWord();
        return wordOfTheDay;
    }
}
