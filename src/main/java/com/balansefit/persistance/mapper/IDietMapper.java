package com.balansefit.persistance.mapper;

import com.balansefit.dto.DietDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IDietMapper {

    // 게시판 리스트
    List<DietDTO> getDietList() throws Exception;

    // 게시판 글 등록
    void InsertDietInfo(DietDTO dDTO) throws Exception;

    // 게시판 상세보기
    DietDTO getDietInfo(DietDTO dDTO) throws Exception;

    // 게시판 글 수정
    void updateDietInfo(DietDTO dDTO) throws Exception;

    // 게시판 글 삭제
    void deleteDietInfo(DietDTO dDTO) throws Exception;

    int InsertDietFileInfo(DietDTO dDTO) throws Exception;

    List<DietDTO> foodSearchList(DietDTO dDTO) throws Exception;
}
