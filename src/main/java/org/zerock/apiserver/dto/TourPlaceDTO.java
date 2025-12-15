package org.zerock.apiserver.dto;


import lombok.Data;

@Data
public class TourPlaceDTO {

    private String title;            // 장소명
    private String addr1;            // 주소
    private String contentid;        // 고유 콘텐츠 ID
    private String contenttypeid;    // 유형 ID (12: 관광지, 39: 음식점 등)

}