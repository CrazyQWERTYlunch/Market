package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class ControllerWeb {
    @RequestMapping("/")
    public String showCatalog() {
        return "catalog-view";
    }


}
