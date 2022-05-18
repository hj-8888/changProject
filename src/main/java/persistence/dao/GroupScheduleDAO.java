package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.GroupScheduleDTO;

import java.util.List;

public class GroupScheduleDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public GroupScheduleDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<GroupScheduleDTO> selectAllGroupSchedule() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<GroupScheduleDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.GroupScheduleMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public GroupScheduleDTO selectOneGroupSchedule(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        GroupScheduleDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.GroupScheduleMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public void insertGroupSchedule(GroupScheduleDTO groupScheduleDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.GroupScheduleMapper.insertOne", groupScheduleDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void updateGroupSchedule(GroupScheduleDTO groupScheduleDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.GroupScheduleMapper.updateOne", groupScheduleDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteGroupSchedule(GroupScheduleDTO groupScheduleDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.GroupScheduleMapper.updateOne", groupScheduleDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

}
