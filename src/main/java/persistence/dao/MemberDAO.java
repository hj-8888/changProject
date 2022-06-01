package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MemberDTO;

import java.util.List;

public class MemberDAO {

    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public MemberDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<MemberDTO> selectAllMember() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<MemberDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.MemberMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public MemberDTO selectId(String id){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        MemberDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.MemberMapper.selectOne", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public List<MemberDTO> selectAllBySportIndexAndLocalInfoIndex(MemberDTO memberDTO){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<MemberDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.MemberMapper.selectAllBySportIndexAndlLocalInfoIndex", memberDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<MemberDTO> selectAllFollowingByIndex(MemberDTO memberDTO){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<MemberDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.MemberMapper.selectAllFollowingByIndex", memberDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<MemberDTO> selectAllFollowerByIndex(MemberDTO memberDTO){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<MemberDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.MemberMapper.selectAllFollowerByIndex", memberDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public int selectOneById(String id){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        MemberDTO item = null;
        try {
            item= sqlSession.selectOne("mapper.MemberMapper.selectOneById", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item.getMemberIndex();
    }
    public String selectOneNickNameById(String id){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        MemberDTO item = null;
        try {
            item= sqlSession.selectOne("mapper.MemberMapper.selectOneById", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item.getNickname();
    }

    public List<MemberDTO> selectOneId(String id){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<MemberDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.MemberMapper.selectOneId", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<MemberDTO> selectOneNick(String nick){
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<MemberDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.MemberMapper.selectOneNick", nick);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public void insertMember(MemberDTO memberDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.MemberMapper.insertOne", memberDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void updateMember(MemberDTO memberDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.MemberMapper.updateOne", memberDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteMember(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.MemberMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

}
