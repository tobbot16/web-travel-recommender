package org.zerock.apiserver.service.plan;


import org.zerock.apiserver.dto.plan.PlanRequestDTO;
import org.zerock.apiserver.dto.plan.PlanDayDTO;

import java.util.List;

public interface PlanService {

    // 여행 조건에 따라 추천 일정을 생성
    List<PlanDayDTO> generateRecommendedPlan(PlanRequestDTO requestDTO);

    // 추천된 일정을 저장
    void savePlan(List<PlanDayDTO> planList);

}