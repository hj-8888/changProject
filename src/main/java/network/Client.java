package network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class Client {

    public static void main(String[] args) throws Exception, ClassNotFoundException, InterruptedException {

        Socket socket = new Socket("127.0.0.1", 4444); // ip = 192.168.224.174
        System.out.println("클라이언트 시작");
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        Protocol protocol = new Protocol();
        byte[] buf = protocol.getPacket();

        while(true){
            is.read(buf);

            int packetType = buf[0];
            int packetCode = buf[1];
            protocol.setPacket(packetType, packetCode, buf);

            if(packetType == Protocol.EXIT){
                System.out.println("클라이언트 종료");
                os.close();
                is.close();
                socket.close();
                break;
            }

            switch(packetType){
                case Protocol.PT_MEMBBER_CREATE :
                    switch (packetCode){
                        case Protocol.CD_REQ_MEMBBER_CREATE:
                            System.out.println("서버가 회원가입정보 요청");
                            System.out.print("아이디 : ");
                            String id = userIn.readLine();
                            System.out.print("암호 : ");
                            String pwd = userIn.readLine();
                            System.out.print("이름 : ");
                            String name = userIn.readLine();
                            System.out.print("성별 : ");
                            String gender = userIn.readLine();
                            System.out.print("직업 : ");
                            String job = userIn.readLine();
                            System.out.print("프로필 사진 : ");
                            String pickture = userIn.readLine();
                            System.out.print("닉네임 : ");
                            String nick = userIn.readLine();

                            protocol = new Protocol(Protocol.PT_MEMBBER_CREATE, Protocol.CD_REQ_largeCategoryLocal);
                            protocol.setId(id);
                            protocol.setPassword(pwd);
                            protocol.setName(name);
                            protocol.setGender(gender);
                            protocol.setJob(job);
                            protocol.setPickture(pickture);
                            protocol.setNick(nick);
                            System.out.println("일반정보 전달 및 대분류 요청");
                            os.write(protocol.getPacket());
                            break;

                        case Protocol.CD_RES_largeCategoryLocal:
                            System.out.println("대분류 선택 : 입력 대체:");
                            protocol = new Protocol(Protocol.PT_MEMBBER_CREATE, Protocol.CD_REQ_middleCategoryLocal);
                            String lLocation = userIn.readLine();
                            protocol.setLocation(lLocation);
                            System.out.println("대분류 전달 및 중분류 요청");
                            os.write(protocol.getPacket());
                            break;
                        case Protocol.CD_RES_middleCategoryLocal:
                            System.out.println("중분류 선택 : 입력 대체:");
                            protocol = new Protocol(Protocol.PT_MEMBBER_CREATE, Protocol.CD_REQ_smallCategoryLocal);
                            String mLocation = userIn.readLine();
                            protocol.setLocation(mLocation);
                            System.out.println("대분류 전달 및 소분류 요청");
                            os.write(protocol.getPacket());
                            break;
                        case Protocol.CD_RES_smallCategoryLocal:
                            System.out.println("소분류 선택 : 입력 대체:");
                            protocol = new Protocol(Protocol.PT_MEMBBER_CREATE, Protocol.CD_REQ_SPORTS);
                            String sLocation = userIn.readLine();
                            protocol.setLocation(sLocation);
                            System.out.println("소분류 전달 및 종목 요청");
                            os.write(protocol.getPacket());
                            break;
                        case Protocol.CD_RES_SPORTS:
                            System.out.println("종목 선택 : 입력 대체:");
                            protocol = new Protocol(Protocol.EXIT, Protocol.EXIT);
                            String sports = userIn.readLine();
                            protocol.setSports(sports);
                            System.out.println("종목 전달 및 회원가입 요청");
                            os.write(protocol.getPacket());
                            break;
                    }
                    break;
            }

        }//end while


    }
}
