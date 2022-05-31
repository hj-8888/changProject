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

    // 전체 채팅방 조회
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

    // 인덱스에 해당하는 하나의 채팅방 조회
    public ChatRoomDTO selectOneChatRoom(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ChatRoomDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.ChatRoomMapper.selectOne", index);
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
            sqlSession.update("mapper.ChatRoomMapper.updateOne", chatRoomDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 삭제

    public void deleteChatRoom(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.ChatRoomMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
