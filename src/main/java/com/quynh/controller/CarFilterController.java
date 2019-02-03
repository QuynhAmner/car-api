package com.quynh.controller;

import com.quynh.domain.Cars;
import com.quynh.service.CarFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * Controller class for routing and handling of HTTP requests
 */
@RestController
@Validated
public class CarFilterController {
    /**
     * Service which provides filtering capabilities
     */
    private CarFilterService carFilterService;

    /**
     * Constructor
     * @param carFilterService Service to allow filtering based on car colour
     */
    @Autowired
    public CarFilterController(CarFilterService carFilterService) {
        this.carFilterService = carFilterService;
    }

    /**
     * Filters cars based on colour property
     * @param colour Car colour for filtering
     * @return Cars that match the given colour.
     */
    @GetMapping("/cars")
    public Cars filter(
        @Valid @Pattern(regexp = "^[a-zA-Z]+$")
        @RequestParam(value = "colour") String colour) {
        return carFilterService.filterByCarColour(colour);
    }
}