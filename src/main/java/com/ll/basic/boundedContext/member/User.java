package com.ll.basic.boundedContext.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
class User {
    private final String name;
    private final String pwd;
}