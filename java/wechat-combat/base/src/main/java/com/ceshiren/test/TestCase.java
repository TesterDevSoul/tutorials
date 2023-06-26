package com.ceshiren.test;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class TestCase {
    private String method;
    private String desc;
    private List<String> tags;

    private List<String> steps;
}
