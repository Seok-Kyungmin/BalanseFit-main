package com.balansefit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
class BalanseFitApplicationTests {

//    @Test
//    void contextLoads() {
//
//
//        final IRecipeMapper recipeMapper = null;
//
//        try {
//
//            URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/8d7015fa51cb4afe933d/COOKRCP01/json/1/500");
//            System.out.println();
//            System.out.println(url);
//            System.out.println();
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-type", "application/json");
//
//            System.out.println("Response code: " + conn.getResponseCode());
//
//            BufferedReader bf;
//            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//                bf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            } else {
//                bf = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
//            }
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = bf.readLine()) != null) {
//                sb.append(line);
//            }
//            bf.close();
//            conn.disconnect();
//
//            // sb 값 출력하기, 단 값이 너무 길어서 막음
////            System.out.println(sb.toString());
//
//            //2. Parser
//            JSONParser jsonParser = new JSONParser();
//
//            //ERR
//            //3. To Object
//            Object obj = jsonParser.parse(sb.toString());
//
//            //4. To JsonObject
//            JSONObject jsonObj = (JSONObject) obj;
//            log.info("jsonObj : " + jsonObj.toString());
//
//            System.out.println("###\n");
//            JSONObject jsonObj_1 = (JSONObject) jsonObj.get("COOKRCP01");
//            log.info("COOKRCP01 : " + jsonObj_1.toString());
//
//            JSONArray items = (JSONArray) jsonObj_1.get("row");
//            log.info("row : " + items.toString());
//
//            for(int i=0; i<items.size(); i++){
//                JSONObject jobj = (JSONObject) items.get(i);
//                System.out.println(jobj.get("RCP_PARTS_DTLS"));
//                System.out.println(jobj.get("RCP_SEQ"));
//                System.out.println(jobj.get("RCP_NM"));
//                System.out.println(jobj.get("RCP_PAT2"));
//                System.out.println(jobj.get("INFO_WGT"));
//                System.out.println(jobj.get("INFO_ENG"));
//                System.out.println(jobj.get("INFO_CAR"));
//                System.out.println(jobj.get("INFO_PRO"));
//                System.out.println(jobj.get("INFO_FAT"));
//                System.out.println(jobj.get("INFO_NA"));
//                System.out.println(jobj.get("MANUAL01"));
//                System.out.println(jobj.get("MANUAL02"));
//                System.out.println(jobj.get("MANUAL04"));
//                System.out.println(jobj.get("MANUAL05"));
//                System.out.println(jobj.get("MANUAL06"));
//                System.out.println(jobj.get("MANUAL07"));
//                System.out.println(jobj.get("MANUAL08"));
//                System.out.println(jobj.get("MANUAL09"));
//                System.out.println(jobj.get("MANUAL10"));
//                System.out.println(jobj.get("MANUAL11"));
//                System.out.println(jobj.get("MANUAL12"));
//                System.out.println(jobj.get("MANUAL13"));
//                System.out.println(jobj.get("MANUAL14"));
//                System.out.println(jobj.get("MANUAL15"));
//                System.out.println(jobj.get("MANUAL16"));
//                System.out.println(jobj.get("MANUAL17"));
//                System.out.println(jobj.get("MANUAL18"));
//                System.out.println(jobj.get("MANUAL19"));
//                System.out.println(jobj.get("MANUAL20"));
//
//            }
//
//            int res = recipeMapper.insertRecipe(items);
//
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    }
