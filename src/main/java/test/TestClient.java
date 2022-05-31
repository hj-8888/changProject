package test;

import java.net.*;
import java.io.*;
public class TestClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        if(args.length < 2) System.out.println("사용법 : " + "java LoginClient 주소 포트번호");

        Socket socket = new Socket("127.0.0.1", 3333);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        Protocol protocol = null;
        ObjectOutputStream out = new ObjectOutputStream(os);
        ObjectInputStream in = new ObjectInputStream(is);



            // 프로토콜에 객체가 담겨있음
            protocol = (Protocol) in.readObject();
            protocol = (Protocol) in.readObject();
            protocol = (Protocol) in.readObject();
            int packetType1 = protocol.getProtocolType();
            int packetType2 = protocol.getProtocolType();
            int packetType3 = protocol.getProtocolType();




        os.close();
        is.close();
        socket.close();
    }
}
