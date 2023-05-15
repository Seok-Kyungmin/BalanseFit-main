package com.balansefit.controller;

import com.balansefit.dto.ExerciseDTO;
import com.balansefit.service.IExerciseService;
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
public class ExerciseController {

    @Resource(name = "ExerciseService")
    private IExerciseService exerciseService;

    /**
     * 게시판 리스트 보여주기
     */
    @GetMapping(value = "admin/ExerciseList")
    public String ExerciseList(ModelMap model)
            throws Exception{

        log.info(this.getClass().getName()+".ExerciseList start!");

        // 운동 리스트 가져오기
        List<ExerciseDTO> eList = exerciseService.getExerciseList();

        if (eList == null) {
            log.info(this.getClass().getName()+"안가져옴");
            eList = new ArrayList<>();
        }

        // 조회된 결과값 가져오기
        model.addAttribute("eList", eList);

        log.info(this.getClass().getName()+".ExerciseList end!");

        return "/adminExercise/ExerciseList";
    }

    /**
     * 운동정보 작성 페이지 이동
     */
    @GetMapping(value = "admin/ExerciseReg")
    public String ExerciseReg() {

        log.info(this.getClass().getName()+".ExerciseReg start!");

        log.info(this.getClass().getName()+".ExerciseReg end!");

        return "/adminExercise/ExerciseReg";
    }

    /**
     * 게시판 글 등록
     */
    @PostMapping(value = "admin/ExerciseInsert")
    public String ExerciseInsert(HttpSession session, HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".ExerciseInsert start!");

        String msg = "";
        String url = "";

        try{
            // 운동 정보 추가하기 위해 사용되는 from객체의 하위 input 객체 등을 받아오기 위해 사용함
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String exercise_name = CmmUtil.nvl(request.getParameter("exercise_name")); // 운동명
            String exercise_type = CmmUtil.nvl(request.getParameter("exercise_type")); // 운동명
            String exercise_met = CmmUtil.nvl(request.getParameter("exercise_met")); // 소모 칼로리

            // 반드시, 값을 받았으면, 꼭 로그를 찍어서 값이 제대로 들어오는지 파악해야함 반드시 작성할 것
            log.info("user_id : " + user_id);
            log.info("exercise_name : " + exercise_name);
            log.info("exercise_type : " + exercise_type);
            log.info("exercise_met : " + exercise_met);

            ExerciseDTO pDTO = new ExerciseDTO();

            pDTO.setUser_id(user_id);
            pDTO.setExercise_name(exercise_name);
            pDTO.setExercise_type(exercise_type);
            pDTO.setExercise_met(exercise_met);

            // 정보 추가하기 위한 비즈니스 로직을 호출
            exerciseService.insertExerciseInfo(pDTO);

            // 저장이 완료되면 사용자에게 보여줄 메시지
            msg = "추가되었습니다!";
            url = "/admin/ExerciseList";

        } catch (Exception e) {

            // 저장이 실패되면 사용자에게 보여줄 메시지
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/admin/ExerciseList";
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + "finally.ExerciseInsert end!");

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
    @GetMapping(value = "admin/ExerciseInfo")
    public String ExerciseInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".ExerciseInfo start!");

        String msg = "";

        try {
            String eSeq = request.getParameter("eSeq");
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
            log.info(this.getClass().getName()+" session_id : "+ session_id);
            log.info(this.getClass().getName()+" Exercise_type : "+ rDTO.getExercise_type());
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
            log.info(this.getClass().getName() + "finally.ExerciseInsert end!");

            // 결과 메시지 전달
            model.addAttribute("msg", msg);

        }

        log.info((String) model.getAttribute("url"));
        return "/adminExercise/ExerciseInfo";
    }

    /**
     * 운동정보 수정 페이지 이동
     */
    @GetMapping(value = "admin/ExerciseEdit")
    public String ExerciseEdit(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".ExerciseEdit start!");

        String exercise_seq = CmmUtil.nvl(request.getParameter("eSeq"));
        String user_id = CmmUtil.nvl(request.getParameter("user_id"));

        log.info(this.getClass().getName()+".ExerciseEdit exercise_seq! "+ exercise_seq);
        log.info(this.getClass().getName()+".ExerciseEdit user_id! "+ user_id);

        log.info(this.getClass().getName()+".ExerciseEdit end!");

        model.addAttribute("exercise_seq", exercise_seq);
        model.addAttribute("user_id", user_id);

        return "/adminExercise/ExerciseEditInfo";
    }

    /**
     * 게시판 글 수정
     */
    @PostMapping(value = "admin/ExerciseUpdate")
    public String ExerciseUpdate(HttpSession session, HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".ExerciseUpdate start!");

        String msg = "";
        String url = "";

        try{

            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String exercise_seq = CmmUtil.nvl(request.getParameter("exercise_seq"));
            String exercise_name = CmmUtil.nvl(request.getParameter("exercise_name"));
            String exercise_type = CmmUtil.nvl(request.getParameter("exercise_type"));
            String exercise_met = CmmUtil.nvl(request.getParameter("exercise_met"));

            log.info("user_id : " + user_id);
            log.info("exercise_seq : "+ exercise_seq);
            log.info("exercise_name : " + exercise_name);
            log.info("exercise_type : " + exercise_type);
            log.info("exercise_met : " + exercise_met);

            ExerciseDTO pDTO = new ExerciseDTO();

            pDTO.setUser_id(user_id);
            pDTO.setExercise_seq(exercise_seq);
            pDTO.setExercise_name(exercise_name);
            pDTO.setExercise_type(exercise_type);
            pDTO.setExercise_met(exercise_met);

            //게시글 수정하기 DB
            exerciseService.updateExerciseInfo(pDTO);

            msg = "수정되었습니다.";
            url = "/admin/ExerciseList";

        } catch (Exception e) {
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/admin/ExerciseList";
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName()+"finally .ExerciseUpdate end!");

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
    @GetMapping(value = "admin/ExerciseDelete")
    public String ExerciseDelete(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName()+".ExerciseDelete start!");

        String msg = "";
        String url = "";

        try {

            String eSeq = CmmUtil.nvl(request.getParameter("eSeq"));

            log.info("eSeq : " + eSeq);

            ExerciseDTO pDTO = new ExerciseDTO();

            pDTO.setExercise_seq(eSeq);

            //게시글 삭제하기 DB
            exerciseService.deleteExerciseInfo(pDTO);

            msg = "삭제되었습니다";
            url = "/admin/ExerciseList";

        } catch (Exception e) {
            msg = "실패하였습니다 : " + e.getMessage();
            url = "/admin/ExerciseList";
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName()+".ExerciseDelete end! finally");

            //결과 메시지 전달하기
            model.addAttribute("msg",msg);
            model.addAttribute("url", url);

        }
        log.info((String) model.getAttribute("url"));
        return "/redirect";
    }

}
