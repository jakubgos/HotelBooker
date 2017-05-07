package com.jgos.hotelbooker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Bos on 2017-04-16.
 */


@Controller
public class Greetings {

    @RequestMapping("/test")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        System.out.print("asdasd");
        return "greetings";
    }

}
