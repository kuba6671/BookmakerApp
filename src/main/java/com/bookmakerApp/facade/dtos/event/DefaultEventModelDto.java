package com.bookmakerApp.facade.dtos.event;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
public class DefaultEventModelDto {
    private Long idEvent;
    private Double odds;
    private Boolean success;
    private boolean finish;
    private Date date;
    private String type;
    private String chosenResult;
}
