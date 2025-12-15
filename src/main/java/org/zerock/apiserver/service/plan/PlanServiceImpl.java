package org.zerock.apiserver.service.plan;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.dto.TourApiResponseDTO;
import org.zerock.apiserver.dto.plan.ActivityDTO;
import org.zerock.apiserver.dto.plan.PlanDayDTO;
import org.zerock.apiserver.dto.plan.PlanRequestDTO;
import org.zerock.apiserver.dto.TourPlaceDTO;
import org.zerock.apiserver.util.TourApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final TourApiClient tourApiClient;

    @Override
    public List<PlanDayDTO> generateRecommendedPlan(PlanRequestDTO requestDTO) {
        log.info("🧠 추천 일정 생성 시작: {}", requestDTO);

        // 장소 데이터 가져오기 (오전/오후 × 일수)
        List<TourApiResponseDTO.Item> placeList = tourApiClient.getPlaces(
                requestDTO.getAreaCode(),
                requestDTO.getContentTypeId(),
                requestDTO.getNumOfDays() * 2
        );

        // 무작위 섞기
        Collections.shuffle(placeList);

        List<PlanDayDTO> result = new ArrayList<>();
        int idx = 0;

        for (int day = 1; day <= requestDTO.getNumOfDays(); day++) {
            List<ActivityDTO> activities = new ArrayList<>();

            if (idx < placeList.size()) {
                TourApiResponseDTO.Item place = placeList.get(idx++);
                activities.add(new ActivityDTO("오전", place.getTitle(), place.getContenttypeid()));
            }

            if (idx < placeList.size()) {
                TourApiResponseDTO.Item place = placeList.get(idx++);
                activities.add(new ActivityDTO( "오후", place.getTitle(), place.getContenttypeid()));
            }

            Collections.shuffle(activities);

            result.add(new PlanDayDTO(day, activities));
        }

        log.info("✅ 추천 일정 결과: {}", result);
        return result;
    }

    @Override
    public void savePlan(List<PlanDayDTO> planList) {
        log.info("💾 일정 저장 요청 수신");

        // TODO: 실제 DB 저장 처리 예정
        planList.forEach(day -> log.info("🗓️ Day {}: {}", day.getDay(), day.getActivities()));
    }
}
