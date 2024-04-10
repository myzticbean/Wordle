package io.myzticbean.wordle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GenerateNewWordleResponse {
    private String newWordle;
}
