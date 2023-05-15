package com.balansefit.service;

import com.balansefit.dto.FoodDTO;

import java.util.List;

public interface IFoodService {

    // 음식리스트 저장하기
    int collectFood() throws Exception;

    // 수집된 음식 리스트 가져오기
    List<FoodDTO> getFoodList() throws Exception;

    // 음식 정보 등록
    void insertFoodInfo(FoodDTO pDTO) throws Exception;

    // 음식 정보 상세보기
    FoodDTO getFoodInfo(FoodDTO pDTO) throws Exception;

    // 음식 정보 삭제
    void deleteFoodInfo(FoodDTO pDTO) throws Exception;
}
