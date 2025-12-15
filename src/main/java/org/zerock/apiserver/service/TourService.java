package org.zerock.apiserver.service;

import org.zerock.apiserver.dto.ItineraryDTO;
import org.zerock.apiserver.dto.TravelConditionDTO;

import java.util.List;

public interface TourService {

    List<ItineraryDTO> recommendFromTourAPI(TravelConditionDTO dto);

}
