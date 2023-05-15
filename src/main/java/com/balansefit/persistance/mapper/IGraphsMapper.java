package com.balansefit.persistance.mapper;

import com.balansefit.dto.UserWeightDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IGraphsMapper {

//    // 칼로리 카운트
//    public List<NutrientGraphsDTO> sumCalories(NutrientGraphsDTO nDTO) throws Exception;
//
//    // 탄수화물 카운트
//    public List<NutrientGraphsDTO> sumCarbohydrate(NutrientGraphsDTO nDTO) throws Exception;
//
//    // 지방 카운트
//    public List<NutrientGraphsDTO> sumFat(NutrientGraphsDTO nDTO) throws Exception;
//
//    // 나트륨 카운트
//    public List<NutrientGraphsDTO> sumSodium(NutrientGraphsDTO nDTO) throws Exception;
//
//    // 당 카운트
//    public List<NutrientGraphsDTO> sumSugar(NutrientGraphsDTO nDTO) throws Exception;
//
//    // 물 카운트
//    public List<NutrientGraphsDTO> sumWater(NutrientGraphsDTO nDTO) throws Exception;

    // 몸무게 카운트
    public List<UserWeightDTO> countWeight(UserWeightDTO wDTO) throws Exception;

}
