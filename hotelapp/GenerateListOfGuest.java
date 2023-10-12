package com.hotelapp;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class GenerateListOfGuest{
    public List<Guest> get = new ArrayList<>();

    public GenerateListOfGuest(){
        add(new Guest("Adam","First",LocalDate.of(1900,Month.JANUARY,1)))
        .add(new Guest("Bernard","Second",LocalDate.of(1951,Month.MARCH,23)))
        .add(new Guest("Chen","Third",LocalDate.of(1980,Month.APRIL,1)))
        .add(new Guest("Daniel","Fourth",LocalDate.of(1983,Month.DECEMBER,15)))
        .add(new Guest("Edward","Fifth",LocalDate.of(1994,Month.JANUARY,26)))
        .add(new Guest("Ferdinand","Next",LocalDate.of(1993,Month.NOVEMBER,3)))
        .add(new Guest("Galois","Young",LocalDate.of(1900,Month.JANUARY,1)))
        .add(new Guest("Hilbert","Space",LocalDate.of(1900,Month.JANUARY,1)))
        .add(new Guest("Ian","NextOne",LocalDate.of(2002,Month.AUGUST,4)))
        .add(new Guest("Jack","Daniel",LocalDate.of(1900,Month.JANUARY,1)))
        .add(new Guest("Key","Seeing",LocalDate.of(1978,Month.JULY,14)))
        .add(new Guest("Luk","Blinded",LocalDate.of(1905,Month.FEBRUARY,11)))
        .add(new Guest("Mike","Ratting",LocalDate.of(1997,Month.AUGUST,30)))
        .add(new Guest("Noan","Underth",LocalDate.of(2001,Month.APRIL,25)));
    }
    private GenerateListOfGuest add(Guest guest) {
        get.add(guest);
        return this;
    }
}
