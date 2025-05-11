package org.example.Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class Serialization {
    public static <T> ArrayList<T> DeSerialize(ArrayList<String> data, Class<T> tClass){
        ArrayList<T> result = new ArrayList<>();

        ArrayList<String> processedResult = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String datum : data) {
            stringBuilder.append(datum.trim());
            if (datum.trim().equals("},")) {
                processedResult.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }
        processedResult.add(stringBuilder.toString());

        ObjectMapper mapper = new ObjectMapper();
        for (String json : processedResult) {
            try {
                T obj = mapper.readValue(json, tClass);
                result.add(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
