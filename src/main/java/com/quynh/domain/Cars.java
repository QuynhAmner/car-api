package com.quynh.domain;

import java.util.ArrayList;

/**
 * Class to store a collection of cars
 */
public class Cars {
    /**
     * A collection of cars
     */
    private ArrayList<Car> cars = new ArrayList<Car>();

    /**
     * @return Collection of cars stored in this class
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

    /**
     * Sets the cars member to the given collection of cars
     * @param cars The collection of cars to store
     */
    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    /**
     * Add a single car to the list
     * @param car The car to store
     */
    public void add(Car car) {
        this.cars.add(car);
    }

    /**
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return cars.toString();
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
            return cars.equals(otherCars.getCars());
        }

        return false;
    }
}
