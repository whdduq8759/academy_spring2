package com.spring.mvc.v2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *  스프링 MVC 프레임워크의 컨트롤러는 웹 브라우저의
 *  요청을 받아 처리하며 응답할 화면(View)을 선택하는 역할을 합니다람쥐~
 *
 *  스프링이 이 클래스를 컨트롤러로 인식하게 하려면
 *  @Controller 이노테이션을 붙여야 합니다.
 * */
@Controller
public class BasicController {
    @GetMapping("/basic/test") // 해당 경로로 URL GET요청이 오면 이 메소드가 처리하겠다
    // 요청 처리 메소드
    public String test() {
        System.out.println("test요청이 들어왔어요!");
        return "/WEB-INF/views/check.jsp"; // 리턴값은 내가 이 요청이 끝난 후 응답할 페이지의 경로
    }



    // 요청 URL: /basic
    // basic.jsp가 열러도록 요청 메소드 작성
    @GetMapping("/basic")
    public String test2() {
        System.out.println("test2 요청이 들어왔어요!");
        return "basic";
    }

    // 요청 파라미터(오쳥시에 브라우저에서 넘어오는 데이터) 받기
    // ex> 로그인을 하면 아이디나 비밀번호가 요청 파라미터!!
    @GetMapping("/basic/join")
    public String join(HttpServletRequest request) {
        System.out.println("/basic/join 요청이 들어옴!");
        String userId = request.getParameter("userId");
        String userPw = request.getParameter("userPw");
        String userName = request.getParameter("userName");
        String userAge = request.getParameter("userAge");
        System.out.println("userId = " + userId);
        System.out.println("userPw = " + userPw);
        System.out.println("userName = " + userName);
        return "";
    }


    @PostMapping("/basic/join2")
    public String join(@RequestParam("userId") String id, String userPw, String userName, int userAge, @RequestParam("hobbys") ArrayList<String> hobbys) {
        System.out.println("/basic/join2 요청이 들어옴!");

        System.out.println("userId = " + id);
        System.out.println("userPw = " + userPw);
        System.out.println("userName = " + userName);
        System.out.println("userAge = " + userAge);
        System.out.println("hobbys = " + hobbys);
        return "";
    }
    @PostMapping("/basic/join3")
    public String join(User user) {
        System.out.println("아이디:" + user.getUserId());
        System.out.println("이름:" + user.getUserName());
        System.out.println("취미:" + user.getHabbys());
        return "";
    }


}

