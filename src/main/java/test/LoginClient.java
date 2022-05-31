package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
public class LoginClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        if(args.length < 2) System.out.println("사용법 : " + "java LoginClient 주소 포트번호");

        Socket socket = new Socket("127.0.0.1", 4444);
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
                    Car car = new Car();
                    try {
                        byte[] bytes = car.imageFileConvertToByteArray("C:\\Users\\xcxc4\\Documents\\GitHub\\changProject\\src\\main\\java\\service\\43124.PNG");
                        protocol.setObj(bytes);
                        out.writeObject(protocol);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

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
