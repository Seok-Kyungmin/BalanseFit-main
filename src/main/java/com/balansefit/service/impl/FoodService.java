package com.balansefit.service.impl;

import com.balansefit.dto.FoodDTO;
import com.balansefit.persistance.mapper.IFoodMapper;
import com.balansefit.service.IFoodService;
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
@Service("FoodService")
public class FoodService implements IFoodService {

    public final IFoodMapper foodMapper;

    @Autowired
    public FoodService(IFoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }


    // 음식리스트 저장하기
    @Override
    public int collectFood() throws Exception{

        log.info(this.getClass().getName() + ".collectFoodSong Start!");

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2Ft6%2FQXd94l4rgXGPoO9OLg59%2FUb4IEejesjmsrhca329M%2FxqnGEcePnZVgU3gzdDxfYySj7%2BabAtJkuNnJlG3A%3D%3D"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("desc_kor","UTF-8") + "=" + URLEncoder.encode("바나나칩", "UTF-8")); /*식품이름*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("bgn_year","UTF-8") + "=" + URLEncoder.encode("2017", "UTF-8")); /*구축년도*/
//        urlBuilder.append("&" + URLEncoder.encode("animal_plant","UTF-8") + "=" + URLEncoder.encode("(유)돌코리아", "UTF-8")); /*가공업체*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(xml/json) Default: xml*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        //2. Parser
        JSONParser jsonParser = new JSONParser();

        //3. To Object
        Object obj = jsonParser.parse(sb.toString());

        //4. To JsonObject
        JSONObject jsonObj = (JSONObject) obj;
        log.info("jsonObj : " + jsonObj.toString());

        JSONObject jsonObj_1 = (JSONObject) jsonObj.get("body");
        log.info("jsonObj_2 : " + jsonObj_1.toString());

        JSONArray items = (JSONArray) jsonObj_1.get("items");
        log.info("items : " + items.toString());

        List<FoodDTO> fList = new ArrayList<>();
        for(Object o : items){
            JSONObject item = (JSONObject) o;
            FoodDTO fDTO = new FoodDTO();
            fDTO.setFood_name((String) item.get("DESC_KOR")); // 이름
            fDTO.setFood_calories((String) item.get("NUTR_CONT1")); // 칼로리
            fDTO.setFood_carbohydrate((String) item.get("NUTR_CONT2")); // 탄수화물
            fDTO.setFood_protein((String) item.get("NUTR_CONT3")); // 단백질
            fDTO.setFood_fat((String) item.get("NUTR_CONT4")); // 지방
            fDTO.setFood_sugar((String) item.get("NUTR_CONT5")); // 당
            fDTO.setFood_natrium((String) item.get("NUTR_CONT6")); // 나트륨
            fDTO.setFood_weight((String) item.get("SERVING_WT")); // 1회제공량

            fList.add(fDTO);
        }

        int res = foodMapper.insertFood(fList);

        return res;

    }

    // 수집된 음식 리스트 가져오기
    @Override
    public List<FoodDTO> getFoodList() throws Exception {
        return foodMapper.getFoodList();
    }

    
    @Override
    public void insertFoodInfo(FoodDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".insertFoodInfo Start!");

        foodMapper.insertFoodInfo(pDTO);

    }

    
    @Override
    public FoodDTO getFoodInfo(FoodDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".getFoodInfo Start!");

        return foodMapper.getFoodInfo(pDTO);
    }

    
    @Override
    public void deleteFoodInfo(FoodDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + ".deleteFoodInfo Start!");

        foodMapper.deleteFoodInfo(pDTO);

    }

}
