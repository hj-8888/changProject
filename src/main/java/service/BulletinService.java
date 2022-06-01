package service;

import persistence.dao.BulletinDAO;
import persistence.dao.CreatingBulletinDAO;
import persistence.dao.MemberDAO;
import persistence.dto.BulletinDTO;
import persistence.dto.CreatingBulletinDTO;
import persistence.dto.MemberDTO;
import persistence.dto.PackingDTO;

import java.util.List;

public class BulletinService {
    private BulletinDAO bulletinDAO;
    private CreatingBulletinDAO creatingBulletinDAO;
    private MemberDAO memberDAO;

    private BulletinDTO bulletinDTO;
    private CreatingBulletinDTO creatingBulletinDTO;
    public BulletinService(){
        this.bulletinDAO  = new BulletinDAO();
        this.creatingBulletinDAO = new CreatingBulletinDAO();
        this.memberDAO = new MemberDAO();
    }

    // 회원 아이디와, 게시글 내용으로 게시글 생성
    public void createBulletin(PackingDTO packingDTO){
        int primary_member = memberDAO.selectOneById(packingDTO.getMemberDTO().getMemberID());
        int primary_bulletin = bulletinDAO.selectOneByTitle(packingDTO.getBulletinDTO().getBulletinTitle());

        bulletinDAO.insertBulletin(bulletinDTO);
        creatingBulletinDTO.setBulletinIndex(primary_bulletin);
        creatingBulletinDTO.setMemberIndex(primary_member);
        creatingBulletinDAO.insertCreatingBulletin(creatingBulletinDTO);

        System.out.println("게시글 등록 완료 : " + bulletinDTO.getBulletinTitle() + "게시글 작성 인덱스 : " + creatingBulletinDTO.getCreatingBulletinIndex());
    }


    // 그룹 인덱스르 받고 게시글 리스트 리턴
    public List<BulletinDTO> searchBulletin_groupIndex(int groupIndex){
        List<BulletinDTO> list = bulletinDAO.selectAllByGroupIndex(groupIndex);
        if(list == null){
            System.out.println("비어있음");
            return null;
        }
        return list;
    }
    public List<BulletinDTO> searchBulletin_title(BulletinDTO bulletinDTO){
        System.out.println(bulletinDTO.toString());
        //게시글 제목을 입력 받아서 리스트 검색
        List<BulletinDTO> list = bulletinDAO.selectAllByTitle(bulletinDTO);
        if(list == null){
            System.out.println("비어있음");
            return null;
        }
        return list;
    }

    // 게시글 인덱스가 같은 게시글 리턴
    public BulletinDTO searchBulletin_index(int bulletinIndex){
        bulletinDTO = bulletinDAO.selectOneBulletin(bulletinIndex);
        return bulletinDTO;
    }
}
