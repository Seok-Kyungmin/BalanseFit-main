package com.balansefit.service.impl;

import com.balansefit.dto.DietDTO;
import com.balansefit.persistance.mapper.IDietMapper;
import com.balansefit.service.IDietService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("DietService")
public class DietService implements IDietService {

    public final IDietMapper dietMapper;

    @Autowired
    public DietService(IDietMapper dietMapper) {
        this.dietMapper = dietMapper;
    }

    @Override
    public List<DietDTO> getDietList() throws Exception {
        return dietMapper.getDietList();
    }
    
    @Override
    public void InsertDietInfo(DietDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".InsertDietInfo start!");
        dietMapper.InsertDietInfo(pDTO);
    }

    @Override
    public DietDTO getDietInfo(DietDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getDietInfo start!");

        return dietMapper.getDietInfo(pDTO);
    }

    @Override
    public void updateDietInfo(DietDTO dDTO) throws Exception {

        log.info(this.getClass().getName() + ".getDietInfo start!");

        dietMapper.updateDietInfo(dDTO);
    }


    @Override
    public void deleteDietInfo(DietDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteDietInfo start1");
        dietMapper.deleteDietInfo(pDTO);
    }


}
