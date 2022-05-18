package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.ChatRoomDTO;

import java.util.List;

public class ChatRoomDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public ChatRoomDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<ChatRoomDTO> selectAllChatRoom() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<ChatRoomDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.ChatRoomMapper.selectAll");
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
    public List<ChatRoomDTO> selectOneChatRoom(ChatRoomDTO chatRoomDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<ChatRoomDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.ChatRoomMapper.selectOne", chatRoomDTO);
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
    public void insertChatRoom(ChatRoomDTO chatRoomDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.ChatRoomMapper.insertOne", chatRoomDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 수정
    public void updateChatRoom(ChatRoomDTO chatRoomDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.ChatRoomMapper.updateOne", chatRoomDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 삭제
    public void deleteChatRoom(ChatRoomDTO chatRoomDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.ChatRoomMapper.deleteOne", chatRoomDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
