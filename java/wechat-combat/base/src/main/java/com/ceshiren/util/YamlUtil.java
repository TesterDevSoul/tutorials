package com.ceshiren.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlUtil<T> implements  java.io.Serializable{
    private static final long serialVersionUID = 2L; // as of 2.9
    ObjectMapper objectMapper;

    T readValue ;

    public T getReadValue(String pathName){
        try {
            //yaml解析
            if (pathName.endsWith(".yaml") | pathName.endsWith(".yml")){
                objectMapper = new ObjectMapper(new YAMLFactory());
            }
            TypeReference<T> valueTypeRef =
                    new TypeReference<T>() {};
            //解析数据
            readValue = objectMapper.readValue( new File(pathName), valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readValue;
    }

}
