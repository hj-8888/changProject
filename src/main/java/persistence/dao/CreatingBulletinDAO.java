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
            list = sqlSession.selectList("mapper.creatingBulletinMapper.selectAll");
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
    public CreatingBulletinDTO selectOneCreatingBulletin(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        CreatingBulletinDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.creatingBulletinMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    // 생성
    public void insertCreatingBulletin(CreatingBulletinDTO creatingBulletinDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.creatingBulletinMapper.insertOne", creatingBulletinDTO);
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
            sqlSession.update("mapper.creatingBulletinMapper.updateOne", creatingBulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 삭제
    public void deleteCreatingBulletin(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.creatingBulletinMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
