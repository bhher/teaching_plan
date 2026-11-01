package com.example;

/**
 * JavaBean 예제 — JSP 액션 태그 실습용
 * getter/setter 이름이 프로퍼티(id, name)와 맞아야 함
 */
public class Member {

    private String id;
    private String name;

    public Member() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
