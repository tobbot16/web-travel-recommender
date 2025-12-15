//package org.zerock.apiserver.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Serv ice
//public class TourAPIService {
//
//    private final WebClient webClient = WebClient.create("http://apis.data.go.kr/B551011/KorService1/detailInfo1?serviceKey=8eTpP7%2BLw%2FTcr9X8sAilVyPadq5zTLkFZ6emsIAvC6T7bblLJnFQzMFGvCfjOZwvbg%2FLdp6fRm1aAiOPKWRUmQ%3D%3D&pageNo=1&numOfRows=10&MobileApp=AppTest&MobileOS=ETC&contentId=2987549&contentTypeId=25&_type=json");
//
//
//    public TourResponseDto getTourData(String keyword) {
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path("/KorService1/searchKeyword1")
//                        .queryParam("ServiceKey", "인증키")
//                        .queryParam("MobileOS", "ETC")
//                        .queryParam("MobileApp", "MyApp")
//                        .queryParam("keyword", keyword)
//                        .queryParam("_type", "json")
//                        .build())
//                .retrieve()
//                .bodyToMono(TourResponseDto.class)
//                .block();  // 비동기면 subscribe, 동기면 block
//    }
//}
