package com.ll.basic.boundedContext.home;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {
    private int count;
    private int countInCookie;
    private int peopleNum;
    List<Person> people;

    public HomeController() {
        this.count = -1;
        this.peopleNum = 0;
        this.countInCookie = 0;
        people = new ArrayList<>();
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

    @GetMapping("/home/cookieIncrease")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req, HttpServletResponse resp) throws IOException { // 리턴되는 int 값은 String 화 되어서 고객(브라우저)에게 전달된다.
        // 고객이 가져온 쿠폰에서 count 쿠폰을 찾고 그 쿠폰의 값을 가져온다.
        if (req.getCookies() != null) {
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(Cookie::getValue)
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        int newCountInCookie = countInCookie + 1;

        // 고객이 가져온 count 쿠폰값에 1을 더한 쿠폰을 만들어서 고객에게 보낸다.
        // 쉽게 말하면 브라우저(고객)에 저장되어 있는 count 쿠폰의 값을 1 증가시킨다.
        // 이렇게 브라우저의 쿠키값을 변경하면 재방문시에 스프링부트가 다시 그 값을 받게 되어 있다.
        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        // 응답 본문
        return newCountInCookie;

    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam(defaultValue = "0") int b) { //@RequestParam는 생략가능, 순서도 상관없음
        return a+b;            //디폴트값 지정시 누락가능 위에경우는 a에만 디폴트 지정해준것
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name, int age) {
        people.add(new Person(++peopleNum, name, age));
        return peopleNum+"번 사람을 추가되었습니다.";
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople() {
        return people;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id){
        for(Person p : people){
            //혹은 boolean removed = people.removeIf(person -> person.getId() == id);
            if(p.getId() == id){
                people.remove(id-1);
                return id+"번 사람이 삭제되었습니다.";
            }
        }
        return id+"번 사람이 존재하지 않습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(int id, String name, int age){
        for(Person p : people){
            if(p.getId() == id){
                p.setId(id);
                p.setName(name);
                p.setAge(age);
                return id+"번 사람이 수정되었습니다.";
            }
        }
//        v2 스트림 이용 방식
//        Person found = people.stream()
//                .filter(person -> person.getId() == id)
//                .findFirst()
//                .orElse(null);
        return id+"번 사람이 존재하지 않습니다.";
    }
}

@AllArgsConstructor // 아래 모든 변수를 가진 생성자 자동생성(롬복 기능)
@Getter
@Setter
@ToString
class Person {
    private int id;
    private String name;
    private int age;
}