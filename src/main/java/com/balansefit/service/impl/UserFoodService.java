package com.balansefit.service.impl;

import com.balansefit.dto.FoodDTO;
import com.balansefit.persistance.mapper.IUserFoodMapper;
import com.balansefit.service.IUserFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("UserFoodService")
public class UserFoodService implements IUserFoodService {

    public final IUserFoodMapper userFoodMapper;

    @Autowired
    public UserFoodService(IUserFoodMapper userFoodMapper) {
        this.userFoodMapper = userFoodMapper;
    }
//    private FoodRepository foodRepository;

    // 수집된 음식 리스트 가져오기
    @Override
    public Page<FoodDTO> getUserFoodList(Pageable pageable) throws Exception {
        return userFoodMapper.getUserFoodList(pageable);
    }

//    @Override
//    public Page<FoodDTO> foodSearchList(String searchKeyword, Pageable pageable) {
//
//        return foodRepository.findByTitleContaining(searchKeyword, pageable);
//    }

    @Override
    public FoodDTO getUserFoodInfo(FoodDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getUserFoodInfo Start!");

        return userFoodMapper.getUserFoodInfo(pDTO);
    }

}
