package com.sela.sela.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class Customerdto {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String token;
}
