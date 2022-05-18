package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.ChatLogDTO;


import java.util.List;

public class ChatLogDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public ChatLogDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    // 전체 조회
    public List<ChatLogDTO> selectAllChatLog() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<ChatLogDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.ChatLogMapper.selectAll");
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
    public ChatLogDTO selectOneChatLog(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        ChatLogDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.ChatLogMapper.selectOne", index);
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
    public void insertChatLog(ChatLogDTO chatLogDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.ChatLogMapper.insertOne", chatLogDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 수정
    public void updateChatLog(ChatLogDTO chatLogDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.ChatLogMapper.updateOne", chatLogDTO);
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
            sqlSession.delete("mapper.ChatLogMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
