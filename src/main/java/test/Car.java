package test;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private String wheel;
    private int fual;

    public Car(){
    }

    public Car(String wheel, int fual){
        this.wheel = wheel;
        this.fual = fual;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public int getFual() {
        return fual;
    }

    public void setFual(int fual) {
        this.fual = fual;
    }

    public String toString() {
        return "(" + wheel + ", " + fual;
    }
}
