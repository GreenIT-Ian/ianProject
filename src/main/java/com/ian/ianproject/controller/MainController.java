package com.ian.ianproject.controller;

import com.ian.ianproject.dto.CommonResponse;
import com.ian.ianproject.dto.UserDto;
import com.ian.ianproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/main")
    public void main(Model model){
        model.addAttribute("info", "[B2C플랫폼본부] Ian");
        List<UserDto> users = userService.getUsers();
        model.addAttribute("users", users);
    }

    /**
     * 등록 화면
     */
    @GetMapping("/ian/insert-view")
    public void insertView(){
    }

    /**
     * 등록 처리
     */
    @PostMapping("/ian/insert")
    @ResponseBody
    public CommonResponse insert(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("cellPhone") String cellPhone) throws Exception {
        UserDto userDto = UserDto.builder()
                .email(email)
                .name(name)
                .cellPhone(cellPhone)
                .password("1")
                .build();
        boolean result = userService.insertUser(userDto);

        return CommonResponse.builder()
                .result(result)
                .message(result?"처리했습니다.":"실패했습니다.")
                .build();
    }

    /**
     * 수정
     */
    @PutMapping("/ian/update")
    @ResponseBody
    public CommonResponse update(@RequestParam("id") String id, @RequestParam("cellPhone") String cellPhone){
        UserDto user = UserDto.builder().cellPhone(cellPhone).build();
        boolean result = userService.updateUser(id, user);

        return CommonResponse.builder()
                .result(result)
                .message(result?"처리했습니다.":"실패했습니다.")
                .build();
    }

    /**
     * 삭제
     */
    @DeleteMapping("/ian/delete")
    @ResponseBody
    public CommonResponse delete(Model model, @RequestParam("id") String id) {
        boolean result = userService.deleteUser(id);

        return CommonResponse.builder()
                .result(result)
                .message(result?"처리했습니다.":"실패했습니다.")
                .build();
    }

    /**
     * 계정 중지
     */
    @PutMapping("/ian/disable")
    @ResponseBody
    public CommonResponse disable(Model model, @RequestParam("id") String id) {
        boolean result = userService.disable(id);

        return CommonResponse.builder()
                .result(result)
                .message(result?"처리했습니다.":"실패했습니다.")
                .build();
    }

    /**
     * 상세 화면
     * @param model
     */
    @GetMapping("/ian/view")
    public void view(Model model, @RequestParam String id) {
        UserDto user = null;
        if(!StringUtils.isEmpty(id)) {
            user = userService.getUserById(id);
        }
        model.addAttribute("user", user);
    }


}
