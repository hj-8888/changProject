package chat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChattingServer extends Thread {
    static ArrayList<Socket> list = new ArrayList<Socket>(); // 유저 확인용
    static Socket socket = null;

    public ChattingServer(Socket socket) {
        this.socket = socket; // 유저 socket을 할당
        list.add(socket); // 유저를 list에 추가
    }
    // Thread 에서 start() 메소드 사용 시 자동으로 해당 메소드 시작 (Thread별로 개별적 수행)
    public void run() {
        try {
            // 연결 확인용
            System.out.println("서버 : " + socket.getInetAddress()
                    + " IP의 클라이언트와 연결되었습니다");

            // InputStream - 클라이언트에서 보낸 메세지 읽기
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // OutputStream - 서버에서 클라이언트로 메세지 보내기
            OutputStream out = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);

            // 클라이언트에게 연결되었다는 메세지 보내기
            writer.println("서버에 연결되었습니다! ID를 입력해 주세요!");

            String readValue; // Client에서 보낸 값 저장
            String name = null; // 클라이언트 이름 설정용
            boolean identify = false;

            // 클라이언트가 메세지 입력시마다 수행
            while((readValue = reader.readLine()) != null ) {
                if(!identify) { // 연결 후 한번만 노출
                    name = readValue; // 이름 할당
                    identify = true;
                    writer.println(name + "님이 접속하셨습니다.");
                    continue;
                }

                // list 안에 클라이언트 정보가 담겨있음
                for(int i = 0; i<list.size(); i++) {
                    System.out.print("입력 : ");
                    out = list.get(i).getOutputStream();
                    writer = new PrintWriter(out, true);
                    // 클라이언트에게 메세지 발송
                    writer.println(name + " : " + readValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외처리
        }
    }
}
