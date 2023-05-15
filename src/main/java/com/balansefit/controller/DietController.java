package com.balansefit.controller;

import com.balansefit.dto.DietDTO;
import com.balansefit.service.IDietService;
import com.balansefit.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class DietController {

    @Resource(name = "DietService")
    private IDietService dietService;

    /**
     * 식단 리스트 보여주기
     */
    @GetMapping(value = "admin/DietList")
    public String DietList(ModelMap model) throws Exception{

        log.info(this.getClass().getName()+".DietList start!");

        // 식단 리스트 가져오기
        List<DietDTO> dList = dietService.getDietList();

        if (dList == null) {
            dList = new ArrayList<>();
        }

        // 조회된 결과값 가져오기
        model.addAttribute("dList", dList);

        log.info(this.getClass().getName()+".DietList end!");

        return "/adminDiet/Diet";

    }

    /**
     * 식단정보 작성 페이지 이동
     */
    @GetMapping(value = "admin/DietReg")
    public String DietReg() {
        log.info(this.getClass().getName()+".DietReg start!");
        log.info(this.getClass().getName()+".DietReg end!");

        return "/adminDiet/DietReg";
    }

    /**
     * 식단 추가
     */
    @PostMapping(value = "admin/DietInsert")
    public String DietInsert(HttpSession session, HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".DietInsert start!");

        String msg = "";
        String url = "";

        try{
            // 식단 추가하기 위해 사용되는 from객체의 하위 input 객체 등을 받아오기 위해 사용함
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String diet_name = CmmUtil.nvl(request.getParameter("diet_name")); // 식단 이름
            String food_m_1 = CmmUtil.nvl(request.getParameter("food_m_1"));  // 아침1
            String food_mc_1 = CmmUtil.nvl(request.getParameter("food_mc_1"));  // 아침 식품1 수량
            String food_m_2 = CmmUtil.nvl(request.getParameter("food_m_2"));
            String food_mc_2 = CmmUtil.nvl(request.getParameter("food_mc_2"));
            String food_m_3 = CmmUtil.nvl(request.getParameter("food_m_3"));
            String food_mc_3 = CmmUtil.nvl(request.getParameter("food_mc_3"));
            String food_a_1 = CmmUtil.nvl(request.getParameter("food_a_1"));        // 점심1
            String food_ac_1 = CmmUtil.nvl(request.getParameter("food_ac_1"));      // 점심1 수량
            String food_a_2 = CmmUtil.nvl(request.getParameter("food_a_2"));
            String food_ac_2 = CmmUtil.nvl(request.getParameter("food_ac_2"));
            String food_a_3 = CmmUtil.nvl(request.getParameter("food_a_3"));
            String food_ac_3 = CmmUtil.nvl(request.getParameter("food_ac_3"));
            String food_d_1 = CmmUtil.nvl(request.getParameter("food_d_1"));        // 저녁1
            String food_dc_1 = CmmUtil.nvl(request.getParameter("food_dc_1"));        // 저녁1 수량
            String food_d_2 = CmmUtil.nvl(request.getParameter("food_d_2"));
            String food_dc_2 = CmmUtil.nvl(request.getParameter("food_dc_2"));
            String food_d_3 = CmmUtil.nvl(request.getParameter("food_d_3"));
            String food_dc_3 = CmmUtil.nvl(request.getParameter("food_dc_3"));
            String diet_calories = CmmUtil.nvl(request.getParameter("diet_calories")); // 총칼로리

            // 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
            log.info("user_id : " + user_id);
            log.info("diet_name : " + diet_name);
            log.info("food_m_1 : " + food_m_1);
            log.info("food_mc_1 : " + food_mc_1);
            log.info("food_m_2 : " + food_m_2);
            log.info("food_mc_2 : " + food_mc_2);
            log.info("food_m_3 : " + food_m_3);
            log.info("food_mc_3 : " + food_mc_3);
            log.info("food_a_1 : " + food_a_1);
            log.info("food_ac_1 : " + food_ac_1);
            log.info("food_a_2 : " + food_a_2);
            log.info("food_ac_2 : " + food_ac_2);
            log.info("food_a_3 : " + food_a_3);
            log.info("food_ac_3 : " + food_ac_3);
            log.info("food_d_1 : " + food_d_1);
            log.info("food_dc_1 : " + food_dc_1);
            log.info("food_d_2 : " + food_d_2);
            log.info("food_dc_2 : " + food_dc_2);
            log.info("food_d_3 : " + food_d_3);
            log.info("food_dc_3 : " + food_dc_3);
            log.info("diet_calories : " + diet_calories);

            DietDTO pDTO = new DietDTO();

            pDTO.setUser_id(user_id);
            pDTO.setDiet_name(diet_name);
            pDTO.setFood_m_1(food_m_1);
            pDTO.setFood_mc_1(food_mc_1);
            pDTO.setFood_m_2(food_m_2);
            pDTO.setFood_mc_2(food_mc_2);
            pDTO.setFood_m_3(food_m_3);
            pDTO.setFood_mc_3(food_mc_3);
            pDTO.setFood_a_1(food_a_1);
            pDTO.setFood_ac_1(food_ac_1);
            pDTO.setFood_a_2(food_a_2);
            pDTO.setFood_ac_2(food_ac_2);
            pDTO.setFood_a_3(food_a_3);
            pDTO.setFood_ac_3(food_ac_3);
            pDTO.setFood_d_1(food_d_1);
            pDTO.setFood_dc_1(food_dc_1);
            pDTO.setFood_d_2(food_d_2);
            pDTO.setFood_dc_2(food_dc_2);
            pDTO.setFood_d_3(food_d_3);
            pDTO.setFood_dc_3(food_dc_3);
            pDTO.setDiet_calories(diet_calories);

            // 정보 추가하기 위한 비즈니스 로직을 호출
            dietService.InsertDietInfo(pDTO);

            msg = "추가되었습니다!";
            url = "/admin/DietList";

        } catch (Exception d) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다 : " + d.getMessage();
            url = "/admin/DietList";
            log.info(d.toString());
            d.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + ".DietInsert end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }
        return "/redirect";
    }

    /**
     * 게시판 상세보기
     */
    @GetMapping(value = "admin/DietInfo")
    public String DietInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".DietInfo start!");

        String msg = "";

        try {
            String dSeq = request.getParameter("dSeq"); // 식단 번호
            log.info("dSeq : " + dSeq);

            DietDTO pDTO = new DietDTO();
            pDTO.setDiet_seq(dSeq);

            // 식단 상세정보 가져오기
            DietDTO rDTO  = dietService.getDietInfo(pDTO);

            if (rDTO == null) {
                rDTO = new DietDTO();

            }

            String session_id = CmmUtil.nvl(request.getParameter("SS_USER_ID"));

            log.info("getDietInfo success!!!");
            log.info(this.getClass().getName()+" rDTO.getUser_id() "+rDTO.getUser_id());
            log.info(this.getClass().getName()+" session_id "+session_id);
            log.info(this.getClass().getName()+" getDiet_name "+rDTO.getDiet_name());
            log.info(this.getClass().getName()+" getDiet_calories "+rDTO.getDiet_calories());
            log.info(this.getClass().getName()+" getFood_m_1 "+rDTO.getFood_m_1());
            log.info(this.getClass().getName()+" getFood_a_1 "+rDTO.getFood_a_1());
            log.info(this.getClass().getName()+" getFood_d_1 "+rDTO.getFood_d_1());

            // 조회된 리스트 결과값 넣어주기
            model.addAttribute("rDTO", rDTO);
            log.info("rDTO 값은?"+rDTO.getUser_id());

        } catch (Exception d) {
            msg = "실패하였습니다. :" + d.getMessage();
            log.info(d.toString());
            d.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + "finally.DietInsert end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);

        }

        log.info((String) model.getAttribute("url"));
        return "/adminDiet/DietInfo";
    }

    /**
     * 식단정보 수정 페이지 이동
     */
    @GetMapping(value = "admin/DietEdit")
    public String DietEdit(HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName()+".DietEdit start!");

        String diet_seq = CmmUtil.nvl(request.getParameter("dSeq"));
        String user_id = CmmUtil.nvl(request.getParameter("user_id"));

        log.info(this.getClass().getName()+".DietEdit diet_seq! "+diet_seq);
        log.info(this.getClass().getName()+".DietEdit user_id! "+user_id);

        log.info(this.getClass().getName()+".DietEdit end!");

        model.addAttribute("diet_seq", diet_seq);
        model.addAttribute("user_id", user_id);

        return "/adminDiet/DietEditInfo";
    }

    @RequestMapping(value = "updateDietInfo")
    public String DietUpdate(HttpSession session, HttpServletRequest request, ModelMap model) throws Exception{

        log.info(this.getClass().getName()+ "DietUpdate 시작!!");

        String msg = "";
        String url = "";

        try{

            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String diet_name = CmmUtil.nvl(request.getParameter("diet_name")); // 식단 이름
            String food_m_1 = CmmUtil.nvl(request.getParameter("food_m_1"));  // 아침1
            String food_mc_1 = CmmUtil.nvl(request.getParameter("food_mc_1"));  // 아침 식품1 수량
            String food_m_2 = CmmUtil.nvl(request.getParameter("food_m_2"));
            String food_mc_2 = CmmUtil.nvl(request.getParameter("food_mc_2"));
            String food_m_3 = CmmUtil.nvl(request.getParameter("food_m_3"));
            String food_mc_3 = CmmUtil.nvl(request.getParameter("food_mc_3"));
            String food_a_1 = CmmUtil.nvl(request.getParameter("food_a_1"));        // 점심1
            String food_ac_1 = CmmUtil.nvl(request.getParameter("food_ac_1"));      // 점심1 수량
            String food_a_2 = CmmUtil.nvl(request.getParameter("food_a_2"));
            String food_ac_2 = CmmUtil.nvl(request.getParameter("food_ac_2"));
            String food_a_3 = CmmUtil.nvl(request.getParameter("food_a_3"));
            String food_ac_3 = CmmUtil.nvl(request.getParameter("food_ac_3"));
            String food_d_1 = CmmUtil.nvl(request.getParameter("food_d_1"));        // 저녁1
            String food_dc_1 = CmmUtil.nvl(request.getParameter("food_dc_1"));        // 저녁1 수량
            String food_d_2 = CmmUtil.nvl(request.getParameter("food_d_2"));
            String food_dc_2 = CmmUtil.nvl(request.getParameter("food_dc_2"));
            String food_d_3 = CmmUtil.nvl(request.getParameter("food_d_3"));
            String food_dc_3 = CmmUtil.nvl(request.getParameter("food_dc_3"));
            String diet_calories = CmmUtil.nvl(request.getParameter("diet_calories")); // 총칼로리

            // 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
            log.info("user_id : " + user_id);
            log.info("diet_name : " + diet_name);
            log.info("food_m_1 : " + food_m_1);
            log.info("food_mc_1 : " + food_mc_1);
            log.info("food_m_2 : " + food_m_2);
            log.info("food_mc_2 : " + food_mc_2);
            log.info("food_m_3 : " + food_m_3);
            log.info("food_mc_3 : " + food_mc_3);
            log.info("food_a_1 : " + food_a_1);
            log.info("food_ac_1 : " + food_ac_1);
            log.info("food_a_2 : " + food_a_2);
            log.info("food_ac_2 : " + food_ac_2);
            log.info("food_a_3 : " + food_a_3);
            log.info("food_ac_3 : " + food_ac_3);
            log.info("food_d_1 : " + food_d_1);
            log.info("food_dc_1 : " + food_dc_1);
            log.info("food_d_2 : " + food_d_2);
            log.info("food_dc_2 : " + food_dc_2);
            log.info("food_d_3 : " + food_d_3);
            log.info("food_dc_3 : " + food_dc_3);
            log.info("diet_calories : " + diet_calories);

            DietDTO pDTO = new DietDTO();

            pDTO.setUser_id(user_id);
            pDTO.setDiet_name(diet_name);
            pDTO.setFood_m_1(food_m_1);
            pDTO.setFood_mc_1(food_mc_1);
            pDTO.setFood_m_2(food_m_2);
            pDTO.setFood_mc_2(food_mc_2);
            pDTO.setFood_m_3(food_m_3);
            pDTO.setFood_mc_3(food_mc_3);
            pDTO.setFood_a_1(food_a_1);
            pDTO.setFood_ac_1(food_ac_1);
            pDTO.setFood_a_2(food_a_2);
            pDTO.setFood_ac_2(food_ac_2);
            pDTO.setFood_a_3(food_a_3);
            pDTO.setFood_ac_3(food_ac_3);
            pDTO.setFood_d_1(food_d_1);
            pDTO.setFood_dc_1(food_dc_1);
            pDTO.setFood_d_2(food_d_2);
            pDTO.setFood_dc_2(food_dc_2);
            pDTO.setFood_d_3(food_d_3);
            pDTO.setFood_dc_3(food_dc_3);
            pDTO.setDiet_calories(diet_calories);

            dietService.updateDietInfo(pDTO);

            msg = "수정되었습니다.";
            url = "/admin/DietList";

        } catch (Exception e) {
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/admin/DietList";
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName()+"finally .DietUpdate end!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

        }
        log.info((String) model.getAttribute("url"));
        return "/redirect";
    }

    /**
     * 게시판 글 삭제
     */
    @GetMapping(value = "admin/DietDelete")
    public String DietDelete(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".DietDelete start!");

        String msg = "";
        String url = "";

        try {
            String dSeq = CmmUtil.nvl(request.getParameter("dSeq"));

            log.info("dSeq : " + dSeq);

            DietDTO pDTO = new DietDTO();

            pDTO.setDiet_seq(dSeq);

            //게시글 삭제하기 DB
            dietService.deleteDietInfo(pDTO);

            msg = "삭제되었습니다";
            url = "/admin/DietList";

        } catch (Exception d) {
            msg = "실패하였습니다 : " + d.getMessage();
            url = "/admin/DietList";
            log.info(d.toString());
            d.printStackTrace();

        } finally {
            log.info(this.getClass().getName()+".DietDelete end!");

            //결과 메시지 전달하기
            model.addAttribute("msg",msg);
            model.addAttribute("url", url);

        }
        return "/redirect";
    }



}
