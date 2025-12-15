package org.zerock.apiserver.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDayDTO {

    private int day;                          // ex) 1일차, 2일차 등
    private List<ActivityDTO> activities;     // 오전 / 오후 일정 리스트

}