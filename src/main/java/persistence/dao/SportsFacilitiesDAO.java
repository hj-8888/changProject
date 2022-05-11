package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.SportsFacilitiesDTO;
import java.util.List;

public class SportsFacilitiesDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public SportsFacilitiesDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<SportsFacilitiesDTO> selectAllSportsFacilities() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<SportsFacilitiesDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.SportsFacilitiesMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public void insertSportsFacilities(SportsFacilitiesDTO sportsFacilitiesDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.SportsFacilitiesMapper.insertOne", sportsFacilitiesDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

}
