package persistence.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.LocalInfoDTO;

import java.util.List;

public class LocalInfoDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public LocalInfoDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<LocalInfoDTO> selectAllLocalInfo() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.LocalInfoMapper.selectAll");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public LocalInfoDTO selectOneLocalInfo(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        LocalInfoDTO item = null;
        try {
            item = sqlSession.selectOne("mapper.LocalInfoMapper.selectOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return item;
    }

    public void insertLocalInfo(LocalInfoDTO localInfoDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.insert("mapper.LocalInfoMapper.insertOne", localInfoDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void updateLocalInfo(LocalInfoDTO localInfoDTO) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.update("mapper.LocalInfoMapper.updateOne", localInfoDTO);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void deleteLocalInfo(int index) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        try {
            sqlSession.delete("mapper.LocalInfoMapper.deleteOne", index);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public List<LocalInfoDTO> selectSmallCategory(String large, String middle) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        LocalInfoDTO local = new LocalInfoDTO();
        try {
            local.setLargeCategoryLocal(large);
            local.setMiddleCategoryLocal(middle);
            list = sqlSession.selectList("mapper.LocalInfoMapper.selectSmallCategory", local);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<LocalInfoDTO> selectMiddleCategory(String large) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.LocalInfoMapper.selectMiddleCategory", large);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<LocalInfoDTO> selectLargeCategory() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        try {

            list = sqlSession.selectList("mapper.LocalInfoMapper.selectLargeCategory");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public int selectID(String b, String m, String s) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        LocalInfoDTO local = new LocalInfoDTO();
        try {
            local.setLargeCategoryLocal(b);
            local.setMiddleCategoryLocal(m);
            local.setSmallCategoryLocal(s);
            list = sqlSession.selectList("mapper.LocalInfoMapper.selectID", local);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list.get(0).getLocalInfoIndex();
    }
}
