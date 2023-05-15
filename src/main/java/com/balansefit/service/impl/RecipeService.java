package com.balansefit.service.impl;

import com.balansefit.dto.RecipeDTO;
import com.balansefit.persistance.mapper.IRecipeMapper;
import com.balansefit.service.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("RecipeService")
public class RecipeService implements IRecipeService {

    public final IRecipeMapper recipeMapper;

    @Autowired
    public RecipeService(IRecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }

    // 레시피 리스트 저장하기
    @Override
    public int collectRecipe() throws Exception {

        // 메소드 시작하기
        log.info(this.getClass().getName() + ".collectRecipe Start!");

        StringBuilder urlBuilder = new StringBuilder("http://openapi.foodsafetykorea.go.kr/api/8d7015fa51cb4afe933d/COOKRCP01/json/1/500"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=8d7015fa51cb4afe933d"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default: xml*/
        URL url = new URL(urlBuilder.toString());
        System.out.println();
        System.out.println(url);
        System.out.println();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader bf;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            bf = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        bf.close();
        conn.disconnect();

        // sb 값 출력하기, 단 값이 너무 길어서 막음
//            System.out.println(sb.toString());

        //2. Parser
        JSONParser jsonParser = new JSONParser();

        //ERR
        //3. To Object
        Object obj = jsonParser.parse(sb.toString());

        //4. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;
        log.info("jsonObj : " + jsonObj.toString());

        System.out.println("###\n");
        JSONObject jsonObj_1 = (JSONObject) jsonObj.get("COOKRCP01");
        log.info("COOKRCP01 : " + jsonObj_1.toString());

        JSONArray items = (JSONArray) jsonObj_1.get("row");
        log.info("row : " + items.toString());

        List<RecipeDTO> rList = new ArrayList<>();
        for (Object o : items) {
            JSONObject jobj = (JSONObject) o;
            RecipeDTO rDTO = new RecipeDTO();
            rDTO.setRcp_parts_dtls((String) jobj.get("RCP_PARTS_DTLS"));
            rDTO.setRcp_seq((String) jobj.get("RCP_SEQ"));
            rDTO.setRcp_nm((String) jobj.get("RCP_NM"));
            rDTO.setRcp_wgt((String) jobj.get("INFO_WGT"));
            rDTO.setInfo_eng((String) jobj.get("INFO_ENG"));
            rDTO.setInfo_car((String) jobj.get("INFO_CAR"));
            rDTO.setInfo_pro((String) jobj.get("INFO_PRO"));
            rDTO.setInfo_fat((String) jobj.get("INFO_FAT"));
            rDTO.setInfo_na((String) jobj.get("INFO_NA"));
            rDTO.setManual01((String) jobj.get("MANUAL01"));
            rDTO.setManual02((String) jobj.get("MANUAL02"));
            rDTO.setManual03((String) jobj.get("MANUAL03"));
            rDTO.setManual04((String) jobj.get("MANUAL04"));
            rDTO.setManual05((String) jobj.get("MANUAL05"));
            rDTO.setManual06((String) jobj.get("MANUAL06"));
            rDTO.setManual07((String) jobj.get("MANUAL07"));
            rDTO.setManual08((String) jobj.get("MANUAL08"));
            rDTO.setManual09((String) jobj.get("MANUAL09"));
            rDTO.setManual10((String) jobj.get("MANUAL10"));
            rDTO.setManual11((String) jobj.get("MANUAL11"));
            rDTO.setManual12((String) jobj.get("MANUAL12"));
            rDTO.setManual13((String) jobj.get("MANUAL13"));
            rDTO.setManual14((String) jobj.get("MANUAL14"));
            rDTO.setManual15((String) jobj.get("MANUAL15"));
            rDTO.setManual16((String) jobj.get("MANUAL16"));
            rDTO.setManual17((String) jobj.get("MANUAL17"));
            rDTO.setManual18((String) jobj.get("MANUAL18"));
            rDTO.setManual19((String) jobj.get("MANUAL19"));
            rDTO.setManual20((String) jobj.get("MANUAL20"));

            rList.add(rDTO);

        }

        // rList 값을 모두 DB에 삽입
        int res = recipeMapper.insertRecipe(rList);

        log.info(this.getClass().getName() + "userlogin : 레시피 받아오기 끝!");

        return res;

    }
}
