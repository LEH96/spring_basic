package com.ll.basic.boundedContext.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {
    @GetMapping("member/login")
    @ResponseBody
    public Map<String, String> tryLogin(String username, String password){
        List<User> list = randomUserMake();
        Map<String, String> result = new LinkedHashMap<>();

        User u = list.stream()
                .filter(e -> e.getName().equals(username))
                .findFirst()
                .orElse(null);

        if(u == null) {
            result.put("resultCode","F-2");
            result.put("msg", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        } else {
            if(u.getPwd().equals(password)){
                result.put("resultCode","S-1");
                result.put("msg", "%s 님 환영합니다.".formatted(username));
            } else {
                result.put("resultCode","F-1");
                result.put("msg", "비밀번호가 일치하지 않습니다.");
            }
        }

        return result;
    }

    private List<User> randomUserMake() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1","1234"));
        userList.add(new User("abc","12345"));
        userList.add(new User("test","12346"));
        userList.add(new User("love","12347"));
        userList.add(new User("like","12348"));
        userList.add(new User("giving","12349"));
        userList.add(new User("thanks","123410"));
        userList.add(new User("hello","123411"));
        userList.add(new User("good","123412"));
        userList.add(new User("peace","123413"));

        return userList;
    }
}
