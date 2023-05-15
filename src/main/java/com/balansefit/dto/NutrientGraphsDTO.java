package com.balansefit.dto;

import lombok.Data;

@Data
public class NutrientGraphsDTO {

    private Integer calorie;       // 칼로리
    private Integer carbohydrate;    // 탄수화물
    private String fat;     // 지방
    private Integer sodium;       // 나트륨
    private Integer sugar;        // 당
    private Integer water;        // 물
}
