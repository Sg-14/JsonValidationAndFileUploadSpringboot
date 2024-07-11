package com.blog.student.StudentData.utils;

import com.blog.student.StudentData.entity.Student;
import com.blog.student.StudentData.payload.StudentDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class readJson {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

//        try{
//            Student student = objectMapper.readValue(
//                    new URL("http://localhost:8080/student/1"),
//                    Student.class
//            );
//            System.out.println(student);
//            System.out.println(student.getCourses().getFirst());
//        }
//        catch(Exception exception){
//            System.out.println(exception);
//        }
        String jsonString = "{\n" +
                "    \"data\": {\n" +
                "        \"student\":{\n" +
                "            \"name\":\"abc\",\n" +
                "            \"dob\":\"19 Jan 2000\",\n" +
                "            \"branch\":\"EC\",\n" +
                "            \"courses\":[\n" +
                "                {\n" +
                "                    \"name\":\"Python\",\n" +
                "                    \"institute\":\"Udemy\",\n" +
                "                    \"topics\":[\n" +
                "                        \"OOPS\",\"Collections\"\n" +
                "                    ]\n" +
                "                },\n" +
                "                  {\n" +
                "                    \"name\":\"Java\",\n" +
                "                    \"institute\":\"Udemy\",\n" +
                "                    \"topics\":[\n" +
                "                        \"OOPS\",\"Collections\"\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String jsonCar = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            Iterator<JsonNode> elements = jsonNode.elements();
//            elements.forEachRemaining(element -> System.out.println(element));

            JsonNode jsonStudent = jsonNode.get("data").get("student");
            JsonNode jsonCourse = jsonStudent.get("courses");
            JsonNode jsonTopic = jsonCourse.get("topics");

            String studentName  = jsonStudent.get("name").asText();
            String studentDob = jsonStudent.get("dob").asText();
            String studentBranch = jsonStudent.get("branch").asText();

            System.out.println(studentBranch);

            System.out.println(
                    "Student Name: "+ studentName + "\n" +
                    "Student Dob: "+ studentDob + "\n" +
                    "Student Branch: "+studentBranch
            );

            List<JsonNode> brand = objectMapper.readValue(jsonCarArray, new TypeReference<List<JsonNode>>(){});
            brand.forEach(name -> System.out.println(name));

            Map<String, Object> mp = objectMapper.readValue(
                    jsonString, new TypeReference<Map<String,Object>>(){});
            System.out.println(mp);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
