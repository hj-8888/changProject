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

    public List<LocalInfoDTO> selectSmallCategory(String lage, String middle) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        LocalInfoDTO local = new LocalInfoDTO();
        try {
            local.setLargeCategoryLocal(lage);
            local.setMiddleCategoryLocal(middle);
            list = sqlSession.selectList("mapper.LocalInfoDTOMapper.selectSmallCategory", local);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    public List<LocalInfoDTO> selectMiddleCategory(String lage) {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.LocalInfoDTOMapper.selectMiddleCategory", lage);
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
            list = sqlSession.selectList("mapper.LocalInfoDTOMapper.selectLargeCategory");
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return list;
    }


}
