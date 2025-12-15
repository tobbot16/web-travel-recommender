package org.zerock.apiserver.dto.plan;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {
    private String time;        // ex) 오전, 오후
    private String placeName;   // ex) 경복궁
    private String placeType;   // ex) 관광지, 음식점
}
