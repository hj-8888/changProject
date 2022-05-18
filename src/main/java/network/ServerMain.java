package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("다중 접속 서버 시작...");
        System.out.println("****************************************");

        ServerSocket server = null;
        int connectCount=0;

        try {
            server = new ServerSocket(4444);

            while(true) {
                // 클라이언트가 접속해 오기를 기다립니다.
                Socket connectedClientSocket = server.accept();

                InetAddress ia = connectedClientSocket.getInetAddress();
                int port = connectedClientSocket.getLocalPort();// 접속에 사용된 서버측 PORT
                String ip = ia.getHostAddress(); // 접속된 원격 Client IP

                ++connectCount;  //접속자수 카운트
                System.out.print("현제 접속자 수: " + connectCount);
                System.out.print(" 접속-Local Port: "+ port);
                System.out.println(" Client IP: " + ip);

                //Handler 클래스로 client 소켓 전송
                network.Server handler = new network.Server(connectedClientSocket);
                //스레드 시작, run()호출
                handler.start(); // start() --> run() 호출

                if(handler.isAlive()){
                    System.out.println("접속자 1명 종료");
                    connectCount--;
                }

            }
        } catch(IOException ioe) {
            System.err.println("Exception generated...");
        } finally {
            try {
                server.close();
            } catch(IOException ignored) {}
        }
    }
}
