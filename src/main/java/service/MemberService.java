package service;

import persistence.dao.InterestingSportsDAO;
import persistence.dao.LocalInfoDAO;
import persistence.dao.MemberDAO;
import persistence.dao.SportsFacilitiesDAO;
import persistence.dto.InterestingSportsDTO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;
import persistence.dto.SportsFacilitiesDTO;

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
        List<LocalInfoDTO>  Mlist;
        List<LocalInfoDTO>  Slist;
        System.out.println();
        System.out.println(" id | pw | name | age | gender | job | profileURL | nickname ");
        System.out.print("입력 : ");
        String id = sc.next();
        String pw = sc.next();
        String name = sc.next();
        int age = sc.nextInt();
        String gender = sc.next();
        String job = sc.next();
        String profileURL = sc.next();
        String nickname = sc.next();


        List<InterestingSportsDTO> sList = interestingSportsDAO.selectOneInterestingSports();
        for (int i=0; i<sList.size(); i++){
            System.out.printf(" %d : %s", sList.get(i).getSportIndex(),sList.get(i).getSportName() );
        }
        int sportsIndex = sc.nextInt();

        String b, m, s;
        boolean toggl = false;
        while(true){
            System.out.print("지역 대분류 입력: ");
            Llist.forEach(x -> x.getLargeCategoryLocal());
            b = sc.next();
            for (int i=0; i<Llist.size(); i++){
                if(!Llist.get(i).getLargeCategoryLocal().equals(b)){
                    System.out.println("없는 지역, 다시 입력하셈");
                    break;
                }
                toggl = true;
            }
            if (toggl) {
                toggl = false;
                break;
            }
        }

        while(true) {
            System.out.print("지역 중분류 입력: ");
            Mlist = localInfoDAO.selectMiddleCategory(b);
            Mlist.forEach(x -> x.getLargeCategoryLocal());
            m = sc.next();
            for (int i = 0; i < Mlist.size(); i++) {
                if(!Mlist.get(i).getLargeCategoryLocal().equals(m)){
                    System.out.println("없는 지역, 다시 입력하셈");
                    break;
                }
            }
            if (toggl) {
                toggl = false;
                break;
            }
        }

        while(true) {
            System.out.print("지역소분류 입력: ");
            Slist = localInfoDAO.selectSmallCategory(b, m);
            Slist.forEach(x -> x.getLargeCategoryLocal());
            s = sc.next();
            for (int i = 0; i < Slist.size(); i++) {
                if(!Slist.get(i).getLargeCategoryLocal().equals(s)){
                    System.out.println("없는 지역, 다시 입력하셈");
                    break;
                }
            }
            if (toggl) {
                toggl = false;
                break;
            }
        }


        int localInfoIndex = sc.nextInt();
        create(id, pw, name, age, gender, job, profileURL, nickname, sportsIndex, localInfoIndex);
    }
}
