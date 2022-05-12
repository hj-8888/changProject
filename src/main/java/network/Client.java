package network;

import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception, ClassNotFoundException, InterruptedException {

        String id = null, pwd=null; //클라이언트 id,password
        Socket socket = new Socket("127.0.0.1", 4444); // ip = 192.168.224.174
        System.out.println("클라이언트 시작");
    }
}
