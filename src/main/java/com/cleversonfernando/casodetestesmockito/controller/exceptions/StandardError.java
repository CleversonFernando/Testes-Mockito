package com.cleversonfernando.casodetestesmockito.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardError {
    private LocalDateTime timeStamp;
    private Integer Status;
    private String error;
    private String path;
}