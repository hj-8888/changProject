package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.JoinChatRoomDTO;

import java.util.List;

public class JoinChatRoomDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public JoinChatRoomDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<JoinChatRoomDTO> selectAllJoinChatRoom() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<JoinChatRoomDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.JoinChatRoomMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public JoinChatRoomDTO selectOneJoinChatRoom(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        JoinChatRoomDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.JoinChatRoomMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public void insertJoinChatRoom(JoinChatRoomDTO joinChatRoomDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.JoinChatRoomMapper.insertOne", joinChatRoomDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void updateJoinChatRoom(JoinChatRoomDTO joinChatRoomDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.JoinChatRoomMapper.updateOne", joinChatRoomDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteJoinChatRoom(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.JoinChatRoomMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

}
