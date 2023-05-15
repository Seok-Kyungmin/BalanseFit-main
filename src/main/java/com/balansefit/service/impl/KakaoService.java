package com.balansefit.service.impl;

import com.balansefit.service.IKakaoService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("KakaoSerivce")
public class KakaoService implements IKakaoService {
    @Override
    public String getAuthcode() throws Exception {
        return null;
    }

    @Override
    public String getAccessToken(String code) throws Exception {
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String accessToken) throws Exception {
        return null;
    }

    @Override
    public int kakaoLogOut(String accessToken) throws Exception {
        return 0;
    }
}
