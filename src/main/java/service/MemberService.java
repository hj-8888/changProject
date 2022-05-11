package service;

import persistence.dao.InterestingSportsDAO;
import persistence.dao.LocalInfoDAO;
import persistence.dao.MemberDAO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;

import java.util.List;
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
        InterestingSportsDAO interestingSportsDAO = new InterestingSportsDAO();
        LocalInfoDAO localInfoDAO = new LocalInfoDAO();
        List<LocalInfoDTO>  Llist = localInfoDAO.selectLargeCategory();
        List<LocalInfoDTO>  Mlist = localInfoDAO.selectMiddleCategory();
        List<LocalInfoDTO>  Slist = localInfoDAO.selectSmallCategory();

        String id = sc.next();
        String pw = sc.next();
        String name = sc.next();
        int age = sc.nextInt();
        String gender = sc.next();
        String job = sc.next();
        String profileURL = sc.next();
        String nickname = sc.next();

        System.out.print("지역 대분류 입력: ");
        Llist.forEach(x -> x.getLargeCategoryLocal());
        String b = sc.next();

        System.out.print("지역 중분류 입력: ");
        Mlist.forEach(x -> x.getLargeCategoryLocal());
        String m = sc.next();

        System.out.print("지역소분류 입력: ");
        Slist.forEach(x -> x.getLargeCategoryLocal());
        String s = sc.next();

        int sportsIndex = sc.nextInt();
        int localInfoIndex = sc.nextInt();
        create(id, pw, name, age, gender, job, profileURL, nickname, sportsIndex, localInfoIndex);
    }

    public boolean equalList(List list, String str){
        boolean result = true;
        list.forEach(x ->
                if()
        );
        return result;
    }
}
