package com.balansefit.controller;

import com.balansefit.dto.FoodDTO;
import com.balansefit.service.IFoodService;
import com.balansefit.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class FoodController {

    @Resource(name = "FoodService")
    private IFoodService foodService;

    @GetMapping(value = "admin/FoodList")
    public String FoodList(ModelMap model) throws Exception {

        log.info(this.getClass().getName()+ ".FoodList start!");

        // 식품 리스트 가져오기
        List<FoodDTO> rList = foodService.getFoodList();

        if (rList == null) {
            rList = new ArrayList<>();
        }
        //String food = rList

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        log.info(String.valueOf(rList));

        log.info(this.getClass().getName()+ ".FoodList end!");

        return "/adminFood/Food";
    }

//    @RequestMapping(value = "/food",method = RequestMethod.GET)
//    public get

    // 식품 검색
//    public String getSearch(String searchField, String searchText) {
//
//        try {
//
//            // 식품 리스트 가져오기
//            List<FoodDTO> rList = foodService.getFoodList();
//            String SQL = "SELECT * FROM FOOD WHERE " + searchField.trim();
//
//            if(searchText != null && !searchText.equals("") ){
//                SQL +="LIKE '%+"+searchText.trim()+"%' ORDER BY USER_ID DESC LIMIT 10";
//            }
//            PreparedStatement pstmt=conn.prepareStatement(SQL);
//            rs=pstmt.executeQuery();//select
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * 게시판 글 작성
     */
    @GetMapping(value = "admin/FoodReg")
    public String FoodReg() {

        log.info(this.getClass().getName()+ ".FoodReg start!");
        log.info(this.getClass().getName()+ ".FoodReg end!");

        return "/adminFood/FoodReg";
    }

    /**
     * 게시판 글 등록
     */
    @PostMapping(value = "admin/FoodInsert")
    public String FoodInsert(HttpSession session, HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".FoodInsert start!");

        String msg = "";
        String url = "";

        try{
            // 운동 정보 추가하기 위해 사용되는 from객체의 하위 input 객체 등을 받아오기 위해 사용함
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String food_name = CmmUtil.nvl(request.getParameter("food_name")); // 음식명
            String food_calories = CmmUtil.nvl(request.getParameter("food_calories")); // 칼로리
            String food_carbohydrate = CmmUtil.nvl(request.getParameter("food_carbohydrate")); // 탄수화물
            String food_protein = CmmUtil.nvl(request.getParameter("food_protein")); // 단백질
            String food_fat = CmmUtil.nvl(request.getParameter("food_fat")); // 지방
            String food_sugar = CmmUtil.nvl(request.getParameter("food_sugar")); // 당
            String food_natrium = CmmUtil.nvl(request.getParameter("food_natrium")); // 나트륨
            String food_weight = CmmUtil.nvl(request.getParameter("food_weight")); // 1회제공량


            // 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
            log.info("user_id : " + user_id);
            log.info("food_name : " + food_name);
            log.info("food_calories : " + food_calories);
            log.info("food_carbohydrate : " + food_carbohydrate);
            log.info("food_protein : " + food_protein);
            log.info("food_fat : " + food_fat);
            log.info("food_sugar : " + food_sugar);
            log.info("food_natrium : " + food_natrium);
            log.info("food_weight : " + food_weight);


            FoodDTO pDTO = new FoodDTO();

            pDTO.setUser_id(user_id);
            pDTO.setFood_name(food_name);
            pDTO.setFood_calories(food_calories);
            pDTO.setFood_carbohydrate(food_carbohydrate);
            pDTO.setFood_protein(food_protein);
            pDTO.setFood_fat(food_fat);
            pDTO.setFood_sugar(food_sugar);
            pDTO.setFood_natrium(food_natrium);
            pDTO.setFood_weight(food_weight);

            // 정보 추가하기 위한 비즈니스 로직을 호출
            foodService.insertFoodInfo(pDTO);

            // 저장이 완료되면 사용자에게 보여줄 메시지
            msg = "추가되었습니다!";
            url = "/admin/FoodList";

        } catch (Exception e) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/admin/FoodList";
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".FoodInsert end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }
        log.info((String) model.getAttribute("url"));
        return "/redirect";
    }

    /**
     * 게시판 상세보기
     */
    @GetMapping(value = "admin/FoodInfo")
    public String FoodInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".FoodInfo start!");

        String msg = "";

        try {
            String fSeq = request.getParameter("fSeq");
            log.info("fSeq : " + fSeq);

            FoodDTO pDTO = new FoodDTO();
            pDTO.setFood_seq(Integer.parseInt(fSeq));

            // 상세정보 가져오기
            FoodDTO rDTO = foodService.getFoodInfo(pDTO);

            if (rDTO == null) {
                rDTO = new FoodDTO();
            }

            String session_id = CmmUtil.nvl(request.getParameter("SS_USER_ID"));

            log.info("getFoodInfo success!!");
            log.info(this.getClass().getName()+" rDTO.getUser_id() "+rDTO.getUser_id());
            log.info(this.getClass().getName()+" session_id "+session_id);
            log.info(this.getClass().getName() + " getFood_seq : " +rDTO.getFood_seq());
            log.info(this.getClass().getName() + " getFood_name : " +rDTO.getFood_name());
            log.info(this.getClass().getName() + " getFood_calories : " +rDTO.getFood_calories());
            log.info(this.getClass().getName() + " getFood_carbohydrate : " +rDTO.getFood_carbohydrate());
            log.info(this.getClass().getName() + " getFood_fat : " +rDTO.getFood_fat());
            log.info(this.getClass().getName() + " getFood_protein : " +rDTO.getFood_protein());
            log.info(this.getClass().getName() + " getFood_natrium : " +rDTO.getFood_natrium());
            log.info(this.getClass().getName() + " getFood_sugar : " +rDTO.getFood_sugar());
            log.info(this.getClass().getName() + " getFood_weight : " +rDTO.getFood_weight());

            // 조회된 리스트 결과값 넣어주기
            model.addAttribute("rDTO", rDTO);

        } catch (Exception f) {

            msg= "실패하였습니다 : " + f.getMessage();
            log.info(f.toString());
            f.printStackTrace();

        }finally {

            log.info(this.getClass().getName() + ".FoodInsert end!");

            // 결과 메시지 전달
            model.addAttribute("msg", msg);

        }
        log.info((String) model.getAttribute("url"));

        return "/adminFood/FoodInfo";
    }

    /**
     * 게시판 글 삭제
     */
    @GetMapping(value = "admin/FoodDelete")
    public String FoodDelete(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".FoodDelete start!");

        String msg = "";
        String url = "";

        try {

            String fSeq = CmmUtil.nvl(request.getParameter("fSeq"));

            log.info("fSeq : " + fSeq);

            FoodDTO pDTO = new FoodDTO();

            pDTO.setFood_seq(Integer.parseInt(fSeq));

            //게시글 삭제하기 DB
            foodService.deleteFoodInfo(pDTO);

            msg = "삭제되었습니다";
            url = "/admin/FoodList";

        } catch (Exception f) {
            msg = "실패하였습니다 : " + f.getMessage();
            url = "/admin/FoodList";
            log.info(f.toString());
            f.printStackTrace();

        } finally {
            log.info(this.getClass().getName()+".FoodDelete end!");

            //결과 메시지 전달하기
            model.addAttribute("msg",msg);
            model.addAttribute("url", url);

        }
        log.info((String) model.getAttribute("url"));
        return "/redirect";
    }

}

