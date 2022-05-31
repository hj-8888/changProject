package service;

import persistence.dao.BulletinDAO;
import persistence.dao.CreatingBulletinDAO;
import persistence.dao.MemberDAO;
import persistence.dto.BulletinDTO;
import persistence.dto.CreatingBulletinDTO;
import persistence.dto.MemberDTO;

import java.util.List;

public class BulletinService {
    private BulletinDAO bulletinDAO;
    private CreatingBulletinDAO creatingBulletinDAO;
    private MemberDAO memberDAO;

    public BulletinService(){
        this.bulletinDAO  = new BulletinDAO();
        this.creatingBulletinDAO = new CreatingBulletinDAO();
        this.memberDAO = new MemberDAO();
    }

    public void createBulletin(BulletinDTO bulletinDTO, CreatingBulletinDTO creatingBulletinDTO, MemberDTO memberDTO){
        int primary_member = memberDAO.selectOneById(memberDTO.getMemberID());
        int primary_bulletin = bulletinDAO.selectOneByTitle(bulletinDTO.getBulletinTitle());

        bulletinDAO.insertBulletin(bulletinDTO);
        creatingBulletinDTO.setBulletinIndex(primary_bulletin);
        creatingBulletinDTO.setMemberIndex(primary_member);
        creatingBulletinDAO.insertCreatingBulletin(creatingBulletinDTO);

        System.out.println("게시글 등록 완료 : " + bulletinDTO.getBulletinTitle() + "게시글 작성 인덱스 : " + creatingBulletinDTO.getCreatingBulletinIndex());
    }

    public List<BulletinDTO> searchBulletin(BulletinDTO bulletinDTO){
        System.out.println(bulletinDTO.toString());
        //게시글 제목을 입력 받아서 리스트 검색
        List<BulletinDTO> list = bulletinDAO.selectAllByTitle(bulletinDTO);
        if(list == null){
            System.out.println("비어있음");
            return null;
        }
        return list;
    }
}
