package com.balansefit.service.impl;

import com.balansefit.dto.UserInfoDTO;
import com.balansefit.persistance.mapper.IAdminUserMapper;
import com.balansefit.service.IAdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("AdminUserService")
public class AdminUserService implements IAdminUserService {

    public final IAdminUserMapper adminUserMapper;

    @Autowired
    public AdminUserService(IAdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    // 게시판 리스트
    @Override
    public List<UserInfoDTO> getUserList() throws Exception {

        log.info(this.getClass().getName() + ".getUserList start!");
        return adminUserMapper.getUserList();
    }

    // 게시판 글 등록
//    @Override
//    public void insertUserInfo(UserInfoDTO pDTO) throws Exception {
//
//        log.info(this.getClass().getName() + ".insertUserInfo start!");
//
//        adminUserMapper.insertUserInfo(pDTO);
//
//    }

    // 게시판 상세보기
    @Override
    public UserInfoDTO getUserInfo(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getUserInfo start!");

        return adminUserMapper.getUserInfo(pDTO);
    }

    // 게시판 글 삭제
    @Override
    public void deleteUserInfo(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteUserInfo start!");

        adminUserMapper.deleteUserInfo(pDTO);

    }
}
