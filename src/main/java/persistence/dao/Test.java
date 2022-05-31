package persistence.dao;

import persistence.dto.BulletinDTO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;
import persistence.dto.SportsFacilitiesDTO;
import service.SportsFacilitesService;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SportsFacilitesService sportsFaciliitesService = new SportsFacilitesService();
        SportsFacilitiesDTO localInfoDTO = new SportsFacilitiesDTO();
        localInfoDTO.setLargeCategoryLocal("강원도");
        localInfoDTO.setMiddleCategoryLocal("정선군");
        localInfoDTO.setSmallCategoryLocal("애산리");
        List<SportsFacilitiesDTO> list = sportsFaciliitesService.searchSportsFacilites(localInfoDTO);
        if(list == null){
            System.out.println("해당 값없음");
            return;
        }
        for (SportsFacilitiesDTO i : list){
            System.out.println(i.toString());
        }
    }

}
