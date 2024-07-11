package com.blog.student.StudentData.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class parseJson2 {

    static Pattern p = Pattern.compile("[^a-zA-Z0-9 ]");
//    public static Pattern splitPattern = Pattern.compile(REGEX);



    // Convert JSON string to JSONObject


    public static void main(String[] args) {
        String jsonString = "{"
                + "\"name\":\"John-Hopkin\","
                + "\"age\":30,"
                + "\"address\":{"
                +     "\"street\":\"123 Main St\","
                +     "\"city\":\"Anytown\""
                + "},"
                + "\"phoneNumbers\":["
                +     "\"123-*456-7890\","
                +     "\"987-654-3210\""
                + "]"
                + "}";
        JSONObject jsonObject = new JSONObject(jsonString);
        String s = validationCheck(jsonObject);
        System.out.println(s);
    }

    public static String validationCheck(JSONObject jsonObject){
        Set<String> keys = jsonObject.keySet();
        List<String> errorKeys = new ArrayList<>();

        for (String key: keys){
            Object val = jsonObject.get(key);
            if(val instanceof JSONArray){
                JSONArray array = (JSONArray) val;
                for(int i=0;i< array.length();i++){
                    Object o = array.get(i);
                    if(o instanceof JSONObject){
                        collectJsonObjectErrors((JSONObject) o, errorKeys);
                    } else{
                        if(o != null){
                            String value = o.toString();
                            String regex = getRegexByKey(key);
                            if(!Pattern.compile(regex).matcher(value).matches()){
                                errorKeys.add(key);
                            }
                        }
                    }
                }
            } else if (val instanceof JSONObject) {
                collectJsonObjectErrors((JSONObject) val, errorKeys);
            } else{
                if(val != null){
                    String value = val.toString();
                    String regex = getRegexByKey(key);
                    if(!Pattern.compile(regex).matcher(value).matches()){
                        errorKeys.add(key);
                    }
                }
            }
        }
        if(errorKeys.isEmpty()){
            return "";
        }

        return errorKeys.stream().map(key -> capitaliseFirstLetter(key)).collect(Collectors.joining(" "));
    }

    public static void collectJsonObjectErrors(JSONObject jsonObject, List<String> errorKeys){
        Set<String> innerKeys = jsonObject.keySet();

        for(String key: innerKeys){
            Object val = jsonObject.get(key);
            if(val instanceof JSONArray){
                JSONArray array = (JSONArray) val;
                for(int i=0;i< array.length();i++){
                    Object o = array.get(i);
                    if(o instanceof JSONObject){
                        collectJsonObjectErrors((JSONObject) o, errorKeys);
                    } else {
                        if(o != null){
                            String value = o.toString();
                            String regex = getRegexByKey(key);
                            if(!Pattern.compile(regex).matcher(value).matches()){
                                errorKeys.add(key);
                            }
                        }
                    }
                }
            } else if (val instanceof JSONObject) {
                collectJsonObjectErrors((JSONObject) val, errorKeys);
            } else {
                if(val != null){
                    String value = val.toString();
                    String regex = getRegexByKey(key);
                    if(!Pattern.compile(regex).matcher(value).matches()){
                        errorKeys.add(key);
                    }
                }
            }
        }
    }

    public static String getRegexByKey(String key) {
        Map<String, String> regexMap = new HashMap<>();
        regexMap.put("name", "[A-Za-z]+");
        regexMap.put("city", "[A-Za-z]+");
        regexMap.put("emailAddress", "[A-Za-z0-9-@.]+");
        regexMap.put("email", "[A-Za-z0-9-@.]+");
        regexMap.put("deviceEventId", "[A-Za-z0-9-]+");
        regexMap.put("DataSessionId", "[A-Za-z0-9-]+");
        regexMap.put("phoneNumber", "[0-9+]+");
        regexMap.put("emergencyNumber", "[0-9/-+ ]+");
        if (key.contains("date") || key.contains("time")) {
            return "[0-9-T-Z-/:. +\\\\]+";
        }
        if (regexMap.containsKey(key)) {
            return regexMap.get(key);
        }
        return "[A-Z-a-z-0-9-@#$.,&_()+/\\\\ ]+";
    }

    public static String capitaliseFirstLetter(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
