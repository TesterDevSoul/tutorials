package top.testeru.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Step {
    private String key;
    private Map<String, Object> value;

}
