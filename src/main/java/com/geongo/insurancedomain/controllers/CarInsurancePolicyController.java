package com.geongo.insurancedomain.controllers;

import com.geongo.insurancedomain.entity.*;
import com.geongo.insurancedomain.services.CarInsurancePolicyService;
import com.geongo.insurancedomain.services.TypeOfInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarInsurancePolicyController {

    @Autowired
    private TypeOfInsuranceService typeOfInsuranceService;
    @Autowired
    private CarInsurancePolicyService carInsurancePolicyService;

    @GetMapping("/car_policy_creation")
    public String car(Model model){

        List<TypeOfInsurance> types = typeOfInsuranceService.getTypes("car");
        model.addAttribute("types", types);
        CarInsurancePolicy policy = new CarInsurancePolicy();
        model.addAttribute("policyForm", policy);

        return "car_policy_creation_page";

    }

    @PostMapping("/car_policy_creation")
    public @ResponseBody
    Map<String, String> addPropertyPolicy(@ModelAttribute("policyForm") CarInsurancePolicy policyForm,
                                          @RequestParam List<String> persons, Model model){


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        policyForm.setUser(user);

        for (int i = 0; i<persons.size(); i++){

            policyForm.addPerson(new Person(persons.get(i)));
        }
        policyForm.setInsurancePayment(policyForm.getType().calculateInsurancePayment(policyForm.getCarCost()));
        carInsurancePolicyService.saveCarInsurancePolicy(policyForm);

        Map<String, String> results = new HashMap<>();
        results.put("status", "ok");

        return results;
    }

    @PostMapping("/countCarInsurancePayment")
    public @ResponseBody Map<String, String> getSums(@ModelAttribute("policyForm") CarInsurancePolicy policyForm, Model model){

        double insurancePayment = policyForm.getType().calculateInsurancePayment(policyForm.getCarCost());

        Map<String, String> results = new HashMap<>();
        results.put("insurancePayment", Double.toString(insurancePayment));
        return results;
    }

}
