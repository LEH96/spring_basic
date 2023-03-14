package com.ll.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private int count;
    private int peopleNum = 0;
    List<Person> people;

    public HomeController() {
        this.count = -1;
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
}

@AllArgsConstructor // 아래 모든 변수를 가진 생성자 자동생성(롬복 기능)
@Getter
@ToString
class Person {
    private int id;
    private String name;
    private int age;
}
