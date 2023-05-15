package com.balansefit.service.impl;

import com.balansefit.dto.UserInfoDTO;
import com.balansefit.dto.UserWeightDTO;
import com.balansefit.persistance.mapper.IMyPageMapper;
import com.balansefit.service.IMyPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service("MyPageService")
public class MyPageService implements IMyPageService {

    public final IMyPageMapper myPageMapper;

    @Autowired
    public MyPageService(IMyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;
    }


    // 프로필 리스트
    @Override
    public List<UserInfoDTO> getUserInfoList() throws Exception{

        log.info(this.getClass().getName() + "getUserInfoList 시작!");

        return myPageMapper.getUserInfoList();

    }

    // 프로필 글 수정
    @Override
    public void updateUserInfo(UserInfoDTO uDTO) throws Exception {

        log.info(this.getClass().getName() + "updateUserInfo 시작!");

        myPageMapper.updateUserInfo(uDTO);
    }

    // 회원 탈퇴
    @Override
    public void userDelete(UserInfoDTO uDTO) throws Exception {

        log.info(this.getClass().getName() + "userDelete 시작!");

        myPageMapper.userDelete(uDTO);
    }

    @Override
    public List<UserWeightDTO> getWeightList(UserWeightDTO wDTO) throws Exception {
        log.info(this.getClass().getName()+wDTO.getUser_id());
        log.info(this.getClass().getName() + "getWeightList 시작!");
        List<UserWeightDTO> flist = myPageMapper.getWeightList(wDTO);
        if (flist.size()>0){
            log.info("flist.size()"+flist.size());
        }else {
            flist= new ArrayList<>();
        }
        log.info(this.getClass().getName() + "getWeightList 시작!");

        return flist;
    }

    // 이미지 파일 업로드
    public void write(MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, "fileName");

        file.transferTo(saveFile);

//        myPageMapper.write();
    }
}
