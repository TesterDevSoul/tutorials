package com.ceshiren;

import com.ceshiren.util.PageYamlParser;
import com.ceshiren.util.TestCaseYamlParser;
import org.junit.jupiter.api.Test;

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
