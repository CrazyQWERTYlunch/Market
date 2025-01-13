package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ControllerWeb {
    @RequestMapping(value = "catalog/", method = RequestMethod.GET)
    @Operation(summary = "Example endpoint", description = "This is a sample endpoint.")
    public String showCatalog() {
        return "catalog-view";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Operation(summary = "Example endpoint", description = "This is a sample endpoint.")
    public String showHomePage() {
        return "catalog-view";
    }


}
