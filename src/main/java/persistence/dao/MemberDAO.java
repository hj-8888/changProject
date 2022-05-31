package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MemberDTO;
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
        MemberDTO list = null;
        try {
            list = sqlSession.selectOne("mapper.MemberMapper.selectAll", id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
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
