package com.quynh.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.hash;

/**
 * Class to store a collection of cars
 */
public class Cars {
    /**
     * A collection of cars
     */
    @JsonProperty("cars")
    private List<Car> carList = new ArrayList<>();

    /**
     * @return Collection of cars stored in this class
     */
    public List<Car> getCarList() {
        return carList;
    }

    /**
     * Sets the carList member to the given collection of cars
     * @param carList The collection of cars to store
     */
    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    /**
     * Add a single car to the list
     * @param car The car to store
     */
    public void add(Car car) {
        this.carList.add(car);
    }

    /**
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return carList.toString();
    }

    /**
     * Checks for value equality against the given object
     * @param obj Object for comparision
     * @return True if the given object contains cars with the same property, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) // Same object
            return true;

        if (obj instanceof Cars) { // Same type
            Cars otherCars = (Cars) obj;
            return carList.equals(otherCars.getCarList());
        }

        return false;
    }

    /**
     * Calculates the hash of this instance
     * @return Hash of the car list member
     */
    @Override
    public int hashCode() {
        return hash(carList);
    }
}
