package com.lucy.pass.request;


import lombok.Getter;

@Getter
public class AttendeeConfirmRequest {
    private String name;
    private String email;
    private String phone;
}
