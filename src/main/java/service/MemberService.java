package service;

import persistence.dao.*;
import persistence.dto.InterestingSportsDTO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;
import persistence.dto.SportsFacilitiesDTO;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    private MemberDAO memberDAO;
    private LoginDAO loginDAO;

    private MemberDTO memberDTO;
    Scanner sc;

    public MemberService() {
        this.memberDAO = new MemberDAO();
        this.memberDTO = new MemberDTO();
    }


    // 아이디 비번 일치 검사
    public int login(String id, String pw){
        // 로그인
        loginDAO = new LoginDAO();
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
        List<MemberDTO> list = memberDAO.selectOneMember(id);
        System.out.println("아이디 : "+ id);
        if(!list.isEmpty()){
            System.out.println("중복 아이디 존재");
            return 0;
        }
        else {
            System.out.println("중복 아이디 없음");
            return 1;
        }
    }

    // 대분류 전달 받고, 중분류 전송

}
