package org.zerock.apiserver.controller.plan;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.apiserver.dto.plan.PlanDayDTO;
import org.zerock.apiserver.dto.plan.PlanRequestDTO;
import org.zerock.apiserver.service.plan.PlanService;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
@Slf4j

public class PlanRecommendController {

    private final PlanService planService;

    @PostMapping("/recommend")
    public ResponseEntity<List<PlanDayDTO>> recommend(@RequestBody PlanRequestDTO requestDTO) {
        log.info("📥 추천 요청 수신: {}", requestDTO);

        List<PlanDayDTO> result = planService.generateRecommendedPlan(requestDTO);

        return ResponseEntity.ok(result);
    }
}
