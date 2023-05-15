package com.balansefit.persistance.mapper;

import com.balansefit.dto.UserMadeDietDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserMadeDietMapper {

    // 게시판 리스트
    List<UserMadeDietDTO> getUserMadeDietList() throws Exception;

    // 게시판 글 등록
    void InsertUserMadeDietInfo(UserMadeDietDTO dDTO) throws Exception;

    // 게시판 상세보기
    UserMadeDietDTO getUserMadeDietInfo(UserMadeDietDTO dDTO) throws Exception;

    // 게시판 글 삭제
    void deleteUserMadeDietInfo(UserMadeDietDTO dDTO) throws Exception;
}
