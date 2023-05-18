package com.cleversonfernando.casodetestesmockito.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    private LocalDateTime timeStamp;
    private Integer Status;
    private String error;
    private String path;
}
