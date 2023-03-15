package com.ll.basic.boundedContext.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private static final List<User> list = MemberService.create10Users();

    public static User checkUser(String username) {
        return list.stream()
                .filter(e -> e.getName().equals(username))
                .findFirst()
                .orElse(null);
    }

    public static List<User> create10Users() {
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
