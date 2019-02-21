package com.lslnx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-13 16:56
 **/
@Controller
public class MyController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello(HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print("hello ...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
