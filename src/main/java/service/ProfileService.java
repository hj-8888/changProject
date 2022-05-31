package service;

import persistence.dao.InterestingSportsDAO;
import persistence.dao.LocalInfoDAO;
import persistence.dao.MemberDAO;
import persistence.dto.InterestingSportsDTO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;
import persistence.dto.PackingDTO;

public class ProfileService {
    private LocalInfoDTO localInfoDTO;
    private InterestingSportsDTO interestingSportsDTO;
    private MemberDTO memberDTO;
    private PackingDTO packingDTO;
    private MemberDAO memberDAO;
    private LocalInfoDAO localInfoDAO;
    private InterestingSportsDAO interestingSportsDAO;
    // 지역 정보 인덱스로 지역정보 리턴
    public ProfileService(){
        this.memberDAO = new MemberDAO();
        this.localInfoDAO = new LocalInfoDAO();
        this.interestingSportsDAO = new InterestingSportsDAO();
    }
    private LocalInfoDTO getLocalInfo(int localIndex){
        return localInfoDAO.selectOneLocalInfo(localIndex);
    }

    // 관심 종목 인덱스로 관심종목 리턴
    private InterestingSportsDTO getSport(int sportindex){
        return interestingSportsDAO.selectOneInterestingSports(sportindex);
    }

    // 회원 아이디로 회원 정보 리턴
    private MemberDTO getMember(String id){
        return memberDAO.selectId(id);
    }


    // 지역 정보 인덱스리턴
    private int getLocalInfoIndex(LocalInfoDTO localInfoDTO){
        return localInfoDAO.selectID(localInfoDTO);
    }

    // 관심 종목 인덱스리턴
    private int getSportIndex(InterestingSportsDTO interestingSportsDTO){
        return interestingSportsDAO.selectOneIndex(interestingSportsDTO).getSportIndex();
    }

    // 회원 정보 아이디로 회원정보와, 지역 정보, 관심 종목 정보 리턴
    public PackingDTO getProfile(MemberDTO memberDTO){
        System.out.println(memberDTO.toString());

        memberDTO = getMember(memberDTO.getMemberID());
        localInfoDTO = getLocalInfo(memberDTO.getLocalInfoIndex());
        interestingSportsDTO = getSport(memberDTO.getSportsIndex());
        packingDTO.setMemberDTO(memberDTO);
        packingDTO.setLocalInfoDTO(localInfoDTO);
        packingDTO.setInterestingSportsDTO(interestingSportsDTO);

        return packingDTO;
    }

    // 회원 정보 업데이트 packingDTO로 회원, 종목, 지역 DTO를 받는다.
    public void updateProfile(PackingDTO packingDTO){
        int sportindex = getSportIndex(packingDTO.getInterestingSportsDTO());
        int localIndex = getLocalInfoIndex(packingDTO.getLocalInfoDTO());
        memberDTO = packingDTO.getMemberDTO();
        memberDTO.setLocalInfoIndex(localIndex);
        memberDTO.setSportsIndex(sportindex);
        memberDAO.updateMember(memberDTO);
        System.out.println("업데이트 완료");
    }
}
