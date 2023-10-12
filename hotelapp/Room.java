package com.hotelapp;

public class Room{
    private int number;
    private int beds;
    private boolean balcony;
    private boolean viewOfSea;
    private int cost;

    public Room(int number,int beds,boolean balcony,boolean viewOfSea,int cost){
        this.number=number;
        this.beds=beds;
        this.balcony=balcony;
        this.viewOfSea=viewOfSea;
        this.cost=cost;
    }

    @Override
    public String toString(){
        return "Room{"+"number="+number+", beds="+beds+", balcony="+balcony+", viewOfSea="+viewOfSea+", cost="+cost+'}';
    }

    public int number(){
        return number;
    }

    public int beds(){
        return beds;
    }

    public boolean isBalcony(){
        return balcony;
    }

    public boolean isViewOfSea(){
        return viewOfSea;
    }

    public int cost(){
        return cost;
    }    
}
