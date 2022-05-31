package service;

import persistence.dao.LocalInfoDAO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;

import java.util.List;

public class LocalInfoService {

    private MemberDTO memberDTO;
    private LocalInfoDAO localInfoDAO;

    public LocalInfoService(){
        this.memberDTO = new MemberDTO();
        this.localInfoDAO = new LocalInfoDAO();
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
}
