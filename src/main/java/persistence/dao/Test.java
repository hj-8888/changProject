package persistence.dao;

import persistence.dto.*;
import service.ProfileService;


public class Test {
    public static void main(String[] args) {
        ProfileService profileService = new ProfileService();
        MemberDAO memberDAO = new MemberDAO();
        PackingDTO packingDTO = new PackingDTO();
        LocalInfoDTO localInfoDTO = new LocalInfoDTO();
        localInfoDTO.setLargeCategoryLocal("서울특별시");
        localInfoDTO.setMiddleCategoryLocal("종로구");
        localInfoDTO.setSmallCategoryLocal("청운동");


        InterestingSportsDTO interestingSportsDTO = new InterestingSportsDTO();
        interestingSportsDTO.setSportName("탁구");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberID("11");
        memberDTO.setMemberPW("1234");
        memberDTO.setMemberName("ㄱㅂㅁ");
        memberDTO.setNickname("ㅈㅈ");
        memberDTO.setAge(23);
        memberDTO.setGender("남");
        memberDTO.setJob("교수");
        memberDTO.setProfileURL("모름");
//        memberDTO.setLocalInfoIndex(1);
//        memberDTO.setSportsIndex(1);
//        memberDAO.updateMember(memberDTO);

        packingDTO.setMemberDTO(memberDTO);
        packingDTO.setInterestingSportsDTO(interestingSportsDTO);
        packingDTO.setLocalInfoDTO(localInfoDTO);
        profileService.updateProfile(packingDTO);

    }

}
