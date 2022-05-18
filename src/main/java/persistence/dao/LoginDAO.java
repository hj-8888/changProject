package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MemberDTO;

import java.util.List;

public class LoginDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public LoginDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    // 로그인 아이디와 비번을 받아서 로그인 성공인지 실패인지만 결정
    public List<MemberDTO> login(String id){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<MemberDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.MemberMapper.selectAll_ID", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

}
