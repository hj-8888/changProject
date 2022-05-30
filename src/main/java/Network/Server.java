package Network;

import lombok.SneakyThrows;
import persistence.dto.MemberDTO;
import service.MemberService;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;

public class Server extends Thread {

    private Socket socket;

    public Server(Socket connectedClientSocket) {
        this.socket = connectedClientSocket;
    }

    @SneakyThrows
    public void start(ExecutorService service) throws IOException {
        System.out.println("클라이언트 접속 ( " + socket.getInetAddress() + ")");

        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        ObjectInputStream in = new ObjectInputStream(is);


        Protocol protocol = null;
        int protocolType;
        int protocolCode;

        // DTO
        MemberDTO memberDTO;

        // 서비스
        MemberService memberService = new MemberService();

        int result; // 검사 결과 메뉴
        while(true)
        {
            protocol = (Protocol) in.readObject();
            protocolType = protocol.getProtocolType();
            protocolCode = protocol.getProtocolCode();

            if(protocolType == Protocol.PT_EXIT)
            {
                System.out.println("종료합니다.");
                break;
            }
            switch (protocolType)
            {
                case Protocol.PT_LOGIN:
                    switch (protocolCode)
                    {
                        case Protocol.CD_LOGIN_REQ:
                            System.out.println("로그인 데이터 수신");
                            memberDTO = (MemberDTO) protocol.getObj();
                            result = memberService.login(memberDTO.getMemberID(), memberDTO.getMemberPW());
                            if (result == 1)
                            {
                                // 로그인 성공
                                protocol = new Protocol(Protocol.PT_LOGIN, Protocol.CD_RES_LOGIN_SUCCESS);
                                System.out.println("로그인 성공 결과 전송");
                            } else
                            {
                                // 로그인 실패
                                protocol = new Protocol(Protocol.PT_LOGIN, Protocol.CD_RES_LOGIN_FAIL);
                                System.out.println("로그인 실패 결과 전송");
                            }
                            out.writeObject(protocol);
                            break;
                    }
                case Protocol.PT_SIGNUP:
                    // 회원 정보 수신
                    memberDTO = (MemberDTO) protocol.getObj();

                    switch (protocolCode)
                    {
                        case Protocol.CD_SIGNUP_DUPLICATION_REQ:
                            System.out.println("아이디 데이터 수신");
                            result = memberService.isDuplication_id(memberDTO.getMemberID());
                            if(result == 0)
                            {
                                System.out.println("아이디 중복 성공 결과 전송");
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_ID_DUPLICATION_RES);
                            }
                            else
                            {
                                System.out.println("아이디 중복 실패 결과 전송");
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_RES);
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_SIGNUP_MIDDLE_LOCATION_REQ:
                            System.out.println("대분류 데이터 수신");
                            result = memberService.isDuplication_id(memberDTO.getMemberID());

                            out.writeObject(protocol);
                            break;

                    }

            }

        }

    is.close();
    os.close();
    socket.close();

    }
}
