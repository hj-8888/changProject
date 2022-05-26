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
    public List<CommentsDTO> selectOneCormments(CommentsDTO commentsDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<CommentsDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.commentsMapper.selectOne", commentsDTO);
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
            sqlSession.insert("mapper.commentsMapper.updateOne", commentsDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 삭제
<<<<<<< Updated upstream
    public void deleteCormments(CommentsDTO commentsDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.commentsMapper.deleteOne", commentsDTO);
=======
<<<<<<< Updated upstream
    public void deleteCormments(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.commentsMapper.deleteOne", index);
=======
    public void deleteCormments(int commentsIndex) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.commentsMapper.deleteOne", commentsIndex);
>>>>>>> Stashed changes
>>>>>>> Stashed changes
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
