package test;

import java.io.Serializable;

public class Facking implements Serializable {
    private static final long serialVersionUID = 1L;
    private Car car;
    private User user;

    public Car getCar() {
        return car;
    }


    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Facking(Car car, User user){
        this.car = car;
        this.user = user;
    }
}
