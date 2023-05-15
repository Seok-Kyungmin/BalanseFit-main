package com.balansefit.service;

import com.balansefit.dto.FoodDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserFoodService {

    // 수집된 음식 리스트 가져오기
    Page<FoodDTO> getUserFoodList(Pageable pageable) throws Exception;
    //Page<FoodDTO> getUserFoodList(Pageable pageable) throws Exception;

//    public Page<FoodDTO> foodSearchList(String searchKeyword, Pageable pageable);

    // 음식 정보 상세보기
    FoodDTO getUserFoodInfo(FoodDTO pDTO) throws Exception;
}
