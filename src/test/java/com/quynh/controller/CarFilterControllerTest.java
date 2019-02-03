package com.quynh.controller;

import com.quynh.domain.Car;
import com.quynh.domain.Cars;
import com.quynh.service.CarFilterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarFilterController.class)
public class CarFilterControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarFilterService service;

    @Test
    public void givenCars_whenNotGetRequest_thenReturnMethodNotAllowed() throws Exception {
        mvc.perform(put("/cars?colour=red")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isMethodNotAllowed());
        mvc.perform(post("/cars?colour=red")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isMethodNotAllowed());
        mvc.perform(delete("/cars?colour=red")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void givenCars_whenFilterByColour_thenReturnJsonArray() throws Exception {
        Car redCar = new Car("vauxhall", "corsa", "petrol", 3, "red");
        Cars cars = new Cars();
        cars.add(redCar);

        given(service.filterByCarColour("red")).willReturn(cars);

        mvc.perform(get("/cars?colour=red")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.cars", hasSize(1)))
            .andExpect(jsonPath("$.cars[0].brand", equalTo(redCar.getBrand())))
            .andExpect(jsonPath("$.cars[0].model", equalTo(redCar.getModel())))
            .andExpect(jsonPath("$.cars[0].fuel", equalTo(redCar.getFuel())))
            .andExpect(jsonPath("$.cars[0].doors", equalTo(redCar.getDoors())))
            .andExpect(jsonPath("$.cars[0].colour", equalTo(redCar.getColour())));
    }

    @Test
    public void givenCars_whenInvalidUrl_thenReturnNotFound() throws Exception {
        mvc.perform(get("/bicycles?colour=blue")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
        mvc.perform(get("/submarines?colour=yellow")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void givenCarsWhenInvalidColour_thenReturnBadRequest() throws Exception {
        // Missing colour parameter
        mvc.perform(get("/cars")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());

        // Missing colour value
        mvc.perform(get("/cars?colour=")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());

        // Invalid colour values
        mvc.perform(get("/cars?colour= ")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
        mvc.perform(get("/cars?colour=1234")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
        mvc.perform(get("/cars?colour=r3d")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
        mvc.perform(get("/cars?colour=b!u3")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
        mvc.perform(get("/cars?colour=%$^£!")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
