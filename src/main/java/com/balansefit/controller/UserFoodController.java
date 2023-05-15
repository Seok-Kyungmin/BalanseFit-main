package com.balansefit.controller;

import com.balansefit.dto.FoodDTO;
import com.balansefit.service.IUserFoodService;
import com.balansefit.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class UserFoodController {

    @Resource(name = "UserFoodService")
    private IUserFoodService userFoodService;

    @GetMapping(value = "user/FoodList")
    public String FoodList(ModelMap model, @PageableDefault(page = 0, size = 10, sort = "food_seq", direction = Sort.Direction.DESC)Pageable pageable) throws Exception {

        log.info(this.getClass().getName()+ ".UFoodList start!");

        // 식품 리스트 가져오기
        List<FoodDTO> rList = userFoodService.getUserFoodList(pageable);

        if (rList == null) {
            rList = new ArrayList<>();
        }
        //Page<FoodDTO> rList = userFoodService.getUserFoodList(PageRequest.of(0, 20));

//        int nowPage = rList.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, rList.getTotalPages());

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);

        log.info(String.valueOf(rList));

        log.info(this.getClass().getName()+ ".UFoodList end!");

        return "/food/UFoodList";
    }

    /**
     * 게시판 상세보기
     */
    @GetMapping(value = "user/FoodInfo")
    public String FoodInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".UFoodInfo start!");

        String msg = "";

        try {
            String fSeq = request.getParameter("fSeq");
            log.info("fSeq : " + fSeq);

            FoodDTO pDTO = new FoodDTO();
            pDTO.setFood_seq(Integer.parseInt(fSeq));

            // 상세정보 가져오기
            FoodDTO rDTO = userFoodService.getUserFoodInfo(pDTO);

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

            log.info(this.getClass().getName() + "finally.UFoodInsert end!");

            // 결과 메시지 전달
            model.addAttribute("msg", msg);

        }
        log.info((String) model.getAttribute("url"));

        return "/food/UFoodInfo";
    }
}
