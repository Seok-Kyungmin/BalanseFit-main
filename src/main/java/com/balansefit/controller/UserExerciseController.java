package com.balansefit.controller;

import com.balansefit.dto.ExerciseDTO;
import com.balansefit.service.IExerciseService;
import com.balansefit.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class UserExerciseController {

    @Resource(name = "ExerciseService")
    private IExerciseService exerciseService;

    /**
     * 게시판 리스트 보여주기
     */
    @GetMapping(value = "user/ExerciseList")
    public String ExerciseList(ModelMap model) throws Exception{

        log.info(this.getClass().getName()+".UExerciseList start!");

        // 운동 리스트 가져오기
        List<ExerciseDTO> eList = exerciseService.getExerciseList();

        if (eList == null) {
            eList = new ArrayList<>();
        }

        // 조회된 결과값 가져오기
        model.addAttribute("eList", eList);

        log.info(this.getClass().getName()+".UExerciseList end!");
        return "/exercise/UExerciseList";
    }

    /**
     * 게시판 상세보기
     */
    @GetMapping(value = "user/ExerciseInfo")
    public String ExerciseInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".UExerciseInfo start!");

        String msg = "";

        try {
            String eSeq = request.getParameter("eSeq"); // 운동 번호
            log.info("eSeq : " + eSeq);

            ExerciseDTO pDTO = new ExerciseDTO();
            pDTO.setExercise_seq(eSeq);

            // 상세정보 가져오기
            ExerciseDTO rDTO = exerciseService.getExerciseInfo(pDTO);

            if (rDTO == null) {
                rDTO = new ExerciseDTO();
            }

            String session_id = CmmUtil.nvl(request.getParameter("SS_USER_ID"));

            log.info("getExerciseInfo success!!");
            log.info(this.getClass().getName()+" rDTO.getUser_id() "+rDTO.getUser_id());
            log.info(this.getClass().getName()+" session_id "+session_id);
            log.info(this.getClass().getName() + " Exercise_met : " +rDTO.getExercise_met());
            log.info(this.getClass().getName() + " Exercise_name : " +rDTO.getExercise_name());
            log.info(this.getClass().getName() + " Exercise_seq : " +rDTO.getExercise_seq());

            // 조회된 리스트 결과값 넣어주기
            model.addAttribute("rDTO", rDTO);
            log.info("rDTO 값은?"+rDTO.getUser_id());

        } catch (Exception e) {

            msg= "실패하였습니다 : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        }finally {
            log.info(this.getClass().getName() + "finally.UExerciseInsert end!");

            // 결과 메시지 전달
            model.addAttribute("msg", msg);

        }

        log.info((String) model.getAttribute("url"));

        return "/exercise/UExerciseInfo";
    }

}
