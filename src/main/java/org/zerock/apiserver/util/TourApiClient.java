package org.zerock.apiserver.util;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.apiserver.dto.TourApiResponseDTO;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class TourApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String serviceKey = "5MZSCGSzc6BJvGzjE7vDLdgkZH3BdQsV1gE83A0HjC4W7c8nJRwsw1vQVSEAlqZ46jnlax0CBtF65OgkVSg9Sg==";
    private final String baseUrl = "https://apis.data.go.kr/B551011/KorService2/areaBasedList2";

    public List<TourApiResponseDTO.Item> getPlaces(int areaCode, int contentTypeId, int numOfRows) {

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("MobileOS", "ETC")
                .queryParam("MobileApp", "AppTest")
                .queryParam("_type", "json")
                .queryParam("areaCode", areaCode)
                .queryParam("contentTypeId", contentTypeId)
                .queryParam("numOfRows", numOfRows)
                .queryParam("arrange", "B") // 🔥 조회수순 정렬
                .queryParam("pageNo", 1)
                .build()
                .toUriString();

        log.info("📡 TOUR API 호출 URL: {}", url);

        try {
            TourApiResponseDTO response = restTemplate.getForObject(url, TourApiResponseDTO.class);

            if (response != null
                    && response.getResponse() != null
                    && response.getResponse().getBody() != null
                    && response.getResponse().getBody().getItems() != null) {

                List<TourApiResponseDTO.Item> placeList = response.getResponse().getBody().getItems().getItem();
                log.info("✅ 가져온 장소 수: {}", placeList.size());
                return placeList;
            }

        } catch (Exception e) {
            log.error("❌ Tour API 호출 중 예외 발생", e);
        }

        return Collections.emptyList();
    }
}


