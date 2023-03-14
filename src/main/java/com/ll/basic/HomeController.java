package com.ll.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private int count;

    public HomeController() {
        this.count = -1;
    }

    @GetMapping("/home/main") //해당 요청이 들어오면(이주소로 들어오면) 아래 함수 수행
    @ResponseBody //아래 메서드를 실행한 후 그 리턴값을 응답으로 출력해줌
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2") //해당 요청이 들어오면(이주소로 들어오면) 아래 함수 수행
    @ResponseBody //아래 메서드를 실행한 후 그 리턴값을 응답으로 출력해줌
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3") //해당 요청이 들어오면(이주소로 들어오면) 아래 함수 수행
    @ResponseBody //아래 메서드를 실행한 후 그 리턴값을 응답으로 출력해줌
    public String showMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase") //해당 요청이 들어오면(이주소로 들어오면) 아래 함수 수행
    @ResponseBody //아래 메서드를 실행한 후 그 리턴값을 응답으로 출력해줌
    public int showIncrease() { //메소드 반환값을 int로 해도 됨, int값이 자동으로 string화 됨
        return ++count;
    }
}
