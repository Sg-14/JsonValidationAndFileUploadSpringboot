package com.blog.student.StudentData.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fasterxml.jackson.databind.node.JsonNodeType.ARRAY;
import static com.fasterxml.jackson.databind.node.JsonNodeType.OBJECT;

public class parseJson {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        Pattern p = Pattern.compile(
                "[^a-zA-Z0-9 ]");
        String jsonString = "{\n" +
                "    \"data\": {\n" +
                "        \"student\":{\n" +
                "            \"name\":\"abc\",\n" +
                "            \"dob\":\"19 Jan 2000\",\n" +
                "            \"branch\":\"EC\",\n" +
                "            \"courses\":[\n" +
                "                {\n" +
                "                    \"name\":\"@ython\",\n" +
                "                    \"institute\":\"Udemy\",\n" +
                "                    \"topics\":[\n" +
                "                        \"OO@S\",\"Collections\"\n" +
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

        try {
            List<String> ans = new ArrayList<>();
            JsonNode node = objectMapper.readTree(jsonString);
            JsonNode studentNode = node.get("data").get("student");
            JsonNode courseNode = studentNode.get("courses");


            if(studentNode.get("name") instanceof Array){
                studentNode.get("name").forEach(
                        name -> {
                            Matcher m = p.matcher(name.asText());
                            boolean res = m.find();
                            System.out.println(res);
                            if(res){
                                ans.add("The student name has special character");
                            }

                        }
                );
            }
            else{
                String name = studentNode.get("name").asText();
                Matcher m = p.matcher(name);
                boolean res = m.find();
                System.out.println(res);
                if(res){
                    ans.add("The student name has special character");
                }
            }

            if(studentNode.get("dob") instanceof Array){
                studentNode.get("dob").forEach(
                        dob -> {
                            Matcher m = p.matcher(dob.toString());
                            boolean res = m.find();

                            if(res){
                                ans.add("The student dob has special character");
                            }

                        }
                );
            }
            else{
                String dob = studentNode.get("dob").asText();
                Matcher m = p.matcher(dob);
                boolean res = m.find();
//                System.out.println(res);
                if(res){
                    ans.add("The student dob has special character");
                }
            }


            if(studentNode.get("branch") instanceof Array){
                studentNode.get("branch").forEach(
                        name -> {
                            Matcher m = p.matcher(name.toString());
                            boolean res = m.find();

                            if(res){
                                ans.add("The student branch has special character");
                            }

                        }
                );
            }
            else{
                String branch = studentNode.get("branch").asText();
                Matcher m = p.matcher(branch);
                boolean res = m.find();
                if(res){
                    ans.add("The student branch has special character");
                }
            }
//            System.out.println(c);
            if(courseNode.getNodeType() == ARRAY){
                courseNode.forEach(
                        coursenode-> {
                            System.out.println(coursenode);
                            if(coursenode.get("name") instanceof Array){
                                coursenode.get("name").forEach(
                                        name -> {
                                            String val = name.get("name").asText();
                                            Matcher m = p.matcher(val);
                                            boolean res = m.find();

                                            if(res){
                                                ans.add("The course name has special character");
                                            }

                                        }
                                );
                            }
                            else{
                                String name = coursenode.get("name").asText();
                                Matcher m = p.matcher(name);
                                boolean res = m.find();
                                System.out.println(name);
                                System.out.println(res);
                                if(res){
                                    ans.add("The course name has special character");
                                }
                            }

                            if(coursenode.get("institute") instanceof Array){
                                coursenode.get("institute").forEach(
                                        name -> {
                                            String val = name.get("institute").asText();
                                            Matcher m = p.matcher(val);
                                            boolean res = m.find();

                                            if(res){
                                                ans.add("The course institute has special character");
                                            }

                                        }
                                );
                            }
                            else{
                                String name = coursenode.get("institute").asText();
                                Matcher m = p.matcher(name);
                                boolean res = m.find();
                                if(res){
                                    ans.add("The course institute has special character");
                                }
                            }
//                            System.out.println(coursenode.get("topics").getNodeType());
                            if(coursenode.get("topics").getNodeType() == ARRAY){
                                int i = 0;
                                coursenode.get("topics").forEach(
                                        name -> {
//                                            System.out.println(name);
                                            String topic = name.asText();
                                            Matcher m = p.matcher(topic);
                                            boolean res = m.find();

                                            if(res){
                                                ans.add("The course topic[" + i +  "] has special character");
                                            }

                                        }
                                );
                            }
                            else{
                                String name = coursenode.get("topics").asText();
                                Matcher m = p.matcher(name);
                                boolean res = m.find();
                                if(!res){
                                    ans.add("The course topics has special character");
                                }
                            }
                        }
                );
            }
            System.out.println(ans);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
