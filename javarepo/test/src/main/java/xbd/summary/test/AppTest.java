package xbd.summary.test;

import org.junit.jupiter.api.Test;
import xdb.summary.common.httputils.HttpUtils;

import java.util.concurrent.FutureTask;


public class AppTest {

    @Test
    public void testHttp() {
        System.out.println(HttpUtils.get("https://www.baidu.com/sugrec?prod=pc&wd=%E5%A9%B4%E5%84%BF%E6%84%9F%E5%86%92", null, 10000));
    }



}
