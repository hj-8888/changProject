package persistence.dao;

import persistence.dto.SportsFacilitiesDTO;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        SportsFacilitiesDAO sportsFacilitiesDAO = new SportsFacilitiesDAO();
        SportsFacilitiesDTO sportsFacilitiesDTO = new SportsFacilitiesDTO();

        sportsFacilitiesDTO.setSportsFacilitiesName("현태체육관");
        sportsFacilitiesDTO.setLongitude("111.11");
        sportsFacilitiesDTO.setLatitude("11.11");
        sportsFacilitiesDTO.setSportsFacilitiesTel("010-9999-8888");
        sportsFacilitiesDTO.setSportsFacilitiesTypeCode("1");
        sportsFacilitiesDTO.setLocalInfoIndex(16679);
jhghuyihiu
        sportsFacilitiesDAO.insertSportsFacilities(sportsFacilitiesDTO);
        List<SportsFacilitiesDTO> list = sportsFacilitiesDAO.selectAllSportsFacilities();
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
