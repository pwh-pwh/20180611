package com.lisonglin.model;

import java.util.Objects;

/**
 * @author coderpwh
 * @version 1.0.0 v
 * @date 2022-06-16 19:05
 */
public class Car {
    String CarID;
    String date;
    boolean rented;
    // construcor: Creates a new car with entry date
    public Car(String Id, String entryDate) {
        // Implement this method
        CarID = Id;
        date = entryDate;
    }

    public Car(String carID, String date, boolean rented) {
        CarID = carID;
        this.date = date;
        this.rented = rented;
    }

    public void setCarID(String carID) {
        CarID = carID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public Car() {

    }

    // Marks the car as rented and updates the date to rented date
    public void rented(String rentedDate) {
        // Implement this method
        rented = true;
        date = rentedDate;

    }

    // Marks the car as not rented and updates the returned date
    public void returned(String rtnDate) {
        rented = false;
        date = rtnDate;
    }

    // check the car rental status,Returns true if it is rented, false otherwise
    public boolean isRented() {
        // Implement this method
        if (rented) return true;
        return false;
    }

    // Returns the car ID
    public String getCarID() {
        if (CarID != null) return CarID;
        return null;
    }

    //Returns the date of the car
    public String getDate() {
        // Implement this method
        return date;
    }
    @Override
    public String toString(){
        return "Car id :" + CarID + "  date:" + date + "  retended:" + rented;
    }
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime*result + CarID.hashCode() + date.hashCode();
        return result;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        return Objects.equals(this.CarID,other.CarID) && Objects.equals(this.date,other.date) && Objects.equals(this.rented,other.rented);
    }
}

