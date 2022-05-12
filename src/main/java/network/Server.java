package network;

import java.net.*;

public class Server extends Thread {

    private Socket socket;

    public Server(Socket connectedClientSocket) {
        this.socket = connectedClientSocket;
    }

    public void run()  {
        System.out.println("클라이언트 접속 // 서버 실행");
    }
}
