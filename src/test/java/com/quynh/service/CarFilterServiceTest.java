package com.quynh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quynh.domain.Car;
import com.quynh.domain.Cars;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class CarFilterServiceTest {
    private ObjectMapper objectMapper = new ObjectMapper();
    private CarFilterService service;

    @Before
    public void setup() {
        try {
            service = new CarFilterService(objectMapper);
        } catch (IOException e) {
            fail("Could not create car filter service " + e);
        }
    }

    @Test
    public void givenServiceCreated_thenParseCarsJsonInput() {
        assertFalse(service.getCars().getCarList().isEmpty());
    }

    @Test
    public void givenServiceRunning_whenSingleMatchFilter_thenReturnTheResult() {
        assertThat(service.filterByCarColour("red").getCarList()).hasSize(1)
            .contains(new Car("vauxhall", "corsa", "petrol", 3, "red"));
    }

    @Test
    public void givenServiceRunning_whenMultipleMatchFilter_thenReturnAllResults() {
        assertThat(service.filterByCarColour("blue").getCarList())
            .hasSize(4)
            .contains(new Car("ford", "fiesta", "petrol", 5, "blue"))
            .contains(new Car("ford", "focus", "diesel", 5, "blue"))
            .contains(new Car("peugeot", "207", "petrol", 3, "blue"))
            .contains(new Car("honda", "civic", "diesel", 5, "blue"));
    }

    @Test
    public void givenServiceRunning_whenUsingDifferentCases_thenReturnSameResult() {
        Cars lowercaseResults = service.filterByCarColour("red");
        Cars uppercaseResults = service.filterByCarColour("RED");
        Cars camelcaseResults = service.filterByCarColour("Red");

        assertEquals(lowercaseResults, uppercaseResults);
        assertEquals(uppercaseResults, camelcaseResults);
    }

    @Test
    public void givenServiceRunning_whenNoMatches_thenReturnNoCars() {
        assertEquals((new Cars()).getCarList(), service.filterByCarColour("indigo").getCarList());
    }

    @Test
    public void givenServiceRunning_whenInvalidColourFilterUsed_thenReturnNoCars() {
        Cars noCars = new Cars();
        assertEquals(noCars.getCarList(), service.filterByCarColour("").getCarList());
        assertEquals(noCars.getCarList(), service.filterByCarColour("123").getCarList());
        assertEquals(noCars.getCarList(), service.filterByCarColour("£$%*&%$").getCarList());
    }
}
