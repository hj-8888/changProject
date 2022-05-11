package persistence.dao;

import persistence.dto.SportsFacilitiesDTO;
import service.MemberService;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        System.out.println("회원 가입");
        MemberService member = new MemberService();
        member.input_member();

    }

}
