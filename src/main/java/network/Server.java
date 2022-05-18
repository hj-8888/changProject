package network;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;

public class Server extends Thread {

    private Socket socket;

    public Server(Socket connectedClientSocket) {
        this.socket = connectedClientSocket;
    }

    public void start(ExecutorService service) throws IOException {
        System.out.println(socket.getInetAddress() + " 클라이언트 접속");
        System.out.println("서버 실행");
        socket.close();
        if(socket.isClosed()){
            System.out.println(socket.getInetAddress() + " 클라이언트 종료");
        }
    }
}
