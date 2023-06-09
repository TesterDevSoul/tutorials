package top.testeru.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Key {
    private List<By> by;
    private String a;
}
