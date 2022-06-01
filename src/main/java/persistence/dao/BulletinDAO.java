package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.BulletinDTO;

import java.util.List;

public class BulletinDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public BulletinDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    // 전체 조회
    public List<BulletinDTO> selectAllBulletin() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<BulletinDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.BulletinMapper.selectAll");
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
    public BulletinDTO selectOneBulletin(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        BulletinDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.BulletinMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public List<BulletinDTO> selectAllByTitle(BulletinDTO bulletinDTO){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession() ;
        List<BulletinDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.BulletinMapper.selectAllByTitle", bulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<BulletinDTO> selectAllByGroupIndex(int groupIndex){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession() ;
        List<BulletinDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.BulletinMapper.selectAllByGroupIndex", groupIndex);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public int selectOneByTitle(String title){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession() ;
        BulletinDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.BulletinMapper.selectOneByTitle", title);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item.getBulletinIndex();
    }
    // 생성
    public void insertBulletin(BulletinDTO bulletinDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.BulletinMapper.insertOne", bulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 수정
    public void updateBulletin(BulletinDTO bulletinDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.BulletinMapper.updateOne", bulletinDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 삭제
    public void deleteBulletin(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.BulletinMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

}
