package org.zerock.apiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.apiserver.domain.Itinerary;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {


    List<Itinerary> findByUserId(String userId);

}
