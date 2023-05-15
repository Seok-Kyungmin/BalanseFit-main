package com.balansefit.service;

import com.balansefit.dto.UserInfoDTO;
import com.balansefit.dto.UserWeightDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMyPageService {

    // 프로필 리스트
    List<UserInfoDTO> getUserInfoList() throws Exception;

    // 프로필 글 수정
    void updateUserInfo(UserInfoDTO uDTO) throws Exception;

    // 회원 탈퇴
    void userDelete(UserInfoDTO uDTO) throws Exception;

    // 몸무게 리스트 가져오기
    List<UserWeightDTO> getWeightList(UserWeightDTO wDTO) throws Exception;

    // 이미지 파일 업로드
    void write(MultipartFile file) throws Exception;
}
