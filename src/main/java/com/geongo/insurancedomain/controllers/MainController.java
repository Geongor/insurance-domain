package com.geongo.insurancedomain.controllers;

import com.geongo.insurancedomain.entity.CarInsurancePolicy;
import com.geongo.insurancedomain.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main(Model model, HttpSession session) {

        return "main";
    }


}
