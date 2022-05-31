package test;

import javax.swing.*;
import java.awt.*;
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
                    User user = null;
                    System.out.println("서버가 로그인 정보 요청");
                    BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("아이디 : ");
                    String id = userIn.readLine();
                    System.out.print("암호 : ");
                    String pwd = userIn.readLine();
                    user = new User(id,pwd);
                    // 로그인 정보 생성 및 패킷 전송

                    protocol = new Protocol(Protocol.PT_RES_LOGIN);

                    ImageIcon icon = new ImageIcon(
                            TestFrame.class.getResource("C:\Users\xcxc4\Desktop")
                    );

                    // ImageIcon 객체에서 Image 추출
                    Image img = icon.getImage();

                    out.writeObject(protocol);
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
