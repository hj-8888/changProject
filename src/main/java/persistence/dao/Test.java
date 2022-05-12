package persistence.dao;

import persistence.dto.LocalInfoDTO;
import persistence.dto.SportsFacilitiesDTO;
import service.MemberService;

public class Test {

    public static void main(String[] args) {
        LocalInfoDAO localInfoDAO = new LocalInfoDAO();
        System.out.println(localInfoDAO.selectID("경기도", "안산시 단원구", "초지동"));
    }

}
