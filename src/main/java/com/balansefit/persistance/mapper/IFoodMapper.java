package com.balansefit.persistance.mapper;

import com.balansefit.dto.FoodDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IFoodMapper {

     // 식품 등록
     int insertFood(List<FoodDTO> fList) throws Exception;

     // 식품 리스트
     List<FoodDTO> getFoodList() throws Exception;

     // 식품 상세보기
     FoodDTO getFoodInfo(FoodDTO pDTO) throws Exception;

     // 식품 추가
     void insertFoodInfo(FoodDTO pDTO) throws Exception;

     // 식품 삭제
     void deleteFoodInfo(FoodDTO pDTO) throws Exception;

}
