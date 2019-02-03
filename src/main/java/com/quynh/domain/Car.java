package com.quynh.domain;

import static java.util.Objects.hash;

/**
 * Car class
 */
public class Car {
    private String brand;
    private String model;
    private String fuel;
    private Integer doors;
    private String colour;

    /**
     * Default constructor for use with Jackson.
     * Initialised with empty strings and 0 doors.
     */
    public Car() {
        this.brand = "";
        this.colour = "";
        this.fuel = "";
        this.doors = 0;
        this.colour = "";
    }

    /**
     * Parameterized constructor
     * @param brand Car brand e.g. VW
     * @param model Model of car e.g. Polo
     * @param fuel Type of fuel e.g. Petrol
     * @param doors Number of doors e.g. 3
     * @param colour Colour of car e.g. Silver
     */
    public Car(String brand, String model, String fuel, Integer doors, String colour) {
        this.brand = brand;
        this.model = model;
        this.fuel = fuel;
        this.doors = doors;
        this.colour = colour;
    }

    /**
     * @return Car make
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand Car make
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return Car model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model Car model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return Type of car fuel
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * @param fuel Type of car fuel
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     * @return Number of doors
     */
    public Integer getDoors() {
        return doors;
    }

    /**
     * @param doors Number of doors
     */
    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    /**
     * @return Car colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * Sets the car colour
     * @param colour Car colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * @return String representation of object.
     */
    @Override
    public String toString() {
        return brand + " " + model + " " + fuel +
            " " + doors + "-door " + colour;
    }

    /**
     * Checks for value equality against the given object
     * @param obj Object for comparision
     * @return True if object values are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) // Same object
            return true;

        if (obj instanceof Car) { // Same type
            Car other = (Car) obj;

            return this.colour.equalsIgnoreCase(other.getColour()) &&
                this.brand.equalsIgnoreCase(other.getBrand()) &&
                this.fuel.equalsIgnoreCase(other.getFuel()) &&
                this.doors.equals(other.getDoors()) &&
                this.model.equalsIgnoreCase(other.getModel());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return hash(brand, model, fuel, doors, colour);
    }
}
