package Network;

import lombok.SneakyThrows;
import persistence.dto.InterestingSportsDTO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;
import persistence.dto.SportsFacilitiesDTO;
import service.LocalInfoService;
import service.MemberService;
import persistence.dto.PackingDTO;
import service.ProfileService;
import service.SportsFacilitesService;
import java.io.*;
import java.net.*;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Server extends Thread {

    private Socket socket;
    private NetworkLog networkLog;
    private MemberDTO loginedMember;

    public Server(Socket connectedClientSocket) {
        this.socket = connectedClientSocket;
        networkLog = new NetworkLog();
    }

    public void send() {

    }

    public void receive(Protocol protocol, ObjectInputStream in) {

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
        LocalInfoDTO localInfoDTO;
        SportsFacilitiesDTO sportsFacilitiesDTO;
        InterestingSportsDTO interestingSportsDTO;
        PackingDTO packingDTO;

        // 서비스
        MemberService memberService = new MemberService();
        LocalInfoService localInfoService = new LocalInfoService();
        SportsFacilitesService sportsFacilitiesService = new SportsFacilitesService();
        SportsFacilitesService sportsFaciliitesService = new SportsFacilitesService();
        ProfileService profileService = new ProfileService();
        //List
        int result; // 검사 결과 메뉴
        while (true) {
            System.out.println(networkLog.streamWaitLog());

            protocol = (Protocol) in.readObject();
            protocolType = protocol.getProtocolType();
            protocolCode = protocol.getProtocolCode();

            if (protocolType == Protocol.PT_EXIT) {
                System.out.println("종료합니다.");
                break;
            }

            switch (protocolType) {
                case Protocol.PT_LOGIN:
                    switch (protocolCode) {
                        case Protocol.CD_LOGIN_REQ:
                            System.out.println("로그인 데이터 수신");
                            memberDTO = (MemberDTO) protocol.getObj();
                            result = memberService.login(memberDTO.getMemberID(), memberDTO.getMemberPW());
                            if (result == 1) {
                                // 로그인 성공
                                protocol = new Protocol(Protocol.PT_LOGIN, Protocol.CD_RES_LOGIN_SUCCESS);
                                loginedMember = memberDTO;
                                System.out.println("로그인 성공 결과 전송");
                            } else {
                                // 로그인 실패
                                protocol = new Protocol(Protocol.PT_LOGIN, Protocol.CD_RES_LOGIN_FAIL);
                                System.out.println("로그인 실패 결과 전송");
                            }
                            out.writeObject(protocol);
                            break;
                        default:
                            System.out.println("없는 코드 수신");
                            break;
                    }
                case Protocol.PT_SIGNUP:
                    // 회원 정보 수신
                    switch (protocolCode) {
                        case Protocol.CD_SIGNUP_ID_DUPLICATION_REQ:
                            memberDTO = (MemberDTO) protocol.getObj();
                            System.out.println("아이디 데이터 수신");
                            result = memberService.isDuplication_id(memberDTO.getMemberID());
                            if (result == 0) {
                                System.out.println("아이디 중복 성공 결과 전송");
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_ID_DUPLICATION_RES);
                            } else {
                                System.out.println("아이디 중복 실패 결과 전송");
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_ID_NOT_DUPLICATION_RES);
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_SIGNUP_MIDDLE_LOCATION_REQ:
                            System.out.println("대분류 데이터 수신");
                            localInfoDTO = (LocalInfoDTO) protocol.getObj();
                            // locatList 프로토콜에 저장
                            List<LocalInfoDTO> list;
                            list = localInfoService.transmit_middleLocation(localInfoDTO.getLargeCategoryLocal());
                            if (list.size() > 0) {
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_MIDDLE_LOCATION_RES);
                                protocol.setObj(list);
                                System.out.println("중분류 리스트 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_FAIL);
                                System.out.println("대분류 존재안함");
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_SIGNUP_SMALL_LOCATION_REQ:
                            localInfoDTO = (LocalInfoDTO) protocol.getObj();
                            System.out.println("중분류 데이터 수신");

                            System.out.println(localInfoDTO.toString());
                            // locatList 프로토콜에 저장
                            list = localInfoService.transmit_smallLocation(localInfoDTO.getLargeCategoryLocal(), localInfoDTO.getMiddleCategoryLocal());
                            if (list.size() > 0) {
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_SMALL_LOCATION_RES);
                                protocol.setObj(list);
                                System.out.println("소분류 리스트 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_FAIL);
                                System.out.println("소분류 존재안함");
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_SIGNUP_NICK_DUPLICATION_REQ:
                            memberDTO = (MemberDTO) protocol.getObj();
                            System.out.println("닉네임 데이터 수신");
                            result = memberService.isDuplication_nick(memberDTO.getNickname());
                            if (result == 0) {
                                System.out.println("닉네임 중복 성공 결과 전송");
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_NICK_DUPLICATION_RES);
                            } else {
                                System.out.println("닉네임 중복 실패 결과 전송");
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_NICK_NOT_DUPLICATION_RES);
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_SIGNUP_REQ:
                            System.out.println("test");

                            Object[] arrDTO = (Object[]) protocol.getObj();
                            interestingSportsDTO = (InterestingSportsDTO) arrDTO[0];
                            localInfoDTO = (LocalInfoDTO) arrDTO[1];
                            memberDTO = (MemberDTO) arrDTO[2];

                            ByteArrayInputStream byteStream = new ByteArrayInputStream((byte[]) arrDTO[3]);

                            System.out.println("회원가입정보 데이터 수신");
                            memberService.signup(memberDTO, localInfoDTO, interestingSportsDTO, byteStream);
                            protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SIGNUP_RES);
                            out.writeObject(protocol);

                            break;
                        default:
                            System.out.println("없는 코드 수신");
                            break;
                    }
                    break;

                case Protocol.PT_SPORTSFACILITIE_SEARCH:
                    switch (protocolCode) {
                        case Protocol.CD_SPORTSFACILITIE_SEARCH_MIDDLE_LOCATION_REQ:
                            System.out.println("대분류 데이터 수신");
                            localInfoDTO = (LocalInfoDTO) protocol.getObj();

                            List<LocalInfoDTO> lList;
                            lList = localInfoService.transmit_middleLocation(localInfoDTO.getLargeCategoryLocal());
                            if (lList.size() > 0) {
                                protocol = new Protocol(Protocol.PT_SPORTSFACILITIE_SEARCH, Protocol.CD_SPORTSFACILITIE_SEARCH_MIDDLE_LOCATION_RES);
                                protocol.setObj(lList);
                                System.out.println("중분류 리스트 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_SPORTSFACILITIE_SEARCH, Protocol.CD_SPORTSFACILITIE_SEARCH_FAIL);
                                System.out.println("대분류 존재안함");
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_SPORTSFACILITIE_SEARCH_SMALL_LOCATION_REQ:
                            localInfoDTO = (LocalInfoDTO) protocol.getObj();
                            System.out.println("중분류 데이터 수신");

                            System.out.println(localInfoDTO.toString());

                            List<LocalInfoDTO> list;
                            list = localInfoService.transmit_smallLocation(localInfoDTO.getLargeCategoryLocal(), localInfoDTO.getMiddleCategoryLocal());
                            if (list.size() > 0) {
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SPORTSFACILITIE_SEARCH_SMALL_LOCATION_RES);
                                protocol.setObj(list);
                                System.out.println("소분류 리스트 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_SIGNUP, Protocol.CD_SPORTSFACILITIE_SEARCH_FAIL);
                                System.out.println("소분류 존재안함");
                            }
                            out.writeObject(protocol);
                            out.flush();
                            break;

                        case Protocol.CD_SPORTSFACILITIE_SEARCH_REQ:
                            sportsFacilitiesDTO = (SportsFacilitiesDTO) protocol.getObj();
                            System.out.println("체육시설 검색 데이터 수신");
                            List<SportsFacilitiesDTO> slist;
                            slist = sportsFaciliitesService.searchSportsFacilites(sportsFacilitiesDTO);

                            if (slist != null) {
                                protocol = new Protocol(Protocol.PT_SPORTSFACILITIE_SEARCH, Protocol.CD_SPORTSFACILITIE_SEARCH_RES);
                                protocol.setObj(slist);
                                System.out.println("체육시설 검색 결과 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_SPORTSFACILITIE_SEARCH, Protocol.CD_SPORTSFACILITIE_SEARCH_FAIL);
                                System.out.println("체육시설 검색 실패");
                            }
                            out.writeObject(protocol);
                            break;
                    }
                    break;

                case Protocol.PT_PROFILE:
                    switch (protocolCode) {
                        case Protocol.CD_PT_PROFILE_REQ:
                            memberDTO = (MemberDTO) protocol.getObj();
                            System.out.println("회원 아이디 수신");

                            packingDTO = profileService.getProfile(memberDTO);
                            protocol = new Protocol(Protocol.PT_PROFILE, Protocol.CD_PROFILE_RES);
                            protocol.setObj(packingDTO);
                            System.out.println("회원 데이터 전송");
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_PT_PROFILE_UPDATE_REQ:
                            packingDTO = (PackingDTO) protocol.getObj();
                            System.out.println("회원 프로필 수정 데이터 수신");
                            profileService.updateProfile(packingDTO);
                            System.out.println("회원 수정 완료");
                            protocol = new Protocol(Protocol.PT_PROFILE, Protocol.CD_PROFILE_UPDATE_RES);
                            break;

                        case Protocol.CD_PROFILE_NICK_DUPLICATION_REQ:
                            memberDTO = (MemberDTO) protocol.getObj();
                            System.out.println("닉네임 데이터 수신");
                            result = memberService.isDuplication_nick(memberDTO.getNickname());
                            if (result == 0) {
                                System.out.println("닉네임 중복 성공 결과 전송");
                                protocol = new Protocol(Protocol.PT_PROFILE, Protocol.CD_PROFILE_NICK_NOT_DUPLICATION_RES);
                            } else {
                                System.out.println("닉네임 중복 실패 결과 전송");
                                protocol = new Protocol(Protocol.PT_PROFILE, Protocol.CD_PROFILE_NICK_DUPLICATION_RES);
                            }
                            out.writeObject(protocol);
                            break;
                    }
                    break;

                // 인물 검색
                case Protocol.PT_MEMBER_SEARCH:
                    switch (protocolCode) {
                        case Protocol.CD_MEMBER_SEARCH_MIDDLE_LOCATION_REQ:
                            System.out.println("대분류 데이터 수신");
                            localInfoDTO = (LocalInfoDTO) protocol.getObj();
                            // locatList 프로토콜에 저장
                            List<LocalInfoDTO> lList;
                            lList = localInfoService.transmit_middleLocation(localInfoDTO.getLargeCategoryLocal());
                            if (lList.size() > 0) {
                                protocol = new Protocol(Protocol.PT_MEMBER_SEARCH, Protocol.CD_MEMBER_SEARCH_MIDDLE_LOCATION_RES);
                                protocol.setObj(lList);
                                System.out.println("중분류 리스트 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_MEMBER_SEARCH, Protocol.CD_MEMBER_SEARCH_FAIL);
                                System.out.println("대분류 존재안함");
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_MEMBER_SEARCH_SMALL_LOCATION_REQ:
                            localInfoDTO = (LocalInfoDTO) protocol.getObj();
                            System.out.println("중분류 데이터 수신");

                            System.out.println(localInfoDTO.toString());
                            // locatList 프로토콜에 저장
                            List<LocalInfoDTO> list;
                            list = localInfoService.transmit_smallLocation(localInfoDTO.getLargeCategoryLocal(), localInfoDTO.getMiddleCategoryLocal());
                            if (list.size() > 0) {
                                protocol = new Protocol(Protocol.PT_MEMBER_SEARCH, Protocol.CD_MEMBER_SEARCH_SMALL_LOCATION_RES);
                                protocol.setObj(list);
                                System.out.println("소분류 리스트 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_MEMBER_SEARCH, Protocol.CD_MEMBER_SEARCH_FAIL);
                                System.out.println("소분류 존재안함");
                            }
                            out.writeObject(protocol);
                            break;

                        case Protocol.CD_MEMBER_SEARCH_REQ:
                            packingDTO = (PackingDTO) protocol.getObj();
                            System.out.println("인물 검색 데이터 수신/ InterestingSportsDTO, Local");
                            List<MemberDTO> mlist;
                            mlist = memberService.searchMember(packingDTO);
                            if (mlist != null) {
                                protocol = new Protocol(Protocol.PT_MEMBER_SEARCH, Protocol.CD_MEMBER_SEARCH_RES);
                                protocol.setObj(mlist);
                                System.out.println("인물 검색 결과 전송");
                            } else {
                                protocol = new Protocol(Protocol.PT_MEMBER_SEARCH, Protocol.CD_MEMBER_SEARCH_FAIL);
                                System.out.println("인물 검색 실패");
                            }
                            out.writeObject(protocol);
                            break;
                    }
                    break;
                default:
                    System.out.println("없는 타입 수신 (타입): " + protocol.getProtocolType());
                    System.out.println("없는 타입 수신 (코드): " + protocol.getProtocolCode());
                    break;
            }

            out.flush();
        }

        is.close();
        os.close();
        socket.close();
    }

}