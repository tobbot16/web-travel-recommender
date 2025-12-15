package org.zerock.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class TourApiResponseDTO {

    private Response response;

    @Data
    public static class Response {
        private Body body;
    }

    @Data
    public static class Body {
        private Items items;
    }

    @Data
    public static class Items {
        private List<Item> item;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // 예기치 않은 필드 무시
    public static class Item {
        private String title;
        private String addr1;
        private String contentid;
        private String contenttypeid;
    }
}
