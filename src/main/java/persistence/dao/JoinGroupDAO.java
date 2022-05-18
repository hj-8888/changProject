package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.JoinGroupDTO;

import java.util.List;

public class JoinGroupDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public JoinGroupDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<JoinGroupDTO> selectAllJoinGroup() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<JoinGroupDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.JoinGroupMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public JoinGroupDTO selectOneJoinGroup(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        JoinGroupDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.JoinGroupMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public void insertJoinGroup(JoinGroupDTO joinGroupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.JoinGroupMapper.insertOne", joinGroupDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void updateJoinGroup(JoinGroupDTO joinGroupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.JoinGroupMapper.updateOne", joinGroupDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteJoinGroup(JoinGroupDTO joinGroupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.JoinGroupMapper.updateOne", joinGroupDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

}
