package org.zerock.apiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.zerock.apiserver.dto.plan.ActivityDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ItineraryDTO {

    private int day;                     // ex) 1일차, 2일차
    private List<ActivityDTO> activities;

}
