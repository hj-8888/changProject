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

    public List<InterestingSportsDTO> selectAllInterestingSports() {
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

    public InterestingSportsDTO selectOneInterestingSports(int sportIndex) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        InterestingSportsDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.InterestingSportsMapper.selectOne", sportIndex);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public InterestingSportsDTO selectOneIndex(InterestingSportsDTO interestingSportsDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        InterestingSportsDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.InterestingSportsMapper.selectIndex", interestingSportsDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public InterestingSportsDTO selectOneBySportName(String sportName) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        InterestingSportsDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.InterestingSportsMapper.selectOneBySportName", sportName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public void insertInterestingSports(InterestingSportsDTO interestingSportsDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.InterestingSportsMapper.insertOne", interestingSportsDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void updateInterestingSports(InterestingSportsDTO interestingSportsDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.InterestingSportsMapper.updateOne", interestingSportsDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteInterestingSports(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.InterestingSportsMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
