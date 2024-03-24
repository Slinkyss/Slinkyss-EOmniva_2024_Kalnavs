package Model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.TreeMap;

public class Parcel {
    private boolean isFragile;
    private float price;
    private Driver driver;
    private ParcelSize size;

    private LocalDate orderCreated = LocalDate.now();

    private LocalDate plannedDelivery;

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(ParcelSize size, Boolean isFragile) {
        switch(size){
            case L:
                if(isFragile){
                    this.price = (float) (4 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (4 * 1.99); break;
            case XL:
                if(isFragile){
                    this.price = (float) (5 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (5 * 1.99); break;
            case M:
                if(isFragile){
                    this.price = (float) (3 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (3 * 1.99); break;
            case S:
                if(isFragile){
                    this.price = (float) (2 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (2 * 1.99); break;

            case X:
                if(isFragile){
                    this.price = (float) (1 * 1.99 + 2.99);
                    break;
                }
                else this.price = (float) (1 * 1.99); break;

        }
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ParcelSize getSize() {
        return size;
    }

    public void setSize(ParcelSize size) {
        if(size != null) {
            this.size = size;
        }else{
            this.size = ParcelSize.M;
        }
    }

    public LocalDate getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(LocalDate orderCreated) {
        this.orderCreated = orderCreated;
    }

    public LocalDate getPlannedDelivery() {

        return plannedDelivery;
    }

    public void setPlannedDelivery() {

        if(getDayNumberNew(orderCreated) == 7 ){
            this.plannedDelivery = orderCreated.plusDays(4);
        }
        if(getDayNumberNew(orderCreated) == 6 ){
            this.plannedDelivery = orderCreated.plusDays(5);
        }
        if(getDayNumberNew(orderCreated) == 5 ){
            this.plannedDelivery = orderCreated.plusDays(5);
        }
        if(getDayNumberNew(orderCreated) == 4 ){
            this.plannedDelivery = orderCreated.plusDays(6);
        }
        if(getDayNumberNew(orderCreated) == 3 ){
            this.plannedDelivery = orderCreated.plusDays(7);
        }
        if(getDayNumberNew(orderCreated) == 2 ){
            this.plannedDelivery = orderCreated.plusDays(3);
        }
        if(getDayNumberNew(orderCreated) == 1 ){
            this.plannedDelivery = orderCreated.plusDays(3);
        }

    }



    public static int getDayNumberNew(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

    public Parcel( ParcelSize size, boolean isFragile, Driver driver){
        setPlannedDelivery();
        setDriver(driver);
        setFragile(isFragile);
        setSize(size);
        setPrice(size,isFragile);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "isFragile=" + isFragile +
                ", price=" + price +
                ", driver=" + driver +
                ", size=" + size +
                ", orderCreated=" + orderCreated +
                ", plannedDelivery=" + plannedDelivery +
                '}';
    }
}
