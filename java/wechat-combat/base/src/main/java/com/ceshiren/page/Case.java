package com.ceshiren.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@ToString
public class Case {
    private String method;
    private String returnClass;
    private String desc;
    private HashMap<String, String> args;
    private List<Step> steps;
}
