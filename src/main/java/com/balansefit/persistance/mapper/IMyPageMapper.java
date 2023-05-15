package com.balansefit.persistance.mapper;

import com.balansefit.dto.UserInfoDTO;
import com.balansefit.dto.UserWeightDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMyPageMapper {

    // 프로필 리스트
    List<UserInfoDTO> getUserInfoList() throws Exception;

    // 프로필 글 수정
    void updateUserInfo(UserInfoDTO uDTO) throws Exception;

    // 회원 탈퇴
    void userDelete(UserInfoDTO uDTO) throws Exception;

    // 일일 회원 몸무게 가져오기
    List<UserWeightDTO> getWeightList(UserWeightDTO wDTO) throws Exception;

}
