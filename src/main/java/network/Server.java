package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.concurrent.ExecutorService;

public class Server extends Thread {

    private Socket socket;

    public Server(Socket connectedClientSocket) {
        this.socket = connectedClientSocket;
    }

    public void start(ExecutorService service) throws IOException {
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        // 회원가입요청
        Protocol protocol = new Protocol(Protocol.PT_MEMBBER_CREATE, Protocol.CD_REQ_MEMBBER_CREATE);

        //로그인정보 요청 패킷을 전송
        os.write(protocol.getPacket());
        while(true){

            //새 Protocol 객체 생성 (기본 생성자)
            protocol = new Protocol();

            //기본 생성자로 생성할 때에는 바이트배열의 길이가 1000으로 지정됨
            byte[] buf = protocol.getPacket();

            //socket으로부터 읽어서(클라이언트의 입력) buf 에 저장한다. (블로킹메서드)
            is.read(buf);

            //패킷 타입을 얻고 Protocol 객체의 packet 멤버변수에 buf를 복사한다.
            int packetType = buf[0];
            int packetCode= buf[1];
            protocol.setPacket(packetType,packetCode, buf);




        }//end while

        is.close();
        os.close();
        socket.close();

    }
}
