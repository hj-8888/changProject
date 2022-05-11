package persistence.dao;

import persistence.MyBatisConnectionFactory;

public class MemberDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public MemberDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }
    // 회원가입
    // 지역 인덱스 가져오기
    // 관심종목 인덱스 가져오기

}
