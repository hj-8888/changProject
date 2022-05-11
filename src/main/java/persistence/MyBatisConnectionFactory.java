package persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisConnectionFactory {

    private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder = null;
    private static String resource = null;

    public MyBatisConnectionFactory() {
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        resource = "resources/config/config.xml";
    }

    public SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;

        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }
}
