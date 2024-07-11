package com.blog.student.StudentData.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class objectNode {
    public static void main(String[] args) throws JsonProcessingException {
        String jsonString = "{\n" +
                "    \"name\": \"ab@\",\n" +
                "    \"dob\": \"19 Jan 2000\",\n" +
                "    \"branch\": \"EC\",\n" +
                "    \"courses\": [\n" +
                "        {\n" +
                "            \"name\": \"Python\",\n" +
                "            \"institute\": \"Udemy\",\n" +
                "            \"topics\": [\n" +
                "                \"O@PS\",\n" +
                "                \"Collections\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Java\",\n" +
                "            \"institute\": \"Udemy\",\n" +
                "            \"topics\": [\n" +
                "                \"O^PS\",\n" +
                "                \"Collections\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        ObjectClass objectClass = mapper.readValue(jsonString, ObjectClass.class);
        System.out.println(objectClass.getCourses());
    }
}
