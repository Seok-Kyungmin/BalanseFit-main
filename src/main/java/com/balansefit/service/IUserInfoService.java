package com.balansefit.service;

import com.balansefit.dto.UserInfoDTO;

public interface IUserInfoService {

	// 회원정보 불러오기
	//UserInfoDTO getUserInfo(UserInfoDTO uDTO) throws Exception;

	// 로그인
	int getUserLoginCheck(UserInfoDTO tDTO) throws Exception;

	int insertUser(UserInfoDTO tDTO) throws Exception;

	int getUserExists(String user_id) throws Exception;

	int getUserExists2(String user_email) throws Exception;

	// 유저 비밀번호 변경 메서드
	int updateUserPwdInfo(UserInfoDTO pDTO) throws Exception;

	// 유저 아이디 변경 메서드
	UserInfoDTO getFindUserID(UserInfoDTO tDTO) throws Exception;

	// 유저 이메일 찾기 위한 메드
	UserInfoDTO getFindUserInfo(UserInfoDTO tDTO) throws Exception;

	// 마이 페이지 사용자 정보
	UserInfoDTO getUserInfo(String uDTO) throws Exception;

	// 회원 프로필 조회
//	UserInfoDTO getUserDetail(UserInfoDTO uDTO) throws Exception;

//	UserInfoDTO idCheck(String userId) throws Exception;

//	int updatePw(UserInfoDTO tDTO) throws Exception;

//	int sendAuthEmail(String email, String durl) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, InvalidKeyException;
}
