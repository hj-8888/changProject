package persistence.dao;

import persistence.dto.BulletinDTO;

import java.util.Date;

public class Test {


    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        BulletinDAO bulletinDAO = new BulletinDAO();
        BulletinDTO bulletinDTO = new BulletinDTO();
        bulletinDTO.setBulletinTitle("그그그금오");
        bulletinDTO.setBulletinContent("내내요요용");
        bulletinDTO.setAttachedPicture("사사진진");
        bulletinDTO.setBulletinPW("비비번번");
        bulletinDTO.setMemberIndex(1);
        bulletinDAO.insertBulletin(bulletinDTO);

    }

}
