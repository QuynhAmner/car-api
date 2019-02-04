package com.quynh.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

public class CarTest {
    private Car toyotaYaris;
    private Car anotherToyotaYaris;
    private Car vwPolo;
    private Car blankCar;

    @Before
    public void setup() {
        toyotaYaris = new Car("Toyota", "Yaris", "Petrol", 5, "Silver");
        anotherToyotaYaris = new Car("Toyota", "Yaris", "Petrol", 5, "Silver");
        vwPolo = new Car("VW", "Polo", "Disel", 3, "Blue");
        blankCar = new Car();
    }

    @Test
    public void verifyEquality() {
        assertThat(toyotaYaris, equalTo(toyotaYaris));
        assertThat(toyotaYaris, equalTo(anotherToyotaYaris));
        assertNotEquals(toyotaYaris, "a string");
        assertThat(toyotaYaris, not(equalTo(vwPolo)));
        assertThat(toyotaYaris.hashCode(), equalTo(anotherToyotaYaris.hashCode()));
    }

    @Test
    public void verifyConstructedCar() {
        assertThat(toyotaYaris.getBrand(), equalTo("Toyota"));
        assertThat(toyotaYaris.getModel(), equalTo("Yaris"));
        assertThat(toyotaYaris.getFuel(), equalTo("Petrol"));
        assertThat(toyotaYaris.getDoors(), equalTo(5));
        assertThat(toyotaYaris.getColour(), equalTo("Silver"));
    }

    @Test
    public void verifyStringRepresentation() {
        assertThat(toyotaYaris.toString(), equalTo("Toyota Yaris Petrol 5-door Silver"));
    }

    @Test
    public void verifyModifiedCar() {
        blankCar.setBrand("VW");
        assertThat(blankCar.getBrand(), equalTo("VW"));
        blankCar.setModel("Polo");
        assertThat(blankCar.getModel(), equalTo("Polo"));
        blankCar.setFuel("Disel");
        assertThat(blankCar.getFuel(), equalTo("Disel"));
        blankCar.setDoors(4);
        assertThat(blankCar.getDoors(), equalTo(4));
        blankCar.setColour("Green");
        assertThat(blankCar.getColour(), equalTo("Green"));
    }
}
