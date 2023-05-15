package com.balansefit.service;

import com.balansefit.dto.ExerciseDTO;

import java.util.List;


public interface IExerciseService {

    // 게시판 리스트
    List<ExerciseDTO> getExerciseList() throws Exception;

//    //유저 게시판 리스트
//    List<ExerciseDTO> getUserExerciseList() throws Exception;

    // 게시판 글 등록
    void insertExerciseInfo(ExerciseDTO pDTO) throws Exception;

    // 게시판 상세보기
    ExerciseDTO getExerciseInfo(ExerciseDTO pDTO) throws Exception;

    // 게시판 글 수정
    void updateExerciseInfo(ExerciseDTO pDTO) throws Exception;

    // 게시판 글 삭제
    void deleteExerciseInfo(ExerciseDTO pDTO) throws Exception;
}
