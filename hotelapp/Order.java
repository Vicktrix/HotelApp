package com.hotelapp;

import com.hotelapp.implRoom.Room;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Order{
    private static int numberOfOrders = 1;
    private final Room room;
    private final LocalDate from;
    private final LocalDate to;
    private final Period period;
    private int id;
    private TypeOfVacation type;
    public Order(Room room,LocalDate from,LocalDate to, TypeOfVacation type){
        this.type = type;
        this.room=room;
        this.from=from.isBefore(to)? from : to;
        this.to=to.isAfter(from)? to : from;
        this.id = numberOfOrders++;
        this.period = this.from.until(this.to);
    }

    public TypeOfVacation getTypeOfVacation(){
        return type;
    }

    public void setTypeOfVacation(TypeOfVacation type){
        this.type=type;
    }

    public static int getNumberOfOrders(){
        return numberOfOrders;
    }

    public Room getRoom(){
        return room;
    }

    public LocalDate getFrom(){
        return from;
    }

    public LocalDate getTo(){
        return to;
    }

    public int orderId(){
        return id;
    }

    public Period getPeriod(){
        return period;
    }
    // for added multiple guests in one room in same time
    // we created new order, 
    //this method like clone with new numOrder and increase numberOfOrders
    public Order copyOrderWithIncreaseID() {
        return new Order(this.room, this.from, this.to, this.type);
    }
    public static void clearHistory() {
        numberOfOrders=1;
    }
    @Override
    public int hashCode(){
        int hash=3;
        hash=71*hash+Objects.hashCode(this.room);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(getClass()!=obj.getClass()){
            return false;
        }
        final Order other=(Order)obj;
        if(!Objects.equals(this.room,other.room)){
            return false;
        }
        return !(other.from.minusDays(1).isBefore(from) && other.to.minusDays(1).isBefore(from) ||
                other.from.plusDays(1).isAfter(to) && other.to.plusDays(1).isAfter(to));
    }

    @Override
    public String toString(){
        return "Order{room="+room+", from="+from+", to="+to+", period(Days) ="+period.getDays()+",\n\t TypeOfVacation="+type+", ID="+id+'}';
    }
}