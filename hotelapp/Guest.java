package com.hotelapp;

import java.time.LocalDate;

public class Guest {
    private final String name;
    private final String surname;
    private final LocalDate birthDay;
    public Guest(String name,String surname,LocalDate birthDay){
        this.name=name;
        this.surname=surname;
        this.birthDay=birthDay;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public LocalDate getBirthDay(){
        return birthDay;
    }
    @Override
    public String toString(){
        return "\n\tGuest{"+"name="+name+", surname="+surname+", birthDay="+birthDay
                +"}\n";
    }
    
}