package com.vnpay.springapigradle.entitites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private int age;
    private int request_id;
}