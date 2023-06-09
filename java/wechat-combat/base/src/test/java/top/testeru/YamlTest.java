package top.testeru;

import org.junit.jupiter.api.Test;
import top.testeru.util.PageYamlParser;
import top.testeru.util.TestCaseYamlParser;

public class YamlTest {
    @Test
    public void load(){
        PageYamlParser yamlParser = new PageYamlParser();
//        yamlParser.loadYaml("pages/app/android/ConcatPage.yaml");
        yamlParser.loadYamlPage("pages/app/android/MainPage.yaml#toConcatPage");
    }

    @Test
    public void loadq(){
        TestCaseYamlParser yamlParser = new TestCaseYamlParser();
//        yamlParser.loadYaml("pages/app/android/ConcatPage.yaml");
        yamlParser.loadYamlTestCase("AddMemberTest.yml");
    }
}
