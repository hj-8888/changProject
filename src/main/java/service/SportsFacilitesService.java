package service;


import persistence.dao.SportsFacilitiesDAO;
import persistence.dto.SportsFacilitiesDTO;

import java.util.List;

public class SportsFacilitesService {

    private SportsFacilitiesDAO sportsFacilitiesDAO;
    private SportsFacilitiesDTO sportsFacilitiesDTO;
    public SportsFacilitesService() {
        this.sportsFacilitiesDAO = new SportsFacilitiesDAO();
    }

    public List<SportsFacilitiesDTO> searchSportsFacilites(SportsFacilitiesDTO sportsFacilitiesDTO){
        System.out.println(sportsFacilitiesDTO.toString());
        // 지역 정보를 입력 받아서 리스트 검색
        List<SportsFacilitiesDTO> list = sportsFacilitiesDAO.selectOneSportsFacilitie_local(sportsFacilitiesDTO);
        if(list == null){
            System.out.println("비어있음");
            return null;
        }
        return list;
    }
}
