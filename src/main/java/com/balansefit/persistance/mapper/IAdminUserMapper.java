package com.balansefit.persistance.mapper;

import com.balansefit.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAdminUserMapper {

    // 게시판 리스트
    List<UserInfoDTO> getUserList() throws Exception;

    // 게시판 글 등록
    //void insertUserInfo(UserInfoDTO pDTO) throws Exception;

    // 게시판 상세보기
    UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception;


    // 게시판 글 삭제
    void deleteUserInfo(UserInfoDTO pDTO) throws Exception;

}
