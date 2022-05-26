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

    public InterestingSportsDTO selectOneInterestingSports(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        InterestingSportsDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.InterestingSportsMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

<<<<<<< Updated upstream
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
=======
    //update, insert, delete는 수정할 일이 없어서 아마 미사용
//    public void insertInterestingSports(InterestingSportsDTO interestingSportsDTO) {
//        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
//        try {
//            sqlSession.insert("mapper.InterestingSportsMapper.insertOne", interestingSportsDTO);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    public void updateInterestingSports(InterestingSportsDTO interestingSportsDTO) {
//        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
//        try {
//            sqlSession.update("mapper.InterestingSportsMapper.updateOne", interestingSportsDTO);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
//
//    public void deleteInterestingSports(int index) {
//        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
//        try {
//            sqlSession.delete("mapper.InterestingSportsMapper.deleteOne", index);
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//    }
>>>>>>> Stashed changes

}
