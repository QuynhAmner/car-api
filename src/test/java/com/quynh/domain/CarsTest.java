package com.quynh.domain;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

public class CarsTest {
    private Car toyotaYaris;
    private Car vwPolo;
    private Cars setOfCars;
    private Cars anotherSetOfCars;
    private Cars differentSetOfCars;

    @Before
    public void setup() {
        toyotaYaris = new Car("Toyota", "Yaris", "Petrol", 5, "Silver");
        vwPolo = new Car("VW", "Polo", "Disel", 4, "Red");
        setOfCars = new Cars();
        anotherSetOfCars = new Cars();
        differentSetOfCars = new Cars();
    }

    @Test
    public void verifyEquality() {
        setOfCars.setCarList(Arrays.asList(toyotaYaris, vwPolo));
        anotherSetOfCars.setCarList(Arrays.asList(toyotaYaris, vwPolo));
        differentSetOfCars.setCarList(Arrays.asList(vwPolo));

        assertThat(setOfCars, equalTo(setOfCars));
        assertThat(setOfCars, equalTo(anotherSetOfCars));
        assertNotEquals(setOfCars, "a string");
        assertThat(setOfCars, not(equalTo(differentSetOfCars)));
        assertThat(setOfCars.hashCode(), equalTo(anotherSetOfCars.hashCode()));
    }

    @Test
    public void verifyAddingCars() {
        assertThat(setOfCars.getCarList().size(), equalTo(0));

        setOfCars.add(toyotaYaris);
        assertThat(setOfCars.getCarList().size(), equalTo(1));
        assertThat(setOfCars.getCarList().get(0), equalTo(toyotaYaris));

        setOfCars.add(vwPolo);
        assertThat(setOfCars.getCarList().size(), equalTo(2));
        assertThat(setOfCars.getCarList().get(1), equalTo(vwPolo));
    }
}