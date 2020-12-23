package com.geongo.insurancedomain.controllers;

import com.geongo.insurancedomain.entity.Property;
import com.geongo.insurancedomain.entity.PropertyInsurancePolicy;
import com.geongo.insurancedomain.entity.TypeOfInsurance;
import com.geongo.insurancedomain.entity.User;
import com.geongo.insurancedomain.services.PropertyInsurancePolicyService;
import com.geongo.insurancedomain.services.TypeOfInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class PropertyInsuranceController {

    @Autowired
    private TypeOfInsuranceService typeOfInsuranceService;
    @Autowired
    private PropertyInsurancePolicyService propertyInsurancePolicyService;

    @GetMapping("/property_policy_creation")
    public String property(Model model) {

        List<TypeOfInsurance> types = typeOfInsuranceService.getTypes("property");
        model.addAttribute("types", types);
        PropertyInsurancePolicy policy = new PropertyInsurancePolicy();
        model.addAttribute("policyForm", policy);

        return "property_policy_creation_page";
    }

    @PostMapping("/property_policy_creation")
    public @ResponseBody Map<String, String> addPropertyPolicy(@ModelAttribute("policyForm") PropertyInsurancePolicy policyForm,
                                     @RequestParam List<String> props, @RequestParam List<Double> sum, Model model){


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        policyForm.setUser(user);

        for (int i = 0; i<props.size(); i++){

            policyForm.addProperty(new Property(props.get(i), sum.get(i)));
        }
        policyForm.setInsurancePayment(policyForm.getType().calculateInsurancePayment(sum));
        propertyInsurancePolicyService.savePropertyInsurancePolicy(policyForm);

        Map<String, String> results = new HashMap<>();
        results.put("status", "ok");

        return results;
    }

    @PostMapping("/countInsurancePayment")
    public @ResponseBody Map<String, String> getSums(@ModelAttribute("policyForm") PropertyInsurancePolicy policyForm,
                                                     @RequestParam List<Double> sum, Model model){

        double insurancePayment = policyForm.getType().calculateInsurancePayment(sum);

        Map<String, String> results = new HashMap<>();
        results.put("insurancePayment", Double.toString(insurancePayment));
        return results;
    }

}
