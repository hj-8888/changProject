package test;

import chat.ChattingServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;

public class LoginServer{
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        ServerSocket sSocket = new ServerSocket(4444);
        System.out.println("클라이언트 접속 대기중...");
        Socket socket = sSocket.accept();
        System.out.println("클라이언트 접속");

        // 바이트 배열로 전송할 것이므로 필터 스트림 없이 Input/OutputStream만 사용해도 됨
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        Protocol protocol = null;
        ObjectOutputStream out = new ObjectOutputStream(os);
        ObjectInputStream in = new ObjectInputStream(is);

        // 로그인 정보 요청용 프로토콜 객체 생성 및 전송
        protocol = new Protocol(Protocol.PT_REQ_LOGIN);
        out.writeObject(protocol);

        boolean program_stop = false;

        while(true){
            protocol = (Protocol) in.readObject();		// 새 Protocol 객체 생성 (기본 생성자)
            int packetType = protocol.getProtocolType();		// 수신 데이터에서 패킷 타입 얻음

            switch(packetType){
                case Protocol.PT_EXIT:			// 프로그램 종료 수신
                    protocol = new Protocol(Protocol.PT_EXIT);
                    out.writeObject(protocol);
                    program_stop = true;
                    System.out.println("서버종료");
                    break;

                case Protocol.PT_RES_LOGIN:		// 로그인 정보 수신
                    System.out.println("클라이언트가 " + "로그인 정보를 보냈습니다");

                    try {
                        int socketPort = 1234; // 소켓 포트 설정용
                        ServerSocket serverSocket = new ServerSocket(socketPort); // 서버 소켓 만들기
                        // 서버 오픈 확인용
                        System.out.println("socket : " + socketPort + "으로 서버가 열렸습니다");

                        // 소켓 서버가 종료될 때까지 무한루프
                        int count = 0;
                        while(true) {
                            socket = serverSocket.accept(); // 서버에 클라이언트 접속 시
                            count++;
                            System.out.println(count + "명 접속중");
                            // Thread 안에 클라이언트 정보를 담아줌
                            Thread thd = new ChattingServer(socket);
                            thd.start(); // Thread 시작
                        }

                    } catch (IOException e) {
                        e.printStackTrace(); // 예외처리
                    }

                    protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
                    System.out.println("로그인 처리 결과 전송");
                    out.writeObject(protocol);
                    break;
            }//end switch
            if(program_stop) break;

        }//end while

        is.close();
        os.close();
        socket.close();
    }
}
