package Network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerMain {
    // 스레드 sleep 함수 (sleep 함수의 Exception 제거용)
    private static void sleep() {
        try {
// 스레드 1초 대기
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 실행 함수
    public static void main(String[] main) {
        System.out.println("****************************************");
        System.out.println("다중 접속 서버 시작...");
        System.out.println("****************************************");

// 스레드를 50개만 사용 가능한 스레드풀을 생성한다.
        ExecutorService service = Executors.newFixedThreadPool(50);
// 스레드풀에 스레드를 돌린다.
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


                //Handler 클래스로 client 소켓 전송
                Network.Server handler = new Network.Server(connectedClientSocket);
                //스레드 시작, run()호출
                service.execute(() -> {
                    try {
                        handler.start(service); // start() --> run() 호출
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch(IOException ioe) {
            System.err.println("Exception generated...");
        } finally {
            try {
                server.close();
            } catch(IOException ignored) {
                System.out.println("예외발생");
            }
        }

    }
}

