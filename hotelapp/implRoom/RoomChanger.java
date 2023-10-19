package com.hotelapp.implRoom;

public class RoomChanger extends Room{

    public RoomChanger(int number,int beds,boolean balcony,boolean viewOfSea,int cost){
        super(number,beds,balcony,viewOfSea,cost);
    }

    public void setNumber(int number){
        this.number=number;
    }

    public void setBeds(int beds){
        this.beds=beds;
    }

    public void setBalcony(boolean balcony){
        this.balcony=balcony;
    }

    public void setViewOfSea(boolean viewOfSea){
        this.viewOfSea=viewOfSea;
    }

    public void setCost(int cost){
        this.cost=cost;
    }
    
}
