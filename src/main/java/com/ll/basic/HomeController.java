package com.ll.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/home/main") //해당 요청이 들어오면(이주소로 들어오면) 아래 함수 수행
    @ResponseBody //아래 메서드를 실행한 후 그 리턴값을 응답으로 출력해줌
    public String showMain() {
        return "Hello";
    }
}