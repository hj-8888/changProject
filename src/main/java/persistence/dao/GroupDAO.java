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

    public List<GroupDTO> selectAllGroup() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<GroupDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.GroupMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    // ์ธ์ ์กฐํ

    public GroupDTO selectOneGroup(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        GroupDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.GroupMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public int selectOneByName(String groupName){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        GroupDTO item = null;
        try{
            item = sqlSession.selectOne("mapper.GroupMapper,selectOneByName", groupName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item.getGroupIndex();
    }

    public List<GroupDTO> selectAllByName(String groupName){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<GroupDTO> list = null;
        try{
            list = sqlSession.selectOne("mapper.GroupMapper,selectOneByName", groupName);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    // ์์ฑ
    public void insertGroup(GroupDTO groupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.GroupMapper.insertOne", groupDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // ์์?
    public void updateGroup(GroupDTO groupDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper. GroupMapper.updateOne", groupDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // ์ญ์?

    public void deleteGroup(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.GroupMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
