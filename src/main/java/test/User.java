package test;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String pw;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;

    }

    public String toString() {
        return  id + ", " + pw;
    }
}
