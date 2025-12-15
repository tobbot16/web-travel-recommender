package org.zerock.apiserver.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Itinerary {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userId;

    private String areName;

    private LocalDate startDate;

    private LocalDate endDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "itinerary_places", joinColumns = @JoinColumn(name = "itinerary_id"))
    @Column(name = "place")
    private List<String> placeName;

    private LocalDate createAt;

}
