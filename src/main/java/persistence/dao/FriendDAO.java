package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.FriendDTO;

import java.util.List;

public class FriendDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public FriendDAO(){
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public int selectFollowing(String index){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<FriendDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.FriendMapper.selectFollowingByIndex", index);
            sqlSession.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }
        return list.size();
    }

    public int selectFollower(String index){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<FriendDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.FriendMapper.selectFollowerByIndex", index);
            sqlSession.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }
        return list.size();
    }
}
