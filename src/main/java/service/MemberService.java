package service;

import persistence.dao.InterestingSportsDAO;
import persistence.dao.LocalInfoDAO;
import persistence.dao.MemberDAO;
import persistence.dto.MemberDTO;

import java.util.Scanner;

public class MemberService {
    private final MemberDAO memberDAO;
    private final MemberDTO memberDTO;
    Scanner sc;

    public MemberService() {
        this.memberDAO = new MemberDAO();
        this.memberDTO = new MemberDTO();
        sc = new Scanner(System.in);
    }

    public void create(String id, String pw, String name, int age, String gender, String job, String profileURL, String nickname, int sportsIndex, int localInfoIndex){
        memberDTO.setMemberID(id);
        memberDTO.setMemberPW(pw);
        memberDTO.setMemberName(name);
        memberDTO.setAge(age);
        memberDTO.setGender(gender);
        memberDTO.setJob(job);
        memberDTO.setProfileURL(profileURL);
        memberDTO.setNickname(nickname);
        memberDTO.setSportsIndex(sportsIndex);
        memberDTO.setLocalInfoIndex(localInfoIndex);
        memberDAO.insertMember(memberDTO);
    }

    public void input_member(){
        String id = sc.next();
        String pw = sc.next();
        String name = sc.next();
        int age = sc.nextInt();
        String gender = sc.next();
        String job = sc.next();
        String profileURL = sc.next();
        String nickname = sc.next();

        InterestingSportsDAO interestingSportsDAO = new InterestingSportsDAO();
        LocalInfoDAO localInfoDAO = new LocalInfoDAO();
        System.out.println("지역번호 선택");
        // 대분류 출력
        String b = sc.next();
        // 대분류에 해당하는 중분류 출력
        String m = sc.next();
        // 중분류에 해당하는 소분류 출력
        String s = sc.next();
        int sportsIndex = sc.nextInt();
        int localInfoIndex = sc.nextInt();
        create(id, pw, name, age, gender, job, profileURL, nickname, sportsIndex, localInfoIndex);
    }

}
