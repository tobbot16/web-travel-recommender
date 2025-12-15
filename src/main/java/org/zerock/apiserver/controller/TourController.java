package org.zerock.apiserver.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zerock.apiserver.dto.ItineraryDTO;
import org.zerock.apiserver.dto.TravelConditionDTO;
import org.zerock.apiserver.service.TourService;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
@RequiredArgsConstructor
@Slf4j
public class TourController {

    private final TourService tourService;

//     일정 추천 요청: POST /api/plan/recommend
//    @PostMapping("/recommend")
//    public List<ItineraryDTO> recommend(@RequestBody TravelConditionDTO travelConditionDTO) {
//
//        log.info("추천 요청 수신: {}", travelConditionDTO);
//
//        List<ItineraryDTO> result = tourService.recommendFromTourAPI(travelConditionDTO);
//
//        log.info("추천 결과 반환: {}일차 일정", result.size());
//
//        return result;
//    }
}
