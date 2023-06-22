import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: mango
 * @Date: 2022/12/31 8:45 PM
 */
public class DbUtilApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33306/url_function?useUnicode=true&characterEncoding=UTF-8",
                "root", "123456");
        // 3. 通过dbutils的BeanListHandler来查询数据
        QueryRunner queryRunner = new QueryRunner();
        List<User> resultList = (List<User>)queryRunner.query(connection, "select * from t_user", new BeanListHandler(User.class));
        for(User user : resultList){
            System.out.println(user.toString());
        }
        // 4. 关闭资源链接
        connection.close();
    }
}
