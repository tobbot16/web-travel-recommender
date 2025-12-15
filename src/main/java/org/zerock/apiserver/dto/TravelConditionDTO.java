package org.zerock.apiserver.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelConditionDTO {
    private int areaCode;             // ex) 1 (서울)
    private int themeContentTypeId;   // ex) 39 (음식점)
    private String startDate;            // yyyy-MM-dd (String으로 받아도 됨)
    private String endDate;

}
