package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.CommentsDTO;

import java.util.List;

public class CommentsDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public CommentsDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<CommentsDTO> selectAllCormments() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<CommentsDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.commentsMapper.selectAll");
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
    public CommentsDTO selectOneCormments(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        CommentsDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.commentsMapper.selectOne", index);
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
    public void insertCormments(CommentsDTO commentsDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.commentsMapper.insertOne", commentsDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 수정
    public void updateCormments(CommentsDTO commentsDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.commentsMapper.updateOne", commentsDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 삭제
    public void deleteCormments(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.commentsMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
