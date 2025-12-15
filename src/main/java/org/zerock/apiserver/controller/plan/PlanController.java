package org.zerock.apiserver.controller.plan;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.plan.PlanDayDTO;
import org.zerock.apiserver.service.plan.PlanService;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
@Slf4j
public class PlanController {

    private final PlanService planService;

    @PostMapping("/save")
    public ResponseEntity<String> savePlan(@RequestBody List<PlanDayDTO> planList) {
        log.info("📥 일정 저장 요청: {}", planList);

        // 저장 로직 실행 (실제로는 사용자 ID도 필요할 수 있음)
        planService.savePlan(planList);

        return ResponseEntity.ok("저장 완료");
    }
}

