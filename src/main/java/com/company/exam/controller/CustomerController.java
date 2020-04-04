package com.company.exam.controller;

import com.company.exam.entity.Customer;
import com.company.exam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class CustomerController {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/index")
    public String customers(String emailAddress, Model model) {
        List<Customer> listOfCustomers =
                customerRepository.findByEmailAddress(emailAddress);
        if (listOfCustomers != null) {
            model.addAttribute("customers", listOfCustomers);
        }
        return "/index";
    }

    @PostMapping(value = "/index")
    public String addToCustomers(String emailAddress, Customer customer) {
        customer.setEmailAddress(emailAddress);
        customerRepository.save(customer);
        return "redirect:/index";
    }
}