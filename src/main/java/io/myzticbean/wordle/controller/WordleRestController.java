package io.myzticbean.wordle.controller;

import io.myzticbean.wordle.dto.CheckWordleResponse;
import io.myzticbean.wordle.dto.GenerateNewWordleResponse;
import io.myzticbean.wordle.service.WordleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class WordleRestController {

    WordleService gameService;

    public WordleRestController(WordleService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/check")
    public ResponseEntity<CheckWordleResponse> checkWord(@RequestBody List<String> userInput) {
        String wordOfTheDay = gameService.getWordOfTheDay(false);
        List<String> wordOfDayLetters = Arrays.stream(wordOfTheDay.toUpperCase().split("")).toList();
        List<String> matchesList = new ArrayList<>();
        boolean isAllMatch = true;
        for (int i = 0; i < userInput.size(); i++) {
            String userInputStr = userInput.get(i);
            String comparingStr = wordOfDayLetters.get(i);
            if (i < wordOfDayLetters.size() && userInputStr.equalsIgnoreCase(comparingStr)) {
                matchesList.add(String.valueOf(wordOfDayLetters.get(i)));
            } else if(wordOfDayLetters.contains(userInputStr)) {
                matchesList.add("-");
                isAllMatch = false;
            } else {
                matchesList.add("_");
                isAllMatch = false;
            }
        }
        return new ResponseEntity<>(new CheckWordleResponse(matchesList, isAllMatch), HttpStatus.OK);
    }

    @GetMapping("/generateNewWordle")
    public ResponseEntity<GenerateNewWordleResponse> generateNewWordleWord() {
        String word = gameService.getWordOfTheDay(true);
        return new ResponseEntity<>(new GenerateNewWordleResponse(word), HttpStatus.OK);
    }

}
