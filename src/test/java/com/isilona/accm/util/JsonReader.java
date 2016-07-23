package com.isilona.accm.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    public static <T> List<T> getJsonData(File jsonFile, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();

        List<T> returnObjects = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, type);
            returnObjects = mapper.readValue(jsonFile, javaType);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return returnObjects;
    }
}
