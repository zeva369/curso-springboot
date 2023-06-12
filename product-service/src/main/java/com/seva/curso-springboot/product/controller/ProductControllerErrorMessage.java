package com.seva.serviceDemo1.service.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ProductControllerErrorMessage {
    private String code;
    private List<Map<String,String>> messages;

}
