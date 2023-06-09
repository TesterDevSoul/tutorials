package top.testeru.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class TestCases {
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("methods")
    private List<TestCase> methods;

}
