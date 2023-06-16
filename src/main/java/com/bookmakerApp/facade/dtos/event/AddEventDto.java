package com.bookmakerApp.facade.dtos.event;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddEventDto {
    private Long idSport;
    private Date date;
}
