package test;

import chat.ListeningThread;
import chat.WritingThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
public class LoginClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        if(args.length < 2) System.out.println("사용법 : " + "java LoginClient 주소 포트번호");
        Socket socket = new Socket("192.168.231.154", 4444);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        Protocol protocol = null;
        ObjectOutputStream out = new ObjectOutputStream(os);
        ObjectInputStream in = new ObjectInputStream(is);


        while(true){
            // 프로토콜에 객체가 담겨있음
            protocol = (Protocol) in.readObject();
            int packetType = protocol.getProtocolType();

            if(packetType == Protocol.PT_EXIT){
                System.out.println("클라이언트 종료");
                break;
            }

            switch(packetType){
                case Protocol.PT_REQ_LOGIN:

                    System.out.println("서버가 로그인 정보 요청");
                    protocol = new Protocol(Protocol.PT_RES_LOGIN);
                    System.out.println("채팅 서버 생성 요청");
                    out.writeObject(protocol);
//                    -------------- 클라이언트 채팅 생성요청 ------------
                    // 데이터 베이스에서  IP, port를 갖고와서 연결
                    socket = new Socket("192.168.231.154", 1234);
                    System.out.println("서버에 접속 성공!"); // 접속 확인용

                    // 서버에서 보낸 메세지 읽는 Thread
                    ListeningThread t1 = new ListeningThread(socket);
                    WritingThread t2 = new WritingThread(socket); // 서버로 메세지 보내는 Thread

                    t1.start(); // ListeningThread Start
                    t2 .start(); // WritingThread Start

                    break;

                case Protocol.PT_LOGIN_RESULT:
                    System.out.println("서버가 로그인 결과 전송.");
                    protocol = new Protocol(Protocol.PT_EXIT);
                    System.out.println("종료 패킷 전송");
                    out.writeObject(protocol);
                    break;
            }
        }
        os.close();
        is.close();
        socket.close();
    }


}
