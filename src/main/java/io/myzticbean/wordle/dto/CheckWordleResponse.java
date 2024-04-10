package io.myzticbean.wordle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CheckWordleResponse {
    private List<String> matchedCharacters;
    private boolean isAllMatch;
}
