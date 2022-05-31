package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.LocalInfoDTO;
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

    public SportsFacilitiesDTO selectOneSportsFacilities(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        SportsFacilitiesDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.SportsFacilitiesMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public List<SportsFacilitiesDTO> selectOneSportsFacilitie_local (SportsFacilitiesDTO sportsFacilitiesDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<SportsFacilitiesDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.SportsFacilitiesMapper.selectOne_local", sportsFacilitiesDTO);
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

    public void updateSportsFacilities(SportsFacilitiesDTO sportsFacilitiesDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.SportsFacilitiesMapper.updateOne", sportsFacilitiesDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteSportsFacilities(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.SportsFacilitiesMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

}
