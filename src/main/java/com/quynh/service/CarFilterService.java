package com.quynh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quynh.domain.Car;
import com.quynh.domain.Cars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Service which provides car property filtering
 */
@Service
public class CarFilterService {
    /**
     * Event logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Object mapper for JSON parsing
     */
    private ObjectMapper objectMapper;

    /**
     * Storage class for cars in the JSON input file
     */
    private Cars cars;

    /**
     * Constructor
     * @param objectMapper Object mapper for JSON parsing
     * @throws IOException Exception thrown if the JSON input file cannot be read
     */
    @Autowired
    public CarFilterService(ObjectMapper objectMapper) throws IOException {
        this.objectMapper = objectMapper;
        parseJsonFile();
    }

    /**
     * Source data used as the basis for filtering
     * @return Car source data
     */
    public Cars getCars() {
        return cars;
    }

    /**
     * Parses the JSON input file and stores the result in the cars member
     * @throws IOException Thrown if there are problems whilst parsing the JSON file
     */
    private void parseJsonFile() throws IOException {
        try {
            cars = objectMapper.readValue(new File("cars.json"), Cars.class);
        } catch (IOException e) {
            logger.error("Failed to read cars input file", e);
            // TODO: Throw a CarFilterServiceException instead.
            throw e;
        }
    }

    /**
     * Filters the collection of cars against the given colour
     * @param colour Colour for filtering
     * @return Cars matching the given colour
     */
    public Cars filterByCarColour(String colour) {
        Cars matches = new Cars();

        for (Car car : cars.getCarList()) {
            if (car.getColour().equalsIgnoreCase(colour)) {
                matches.add(car);
            }
        }

        return matches;
    }
}
