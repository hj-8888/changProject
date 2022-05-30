package persistence.dao;

import persistence.dto.BulletinDTO;
import persistence.dto.MemberDTO;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        MemberDAO memberDAO = new MemberDAO();
        List<MemberDTO> list = memberDAO.selectOneNick("족장");
        for (MemberDTO i : list){
            System.out.println(i.getMemberIndex());
        }
    }

}
