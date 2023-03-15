package com.ll.basic.boundedContext.member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@AllArgsConstructor
public class MemberController {
    private Map<String, String> result;

    @GetMapping("member/login")
    @ResponseBody
    public Map<String, String> tryLogin(String username, String password, HttpServletResponse resp){
        result = new LinkedHashMap<>();

        User u = MemberService.checkUser(username);

        if(u == null) {
            result.put("resultCode","F-2");
            result.put("msg", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        } else {
            if(u.getPwd().equals(password)){
                resp.addCookie(new Cookie("username", username));
                result.put("resultCode","S-1");
                result.put("msg", "%s 님 환영합니다.".formatted(username));
            } else {
                result.put("resultCode","F-1");
                result.put("msg", "비밀번호가 일치하지 않습니다.");
            }
        }

        return result;
    }

    @GetMapping("/member/me")
    @ResponseBody
    public Map<String, String> showUserInfo(HttpServletRequest req){
        result = new LinkedHashMap<>();
        String username = Arrays.stream(req.getCookies())
                            .filter(cookie -> cookie.getName().equals("username"))
                            .map(Cookie::getValue)
                            .findFirst()
                            .orElse("");

        if(!username.equals("")){
            result.put("resultCode","S-1");
            result.put("msg","당신의 username(은)는 %s 입니다.".formatted(username));
        } else {
            result.put("resultCode","F-1");
            result.put("msg","로그인 후 이용해주세요.");
        }

        return result;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public Map<String, String> tryLogout(HttpServletRequest req, HttpServletResponse resp) {
        result = new LinkedHashMap<>();

        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("username"))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0); //쿠키의 수명을 0으로 만듬으로써 삭제
                        resp.addCookie(cookie);
                    });
            result.put("resultCode","S-1");
            result.put("msg","로그아웃 되었습니다.");
        }

        return result;
    }
}
