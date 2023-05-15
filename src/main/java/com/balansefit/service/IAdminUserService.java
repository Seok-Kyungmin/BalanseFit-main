package com.balansefit.service;

import com.balansefit.dto.UserInfoDTO;

import java.util.List;

public interface IAdminUserService {

    // 게시판 리스트
    List<UserInfoDTO> getUserList() throws Exception;

    // 게시판 글 등록
    //void insertUserInfo(UserInfoDTO pDTO) throws Exception;

    // 게시판 상세보기
    UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception;


    // 게시판 글 삭제
    void deleteUserInfo(UserInfoDTO pDTO) throws Exception;
}
