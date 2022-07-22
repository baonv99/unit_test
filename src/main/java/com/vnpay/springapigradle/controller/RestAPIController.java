package com.vnpay.springapigradle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vnpay.springapigradle.dao.StudentDao;
import com.vnpay.springapigradle.entitites.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestAPIController {
    private final Logger logger = LoggerFactory.getLogger("request_api");
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private StudentDao studentDao;

    @RequestMapping(value =  "/", method = RequestMethod.GET)
    @ResponseBody
    public String welcome() {
        System.out.println("Xin chao");
        return "Welcome to API Example.";
    }

    // GET
    @RequestMapping(value = "/student/{id}/{name}/{request_id}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public Student getStudent(@PathVariable("id") String id, @PathVariable("name") String name, @PathVariable Integer request_id) {
        try {
            logger.info("Request ID: {} Path /student/ :Params id = {}, name = {}", request_id, id, name);
            Student stdGetID = studentDao.getStudent(id);
            String jsonGetStudent = ow.writeValueAsString(stdGetID);
            logger.info("Response: {} Path /student/ :Data {}", request_id, jsonGetStudent);
            return stdGetID;
        } catch (Exception ex){
            logger.error("Request ID: {} Method GET have Exception: {}", request_id, ex.getMessage());
            return null;
        }
    }

    // POST
    @RequestMapping(value = "/student",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public Student createStudent(@RequestBody Student std) {
        try {
            String jsonCreateStudent = ow.writeValueAsString(std);
            logger.info("Request ID: {} Path /student -  Method: POST - Params:  = {}", std.getRequest_id(), jsonCreateStudent);

            Student stdCreate = studentDao.createStudent(std);
            String jsonResponseStudent = ow.writeValueAsString(stdCreate);
            logger.info("Response: {} Path /student -  Method POST - Data: = {}", stdCreate.getRequest_id(), jsonResponseStudent);
            return stdCreate;
        } catch (Exception ex){
            logger.error("Request ID: {} Method POST have Exception: {}", std.getRequest_id(), ex.getMessage());
            return null;
        }
    }

    // PUT
    @RequestMapping(value = "/student",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseBody
    public Student updateStudent(@RequestBody Student std) {
        try {
            String jsonUpdateStudent = ow.writeValueAsString(std);
            logger.info("Request ID: {} Path /student - Method: PUT - Params: {}", std.getRequest_id(), jsonUpdateStudent);
            Student stdUpdate = studentDao.updateStudent(std);
            String jsonAfterUpdateStudent = ow.writeValueAsString(std);
            logger.info("Response: {} Path /student - Method PUT - Data: {}", stdUpdate.getRequest_id(), jsonAfterUpdateStudent);
            return stdUpdate;
        } catch (Exception ex){
            logger.error("Requset ID: {} Method POST have Exception: {}", std.getRequest_id(), ex.getMessage());
            return null;
        }
    }
}
