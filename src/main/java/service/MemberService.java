package service;

import persistence.dao.*;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;

import java.util.List;
import java.util.Scanner;

public class MemberService {
    private MemberDAO memberDAO;
    private LoginDAO loginDAO;

    private MemberDTO memberDTO;
    private LocalInfoDAO localInfoDAO;

    public MemberService() {
        this.memberDAO = new MemberDAO();
        this.loginDAO = new LoginDAO();
        this.localInfoDAO = new LocalInfoDAO();
    }


    // 아이디 비번 일치 검사
    public int login(String id, String pw){
        // 로그인
        List<MemberDTO> list = loginDAO.login(id); // 로그인 하면 회원 정보 반환
        if(list.isEmpty()){
            System.out.println("해당 아이디가 없습니다.");
            return 0;
        }
        else{
            System.out.println("아이디 : "+ id +" 일치");
            if(pw.equals(list.get(0).getMemberPW())){
                System.out.println("로그인 성공");
                return 1;
            }
            else{
                System.out.println("비밀번호가 다릅니다.");
                return 0;
            }
        }
    }

    // 아이디 중복 검사
    public int isDuplication_id(String id){
        System.out.println("아이디 : "+ id);
        List<MemberDTO> list = memberDAO.selectOneId(id);
        if(list.size() > 0){
            System.out.println("중복 아이디 존재");
            return 0;
        }
        else {
            System.out.println("중복 아이디 없음");
            return 1;
        }
    }

    public int isDuplication_nick(String nick){
        System.out.println("닉네임 : "+ nick);
        List<MemberDTO> list = memberDAO.selectOneNick(nick);
        if(list.size() > 0){
            System.out.println("닉네임 존재");
            return 0;
        }
        else {
            System.out.println("중복 닉네임 없음");
            return 1;
        }
    }

    // 대분류 전달 받고, 중분류 전송
    public List<LocalInfoDTO> transmit_middleLocation(String lLocation){
        System.out.println("대분류 수신 : " + lLocation);
        List<LocalInfoDTO> list = localInfoDAO.selectMiddleCategory(lLocation);
        return  list;
    }

    public List<LocalInfoDTO> transmit_smallLocation(String lLocation, String mLocation){
        System.out.println("대분류 수신 : " + lLocation + " 중분류 수신 : " + mLocation);
        List<LocalInfoDTO> list = localInfoDAO.selectSmallCategory(lLocation, mLocation);
        return  list;
    }

    public void signup(MemberDTO memberDTO){
        memberDAO.insertMember(memberDTO);
        System.out.println("회원 가입 완료");
    }

}
