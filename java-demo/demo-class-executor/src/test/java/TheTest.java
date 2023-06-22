import com.mango.clazz.executor.JavaClassExecutor;

import java.io.*;

/**
 * @Author: mango
 * @Date: 2022/9/2 7:42 下午
 */
public class TheTest {
    public static void main(String[] args) throws IOException {
        String classFile = "/Users/mango/git/java-study/demo-class-executor/target/test-classes/A.class";
        InputStream is = new FileInputStream(new File(classFile));
        byte[] data = new byte[is.available()];
        is.read(data);
        is.close();
        String str = JavaClassExecutor.execute(data);
        System.out.println(str);
    }
}
