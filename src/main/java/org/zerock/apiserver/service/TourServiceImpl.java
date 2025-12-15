package org.zerock.apiserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zerock.apiserver.dto.plan.ActivityDTO;
import org.zerock.apiserver.dto.ItineraryDTO;
import org.zerock.apiserver.dto.TravelConditionDTO;
import org.zerock.apiserver.util.TourApiClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourApiClient tourApiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ItineraryDTO> recommendFromTourAPI(TravelConditionDTO dto) {
        String json = tourApiClient.getPlaces(dto.getAreaCode(), dto.getThemeContentTypeId(), 20).toString();

        if (json == null || json.isEmpty()) {
            log.error("Tour API 응답이 null이거나 비어 있음");
            return new ArrayList<>();
        }

        List<ActivityDTO> activities = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode items = root.path("response").path("body").path("items").path("item");

            int index = 0;
            for (JsonNode item : items) {
                String title = item.path("title").asText();
                String contentType = item.path("contenttypeid").asText();

                String time = (index % 2 == 0) ? "오전" : "오후";
                activities.add(new ActivityDTO(time, title, contentType));
                index++;
            }

        } catch (Exception e) {
            log.error("API 응답 파싱 중 오류 발생", e);
            return new ArrayList<>();
        }

        List<ItineraryDTO> result = new ArrayList<>();
        int day = 1;
        for (int i = 0; i < activities.size(); i += 2) {
            List<ActivityDTO> oneDay = activities.subList(i, Math.min(i + 2, activities.size()));
            result.add(new ItineraryDTO(day++, oneDay));
        }

        return result;
    }
}
