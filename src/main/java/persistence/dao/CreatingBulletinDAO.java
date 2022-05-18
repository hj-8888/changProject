package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.CreatingBulletinDTO;

import java.util.List;

public class CreatingBulletinDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public CreatingBulletinDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<CreatingBulletinDTO> selectAllCreatingBulletin() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<CreatingBulletinDTO> list = null;
        try {
            list = sqlSession.selectList("mapper. creatingBulletinMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    // 인자 조회
    public List<CreatingBulletinDTO> selectOneCreatingBulletin(CreatingBulletinDTO creatingBulletinDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<CreatingBulletinDTO> list = null;
        try {
            list = sqlSession.selectList("mapper. creatingBulletinMapper.selectOne", creatingBulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    // 생성
    public void insertCreatingBulletin(CreatingBulletinDTO creatingBulletinDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper. creatingBulletinMapper.insertOne", creatingBulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 수정
    public void updateCreatingBulletin(CreatingBulletinDTO creatingBulletinDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper. creatingBulletinMapper.updateOne", creatingBulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 삭제
    public void deleteCreatingBulletin(CreatingBulletinDTO creatingBulletinDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper. creatingBulletinMapper.deleteOne", creatingBulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
