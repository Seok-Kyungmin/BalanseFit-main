package com.balansefit.persistance.mapper;

import com.balansefit.dto.RecipeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IRecipeMapper {

    // 식품 등록
    int insertRecipe(List<RecipeDTO> rList) throws Exception;
}
