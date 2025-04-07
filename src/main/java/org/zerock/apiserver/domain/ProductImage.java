package org.zerock.apiserver.domain;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    private String fileName;
    private int ord;

    public void setOrd(int ord){            //대표 ord가 0인걸 출력
        this.ord = ord;
    }


}
