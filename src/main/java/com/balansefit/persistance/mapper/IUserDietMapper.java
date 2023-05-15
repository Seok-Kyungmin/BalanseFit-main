package com.balansefit.persistance.mapper;

import com.balansefit.dto.DietDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDietMapper {

    // 게시판 리스트
    List<DietDTO> getUserDietList() throws Exception;

    // 게시판 글 등록
//    void insertUserDietInfo(UserDietDTO dDTO) throws Exception;

    // 게시판 상세보기
    DietDTO getUserDietInfo(DietDTO dDTO) throws Exception;

    // 게시판 글 삭제
//    void deleteUserDietInfo(UserDietDTO dDTO) throws Exception;


}
