package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.InterestingSportsDTO;

import java.util.List;

public class InterestingSportsDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public InterestingSportsDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<InterestingSportsDTO> selecInterestingSports() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<InterestingSportsDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.InterestingSportsMapper.selectAll");
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
