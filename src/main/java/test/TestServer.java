package test;

import java.net.*;
import java.io.*;

public class TestServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket sSocket = new ServerSocket(3333);
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
        protocol = new Protocol(Protocol.PT_EXIT);
        out.writeObject(protocol);
        protocol = new Protocol(Protocol.PT_LOGIN_RESULT);
        out.writeObject(protocol);





        is.close();
        os.close();
        socket.close();
    }
}
