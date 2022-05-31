package service;

import persistence.dao.*;
import persistence.dto.InterestingSportsDTO;
import persistence.dto.LocalInfoDTO;
import persistence.dto.MemberDTO;
import persistence.dto.PackingDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberService {
    private MemberDAO memberDAO;
    private LocalInfoDAO localInfoDAO;
    private InterestingSportsDAO interestingSportsDAO;
    private LoginDAO loginDAO;

    private MemberDTO memberDTO;
    public MemberService() {
        this.memberDAO = new MemberDAO();
        this.localInfoDAO = new LocalInfoDAO();
        this.interestingSportsDAO = new InterestingSportsDAO();
        this.loginDAO = new LoginDAO();
    }


    // 아이디 비번 일치 검사
    public int login(String id, String pw){
        // 로그인
        List<MemberDTO> list = loginDAO.login(id); // 로그인 하면 회원 정보 반환
        if(list.isEmpty()){
            System.out.println("해당 아이디가 없습니다.");
            return 0;
        }
        else{
            System.out.println("아이디 : "+ id +" 일치");
            if(pw.equals(list.get(0).getMemberPW())){
                System.out.println("로그인 성공");
                return 1;
            }
            else{
                System.out.println("비밀번호가 다릅니다.");
                return 0;
            }
        }
    }

    // 아이디 중복 검사
    public int isDuplication_id(String id){
        System.out.println("아이디 : "+ id);
        List<MemberDTO> list = memberDAO.selectOneId(id);
        if(list.size() > 0){
            System.out.println("중복 아이디 존재");
            return 0;
        }
        else {
            System.out.println("중복 아이디 없음");
            return 1;
        }
    }

    public int isDuplication_nick(String nick){
        System.out.println("닉네임 : "+ nick);
        List<MemberDTO> list = memberDAO.selectOneNick(nick);
        if(list.size() > 0){
            System.out.println("닉네임 존재");
            return 0;
        }
        else {
            System.out.println("중복 닉네임 없음");
            return 1;
        }
    }

    public void signup(MemberDTO memberDTO, LocalInfoDTO localInfoDTO, InterestingSportsDTO interestingSportsDTO, BufferedImage img){
        int primary_LocalInfo = localInfoDAO.selectID(localInfoDTO);
        int primary_InterestingSport = interestingSportsDAO.selectOneBySportName(interestingSportsDTO.getSportName()).getSportIndex();
        String imgName = memberDTO.getMemberID();

        OutputStream out = null; //파일로 출력하기위해 파일출력스트림 생성
        String path = "./image/";
        String localPath = "path" + imgName + ".png";

        try {
            out = new FileOutputStream(localPath);
            ImageIO.write(img, "PNG", out); //이미지 출력! , 이미지를 파일출력스트림을 통해 JPG타입으로 출력


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();  //출력스트림 닫기
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        memberDTO.setProfileURL(localPath);
        memberDTO.setLocalInfoIndex(primary_LocalInfo);
        memberDTO.setSportsIndex(primary_InterestingSport);
        memberDAO.insertMember(memberDTO);

        System.out.println("회원 가입 완료 id : " + memberDTO.getMemberID());
    }

    private int getLocalInfoIndex(LocalInfoDTO localInfoDTO){
        return localInfoDAO.selectID(localInfoDTO);
    }

    // 관심 종목 인덱스리턴
    private int getSportIndex(InterestingSportsDTO interestingSportsDTO){
        return interestingSportsDAO.selectOneIndex(interestingSportsDTO).getSportIndex();
    }

    // 인물 검색
    // 종목 인덱스, 지역 인덱스를 포함한 회원 리스트 리턴
    public List<MemberDTO> searchMember(PackingDTO packingDTO){
        int sportIndex = getLocalInfoIndex(packingDTO.getLocalInfoDTO());
        int localIndex = getSportIndex(packingDTO.getInterestingSportsDTO());
        memberDTO.setSportsIndex(sportIndex);
        memberDTO.setLocalInfoIndex(localIndex);
        List<MemberDTO> list = memberDAO.selectAllBySportIndexAndlLocalInfoIndex(memberDTO);
        return null;
    }
    private void storeImg(ImageIO imgIO) {

    }

}