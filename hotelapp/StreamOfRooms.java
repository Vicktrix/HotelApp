package com.hotelapp;

import com.hotelapp.implRoom.Room;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;
import java.time.LocalDate;

public class StreamOfRooms<T> implements Stream<T> {

    private final Stream<T> delegate;

    public StreamOfRooms(Stream<T> delegate){
        this.delegate=delegate;
    }
    public StreamOfRooms(List<T> list){
        this.delegate=list.stream();
    }
    
    public StreamOfRooms<T> byOneBed() {
        return this.filter(r -> ((Room)r).beds()==1);
    }
    public StreamOfRooms<T> byTwoBeds() {
        return this.filter(r -> ((Room)r).beds()==2);
    }
    public StreamOfRooms<T> byThreeBed() {
        return this.filter(r -> ((Room)r).beds()==3);
    }
    public StreamOfRooms<T> byFourBed() {
        return this.filter(r -> ((Room)r).beds()==4);
    }
    public StreamOfRooms<T> byBedsLessThan(int a) {
        return this.filter(r -> ((Room)r).beds()<a);
    }
    public StreamOfRooms<T> byBedsMoreThan(int a) {
        return this.filter(r -> ((Room)r).beds()>a);
    }
    public StreamOfRooms<T> byCostsBetweenBed(int a, int b) {
        return this.filter(r -> ((Room)r).cost()>=a).filter(r ->((Room)r).cost()<=b);
    }
    public StreamOfRooms<T> byCostsLessThan(int a) {
        return this.filter(r -> ((Room)r).cost()<=a);
    }
    public StreamOfRooms<T> byCostsMoreThan(int a) {
        return this.filter(r -> ((Room)r).cost()>=a);
    }
    public StreamOfRooms<T> withBalcony(boolean a) {
        return this.filter(r -> ((Room)r).isBalcony()==a);
    }
    public StreamOfRooms<T> withViewOfSee(boolean a) {
        return this.filter(r -> ((Room)r).isViewOfSea()==a);
    }
    public StreamOfRooms<T> sortByInreaseCost() {
        return this.sorted((r1,r2) -> ((Room) r1).cost()-((Room) r2).cost());
    }
    public StreamOfRooms<T> sortByDecreaseCost() {
        return this.sorted((r1,r2) -> ((Room) r2).cost()-((Room) r1).cost());
    }
    public Optional<Room> getRoomByNum(int n) {
//        return this.filter(r -> ((Room)r).number()==n).map(r -> (Room)r).findAny();
        return this.filter(r -> ((Room)r).number()==n).map(r -> (Room)r).findFirst();
    }
    public StreamOfRooms<T> getOrderedRoomInDate(LocalDate from, LocalDate to, BiFunction<LocalDate, LocalDate, Predicate<Room>> p) {
        return this.filter(r -> p.apply(from,to).test((Room)r));
    }
    public StreamOfRooms<T> getFreeRoomInDate(LocalDate from, LocalDate to, BiFunction<LocalDate, LocalDate, Predicate<Room>> p) {
        return this.filter(r -> !p.apply(from,to).test((Room)r));
    }
    public StreamOfRooms<T> getOrderedRoomAtNearYear(BiFunction<LocalDate, LocalDate, Predicate<Room>> p) {
        return this.filter(r -> p.apply(LocalDate.now().minusDays(1),LocalDate.now().plusYears(1)).test((Room)r));
    }
    public StreamOfRooms<T> getFreeRoomAtNearYear(BiFunction<LocalDate, LocalDate, Predicate<Room>> p) {
        return this.filter(r -> !p.apply(LocalDate.now().minusDays(1),LocalDate.now().plusYears(1)).test((Room)r));
    }
    public StreamOfRooms<T> getHostedForRecreationalRoom(Predicate<Room> p) {
        return this.filter(r -> p.test((Room)r));
    }
    public StreamOfRooms<T> getHostedForWorkingRoom(Predicate<Room> p) {
        return this.filter(r -> p.test((Room)r));
    }
    // PROBABLY HERE ARE MISTAKE
    // why do we use findFirst()? because we need terminal operation and start new Stream
    // and findFirst() map one to many elements, where flatMap it`s mapping each elment to many (namy-to-many) that not good.
    public StreamOfRooms<T> clearFilter(StreamOfRooms<T> base) {
        System.out.println("Inside Clear filter");
        return new StreamOfRooms<>(this.findFirst().stream().flatMap(i -> base));
    }
    @Override
    public StreamOfRooms<T> filter(Predicate<? super T> predicate){
        return new StreamOfRooms<>(delegate.filter(predicate));
    }

    @Override
    public <R> StreamOfRooms<R> map(Function<? super T,? extends R> mapper){
        return new StreamOfRooms<>(delegate.map(mapper));
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper){
        return delegate.mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper){
        return delegate.mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper){
        return delegate.mapToDouble(mapper);
    }

    @Override
    public <R> StreamOfRooms<R> flatMap(Function<? super T,? extends Stream<? extends R>> mapper){
        return new StreamOfRooms<>(delegate.flatMap(mapper));
    }

    @Override
    public IntStream flatMapToInt(Function<? super T,? extends IntStream> mapper){
        return delegate.flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T,? extends LongStream> mapper){
        return delegate.flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T,? extends DoubleStream> mapper){
        return delegate.flatMapToDouble(mapper);
    }

    @Override
    public StreamOfRooms<T> distinct(){
        return new StreamOfRooms<>(delegate.distinct());
    }

    @Override
    public StreamOfRooms<T> sorted(){
        return new StreamOfRooms<>(delegate.sorted());
    }

    @Override
    public StreamOfRooms<T> sorted(Comparator<? super T> comparator){
        return new StreamOfRooms<>(delegate.sorted(comparator));
    }

    @Override
    public StreamOfRooms<T> peek(Consumer<? super T> action){
        return new StreamOfRooms<>(delegate.peek(action));
    }

    @Override
    public StreamOfRooms<T> limit(long maxSize){
        return new StreamOfRooms<>(delegate.limit(maxSize));
    }

    @Override
    public StreamOfRooms<T> skip(long n){
        return new StreamOfRooms<>(delegate.skip(n));
    }

    @Override
    public void forEach(Consumer<? super T> action){
        delegate.forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action){
        delegate.forEachOrdered(action);
    }

    @Override
    public Object[] toArray(){
        return delegate.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator){
        return delegate.toArray(generator);
    }

    @Override
    public T reduce(T identity,BinaryOperator<T> accumulator){
        return delegate.reduce(identity,accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator){
        return delegate.reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity,BiFunction<U,? super T,U> accumulator,BinaryOperator<U> combiner){
        return delegate.reduce(identity,accumulator,combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier,BiConsumer<R,? super T> accumulator,BiConsumer<R,R> combiner){
        return delegate.collect(supplier,accumulator,combiner);
    }

    @Override
    public <R,A> R collect(Collector<? super T,A,R> collector){
        return delegate.collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator){
        return delegate.min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator){
        return delegate.max(comparator);
    }

    @Override
    public long count(){
        return delegate.count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate){
        return delegate.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate){
        return delegate.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate){
        return delegate.noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst(){
        return delegate.findFirst();
    }

    @Override
    public Optional<T> findAny(){
        return delegate.findAny();
    }

    @Override
    public Iterator<T> iterator(){
        return delegate.iterator();
    }

    @Override
    public Spliterator<T> spliterator(){
        return delegate.spliterator();
    }

    @Override
    public boolean isParallel(){
        return delegate.isParallel();
    }

    @Override
    public StreamOfRooms<T> sequential(){
        return new StreamOfRooms<>(delegate.sequential());
    }

    @Override
    public StreamOfRooms<T> parallel(){
        return new StreamOfRooms<>(delegate.parallel());
    }

    @Override
    public StreamOfRooms<T> unordered(){
        return new StreamOfRooms<>(delegate.unordered());
    }

    @Override
    public StreamOfRooms<T> onClose(Runnable closeHandler){
        return new StreamOfRooms<>(delegate.onClose(closeHandler));
    }

    @Override
    public void close(){
        delegate.close();
    }
}