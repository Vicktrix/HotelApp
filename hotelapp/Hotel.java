package com.hotelapp;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import static com.hotelapp.TypeOfVacation.*;

/**
 @author vitck
 */
public class Hotel{

    // LocalDate, Randoms and List of Guests for tests
    private static final LocalDate now=LocalDate.now();
    private static final Supplier<Integer> randGuest = () -> ThreadLocalRandom.current().nextInt(0,14);
    private static final Supplier<Integer> randRooms = () -> ThreadLocalRandom.current().nextInt(0,120);
    private static final List<Guest> guests = new GenerateListOfGuest().get;
    
    private static List<Room> generateRooms(int max) {
        IntUnaryOperator beds = i -> i%4+1;
        IntPredicate balcony = i -> i>30&&i%5!=0;
        IntPredicate viewOfSee = i -> i%3!=2;
        IntUnaryOperator vipRooms = i -> i>=80? 80 : 0;
        Function<Integer, Integer> cost = i -> beds.applyAsInt(i)*200 + (balcony.test(i)? 100 : 0) 
                + (viewOfSee.test(i)? 50 : 0) + vipRooms.applyAsInt(i);
        return IntStream.rangeClosed(1,max).mapToObj(i -> 
                new Room(i,beds.applyAsInt(i),balcony.test(i),viewOfSee.test(i), cost.apply(i))).toList();
    }


public static void main(String[] args){
    BookingManager reception = new BookingManager(generateRooms(120));
    
        //        TEST 1 : FILTER ROOMS 
//        System.out.println("\n__________byTwoBeds().byCostsLessThan(600).limit(30).sortByDecreaseCost()___________________\n");
//        reception.rooms()
//                .byTwoBeds()
//                .byCostsLessThan(600)
//                .limit(30)
//                .sortByDecreaseCost()
//                .forEach(System.out::println);
//        System.out.println("\n__________.byCostsLessThan(600).byOneBed().byCostsBetweenBed(240,400)___________________\n");
//        reception.rooms()
//                .byCostsLessThan(600)
//                .byOneBed()
//                .byCostsBetweenBed(240,400)
//                .forEach(System.out::println);
//        System.out.println("\n__________filter(r -> r.number()>=100).withBalcony(true).sortByInreaseCost()___________________\n");
//        reception.rooms()
//                .filter(r -> r.number()>=100)
//                .withBalcony(true)
//                .sortByInreaseCost()
//                .forEach(System.out::println);
//        System.out.println("\n__________.byBedsLessThan(4).byBedsMoreThan(1).peek(System.out::println).sortByInreaseCost()\n");
//        List<Room> toList=reception.rooms()
//                .byBedsLessThan(4)
//                .byBedsMoreThan(1)
//                .peek(System.out::println)
//                .sortByInreaseCost()
//                .toList();
//        System.out.println("_____________________________\n");
//        toList.forEach(System.out::println);
        
        //     TEST 2 : RESERVATION ON SYSTEM
                // One guest
//        testOneGuestOneRoomsDifferentDays(reception);
//        System.out.println("\ttestOneGuestOneRoomsDifferentDays\n");
//        reception.printAllBooking();
//        testOneGuestManyRoomsSameDays(reception);
//        System.out.println("\ttestOneGuestManyRoomsSameDays\n");
//        reception.printAllBooking();
//        reception.clearBooking();
//        System.out.println("clear history");
//        testOneGuestManyRoomsDifferendDays(reception);
//        System.out.println("\ttestOneGuestManyRoomsDifferendDays\n");
//        reception.printAllBooking();
//        testOneGuestOneRoomsSameDays(reception);
//        System.out.println("\ttestOneGuestOneRoomsSameDays\n");
//        reception.printAllBooking();
//        reception.clearBooking();
                // many guests
//                System.out.println("\tMany guests\n");
//        testManyGuestManyRoomsDifferendDays(reception);
//        System.out.println("\ttestManyGuestManyRoomsDifferendDays\n");
//        reception.printAllBooking();
//        testManyGuestManyRoomsSameDays(reception);
//        System.out.println("\ttestManyGuestManyRoomsSameDays\n");
//        reception.printAllBooking();
//        System.out.println("clear history");
//        reception.clearBooking();
//        testManyGuestOneRoomsDifferentDays(reception);
//        System.out.println("\ttestManyGuestOneRoomsDifferentDays\n");
//        reception.printAllBooking();
//        testManyGuestOneRoomsSameDays(reception);
//        System.out.println("\ttestManyGuestOneRoomsSameDays\n");
//        reception.printAllBooking();
//        reception.clearBooking();

        // added many guests in one room by one time
//        testManyGuestsToOneOrder(reception);
//        reception.printAllBooking();
        
        
        //   TEST 3 : RECREATIONAL/WORKING ORDERING
        
//        testRecreationalFilter(reception);
//        reception.printAllBooking();

        // TEST 4 : COMBINE ROOM-FILTERS AND RESERVATION BY DATE
        testCrateReservation(reception,50);
//        reception.printAllBooking();
        System.out.println("--------------1--------------");
        reception.rooms().getOrderedRoomInDate(now.plusDays(5),now.plusDays(35),reception.isOrderedRoomInDate)
                .byCostsBetweenBed(400,1800)
                .byBedsMoreThan(1)
                .withViewOfSee(true)
                .flatMap(reception::getBooksWithRoom)
                .forEach(System.out::println);
        System.out.println("--------------2--------------");
        reception.rooms().getOrderedRoomAtNearYear(reception.isOrderedRoomInDate)
                .byThreeBed()
                .withBalcony(false)
                .sortByDecreaseCost()
                .flatMap(reception::getBooksWithRoom)
                .forEach(System.out::println);
        System.out.println("--------------3--------------");
        reception.rooms().getOrderedRoomInDate(now.minusDays(2), now.plusWeeks(5), reception.isOrderedRoomInDate)
                .getHostedForRecreationalRoom(reception.isOrderedRoomForRecreational)
                .flatMap(reception::getBooksWithRoom)
                .filter(b -> b.guest().getName().equals("Daniel") || b.order().getRoom().cost()>500)
                .forEach(System.out::println);
                
}
    public static void testOneGuestManyRoomsDifferendDays(BookingManager reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0,j=0; i<10; i++, j+=4) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(j),now.plusDays(j+3), WORKING);
            reception.addToBook(order, guest);
        }
    }
    public static void testOneGuestManyRoomsSameDays(BookingManager reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0; i<10; i++) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(i+1),now.plusDays(i+4), WORKING);
            reception.addToBook(order, guest);
        }
    }
    public static void testOneGuestOneRoomsDifferentDays(BookingManager reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room = rooms.get(randRooms.get());
        for(int i=0,j=0; i<10; i++, j+=4) {
            order = new Order(room,now.plusDays(j),now.plusDays(j+3), WORKING);
            reception.addToBook(order, guest);
        }
    }
    public static void testOneGuestOneRoomsSameDays(BookingManager reception){
        final Guest guest=guests.get(randGuest.get());
        final List<Room> rooms=reception.rooms().toList();
        Room room = rooms.get(randRooms.get());
        Order order;
        for(int i=0; i<10; i++) {
            order = new Order(room,now.plusDays(i+1),now.plusDays(i+3), WORKING);
            reception.addToBook(order, guest);
        }
    }
    public static void testManyGuestManyRoomsDifferendDays(BookingManager reception){
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0,j=0; i<10; i++, j+=4) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(j),now.plusDays(j+3), WORKING);
            reception.addToBook(order, guests.get(i));
        }
    }
    public static void testManyGuestManyRoomsSameDays(BookingManager reception){
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room;
        for(int i=0; i<10; i++) {
            room = rooms.get(randRooms.get());
            order = new Order(room,now.plusDays(i),now.plusDays(i+3), WORKING);
            reception.addToBook(order, guests.get(i));
        }
    }
    public static void testManyGuestOneRoomsDifferentDays(BookingManager reception){
        final List<Room> rooms=reception.rooms().toList();
        Order order;
        Room room = rooms.get(randRooms.get());
        for(int i=0,j=0; i<10; i++, j+=4) {
            order = new Order(room,now.plusDays(j),now.plusDays(j+3), WORKING);
            reception.addToBook(order, guests.get(i));
        }
    }
    public static void testManyGuestOneRoomsSameDays(BookingManager reception){
        final List<Room> rooms=reception.rooms().toList();
        Room room = rooms.get(randRooms.get());
        Order order;
        for(int i=0; i<10; i++) {
            order = new Order(room,now.plusDays(i),now.plusDays(i+3), WORKING);
            reception.addToBook(order, guests.get(i));
        }
    }
    //-------------------------------------------------------------------------
    public static void testManyGuestsToOneOrder(BookingManager reception) {
        reception.clearBooking();
        Room room;
        Order order;
        Supplier<Boolean> suffle = () -> ThreadLocalRandom.current().nextBoolean();
        Supplier<Integer> randNumGuest = () -> ThreadLocalRandom.current().nextInt(2,5);
        List<Guest> listOfGuest;
        TypeOfVacation type;
        for(int i=0; i<10; i++) {
            type = i%2==0? WORKING : RECREATIONAL;
            room = reception.rooms().getRoomByNum(randRooms.get()+1).get();
            order = new Order(room,now.plusWeeks(1),now.plusWeeks(2), type);
            listOfGuest = guests.stream()
                    .sorted((a,b) -> suffle.get()? 1 : -1)
                    .limit(randNumGuest.get()).toList();
            reception.addToBookWithMultipleGuestsInRoom(order,listOfGuest);
        }
        reception.printAllBooking();
    }
    //-------------------------------------------------------------------------
    
    public static void testRecreationalFilter(BookingManager reception) {
        System.out.println("\n\t--------  testRecreationalFilter  ----------\n");
//        reception.clearBooking();
        Room room;
        Order order;
        TypeOfVacation type;
        for(int i=1; i<=50; i++) {
            type = (i>=30 || i%10==0)? WORKING : RECREATIONAL;
            room = reception.rooms().getRoomByNum(i).get();
            order = new Order(room,now.plusDays(i), now.plusDays(i*2+3), type);
            reception.addToBook(order, guests.get(randGuest.get()));
        }
        System.out.println("\n Show all recreational room-orders \n");
        reception.rooms().getHostedForRecreationalRoom(reception.isOrderedRoomForRecreational)
                .flatMap(reception::getBooksWithRoom).forEach(System.out::println);
        System.out.println("\n Show all working room-orders \n");
        reception.rooms().getHostedForWorkingRoom(reception.isOrderedRoomForWorking)
                .flatMap(reception::getBooksWithRoom).forEach(System.out::println);
    }
    public static void testCrateReservation(BookingManager reception, int numberOfReservation) {
        Room room;
        Order order;
        TypeOfVacation type;
        for(int i=1; i<=numberOfReservation; i++) {
            type = i%2==0? WORKING : RECREATIONAL;
            room = reception.rooms().getRoomByNum(i).get();
            order = new Order(room,now.plusDays(i), now.plusDays(i*2+3), type);
            reception.addToBook(order,guests.get(randGuest.get()));
        }
    }
}