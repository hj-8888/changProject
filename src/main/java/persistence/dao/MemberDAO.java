package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MemberDTO;

import java.util.List;

public class MemberDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public MemberDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }
    // 회원가입
    // 지역 인덱스 가져오기
    // 관심종목 인덱스 가져오기

    public void insertMember(MemberDTO memberDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.MemberMapper.insertOne", memberDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
