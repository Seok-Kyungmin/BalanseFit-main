package com.balansefit.service;

import com.balansefit.dto.DietDTO;

import java.util.List;

public interface IDietService {

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




}
