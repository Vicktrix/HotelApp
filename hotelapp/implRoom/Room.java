package com.hotelapp.implRoom;

// We need change class name, because we have "Error parsing file"
// https://github.com/apache/netbeans/issues/4145 we haven`t any other Room file
// 

public class Room{
    protected int number;
    protected int beds;
    protected boolean balcony;
    protected boolean viewOfSea;
    protected int cost;

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
