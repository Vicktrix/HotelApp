package com.hotelapp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import static com.hotelapp.TypeOfVacation.*;
import java.util.stream.Stream;

public class BookingManager{
    // History of all Guest-Orders mapping (One by Many) with OrderID as key
    private static Map<Integer, Book> booking = new HashMap<>();
    
    private List<Room> rooms;
    
    public BookingManager(List<Room> list){
        rooms = list;
    }
    
    public StreamOfRooms<Room> rooms() {
        return new StreamOfRooms<Room>(rooms);
    }
    public void addToBook(Order order, Guest guest) {
        if(isOrderAlredyPresentInSystem(order)) {
            System.out.println("\n "+guest.getName()+" your Order - "+order+"\n is already in the System\n");
            return;
        }
        booking.put(order.orderId(),new Book(order,guest));
    }
    private boolean isOrderAlredyPresentInSystem(Order order) {
        return booking.values().stream().map(b -> b.order()).anyMatch(o -> o.equals(order));
    }
    public void addToBookWithMultipleGuestsInRoom(Order order, List<Guest> guests) {
        booking.put(order.orderId(),new Book(order, guests.get(0)));
        guests.stream().skip(1).forEach(g -> booking.put(order.getNumberOfOrders(),
                new Book(order.copyOrderWithIncreaseID(),g)));
    }
    public Order getOrderByNumOfOrder(Integer i) {
        return booking.get(i).order();
    }
    public List<Order> getOrdersByGuest(Guest guest) {
        return booking.values().stream().filter(b -> b.guest().equals(guest))
                .map(b -> b.order()).toList();
    }
    public List<Order> getAllOrders() {
        return booking.values().stream().map(b -> b.order()).toList();
    }
    public Optional<Guest> getGuestByOrder(Order o) {
        return booking.values().stream().filter(b -> b.order().equals(o))
                .map(b -> b.guest()).findFirst();
    }
    public List<Guest> getGuestByOrders(List<Order> list) {
        return booking.values().stream()
                .filter(b -> list.contains(b.order()))
                .map(b -> b.guest()).toList();
    }
    public List<Guest> getAllGuest() {
        return booking.values().stream().map(b -> b.guest()).toList();
    }
    public List<Order> getOrdersByRoomNumber(int n) {
        return booking.values().stream().filter(b -> b.order().getRoom().number()==n)
                .map(b -> b.order()).toList();
    }
    public List<Guest> getGuestsByRoomNumber(int n) {
        return booking.values().stream().filter(b -> b.order().getRoom().number()==n)
                .map(b -> b.guest()).toList();
    }
    public BiFunction<LocalDate, LocalDate, Predicate<Room>> isOrderedRoomInDate = 
            (from,to) -> r -> isOrderAlredyPresentInSystem(new Order(r,from,to, WORKING));
    public Predicate<Room> isOrderedRoomForRecreational = 
            r -> booking.values().stream().anyMatch(
                b -> b.order().getRoom()==r && b.order().getTypeOfVacation()==RECREATIONAL);
    public Predicate<Room> isOrderedRoomForWorking = 
            r -> booking.values().stream().anyMatch(
                b -> b.order().getRoom()==r && b.order().getTypeOfVacation()==WORKING);
    public Stream<Book> getBooksWithRoom(Room room) {
        return booking.values().stream().filter(b -> b.order().getRoom().number()==room.number());
    }
    public void clearBooking() {
        booking.clear();
        Order.clearHistory();
    }
    public void printAllBooking() {
        System.out.println("\tPrint All Booking\n");
        booking.values().forEach(b -> 
            System.out.println(b.guest().getName()+" has order "+b.order()));
        System.out.println("_______________________________________\n");
    }
}