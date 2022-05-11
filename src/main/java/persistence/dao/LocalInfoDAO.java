package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import persistence.MyBatisConnectionFactory;
import persistence.dto.LocalInfoDTO;

import java.util.List;

public class LocalInfoDAO {
    private MyBatisConnectionFactory myBatisConnectionFactory = null;

    public LocalInfoDAO() {
        myBatisConnectionFactory = new MyBatisConnectionFactory();
    }

    public List<LocalInfoDTO> selectLocalInfo() {
        SqlSession sqlSession = myBatisConnectionFactory.getSqlSessionFactory().openSession();
        List<LocalInfoDTO> list = null;
        try {
            list = sqlSession.selectList("mapper.LocalInfoDTOMapper.selectAll");
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
