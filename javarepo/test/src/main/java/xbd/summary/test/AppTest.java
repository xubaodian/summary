package xbd.summary.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xdb.summary.common.httputils.HttpUtils;


public class AppTest {

    @Test
    public void testHttp() {
        System.out.println(HttpUtils.get("https://baidu.com", null, 10000));
    }
}
