package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.GroupDTO;

import java.util.List;

public class GroupDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public GroupDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<GroupDTO> selectAllCreatingBulletin() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<GroupDTO> list = null;
        try {
            list = sqlSession.selectList("mapper. GroupMapper.selectAll");
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
<<<<<<< Updated upstream
    public List<GroupDTO> selectOneCreatingBulletin(GroupDTO groupDTO) {
=======
<<<<<<< Updated upstream
    public GroupDTO selectOneGroup(int index) {
=======
    public List<GroupDTO> selectOneCreatingBulletin(int groupIndex) {
>>>>>>> Stashed changes
>>>>>>> Stashed changes
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<GroupDTO> list = null;
        try {
<<<<<<< Updated upstream
            list = sqlSession.selectList("mapper. GroupMapper.selectOne", groupDTO);
=======
<<<<<<< Updated upstream
            item = sqlSession.selectOne("mapper.GroupMapper.selectOne", index);
=======
            list = sqlSession.selectOne("mapper. GroupMapper.selectOne", groupIndex);
>>>>>>> Stashed changes
>>>>>>> Stashed changes
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
    public void insertCreatingBulletin(GroupDTO groupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper. GroupMapper.insertOne", groupDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 수정
    public void updateCreatingBulletin(GroupDTO groupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
<<<<<<< Updated upstream
            sqlSession.insert("mapper. GroupMapper.updateOne", groupDTO);
=======
<<<<<<< Updated upstream
            sqlSession.update("mapper.GroupMapper.updateOne", groupDTO);
=======
            sqlSession.update("mapper. GroupMapper.updateOne", groupDTO);
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

    // 삭제
<<<<<<< Updated upstream
    public void deleteCreatingBulletin(GroupDTO groupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper. GroupMapper.deleteOne", groupDTO);
=======
<<<<<<< Updated upstream
    public void deleteGroup(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.GroupMapper.deleteOne", index);
=======
    public void deleteCreatingBulletin(int groupIndex) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper. GroupMapper.deleteOne", groupIndex);
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
