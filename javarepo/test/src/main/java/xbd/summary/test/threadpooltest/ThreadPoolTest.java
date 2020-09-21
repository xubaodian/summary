package xbd.summary.test.threadpooltest;

import org.junit.jupiter.api.Test;
import xdb.summary.common.httputils.HttpUtils;
import xdb.summary.common.threadpool.RemoteRequestThreadPoolExcutor;
import xdb.summary.common.threadpool.RequestFuture;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ThreadPoolTest {
    @Test
    public void testMultiReq() throws UnsupportedEncodingException, ExecutionException, InterruptedException {
        String [] keys = {"篮球", "足球", "羽毛球", "乒乓球", "冰球", "溜溜球", "棒球", "网球", "橄榄球", "球球"};
        List<RequestFuture<String>> futureTasks = new ArrayList<>();
        Date start = new Date();
        for(String key : keys) {
            RequestBaidu requestBaidu = new RequestBaidu("https://www.baidu.com/sugrec?prod=pc&wd=" + URLEncoder.encode(key, "utf-8"));
            futureTasks.add(new RequestFuture(requestBaidu));
        }
        List<String> results = RemoteRequestThreadPoolExcutor.executeTasks(futureTasks);
        Date end = new Date();
        for(String res : results) {
            System.out.println("=================================================");
            System.out.println(res);
        }
        System.out.println("========================耗时=========================");
        System.out.println(end.getTime() - start.getTime());
    }

    @Test
    public void testMultiReqByOrder() throws UnsupportedEncodingException, ExecutionException, InterruptedException {
        String [] keys = {"篮球", "足球", "羽毛球", "乒乓球", "冰球", "溜溜球", "棒球", "网球", "橄榄球", "球球"};
        List<RequestFuture<String>> futureTasks = new ArrayList<>();
        Date start = new Date();
        List<String> results = new ArrayList<>();
        for(String key : keys) {
            String res = HttpUtils.get("https://www.baidu.com/sugrec?prod=pc&wd=" + URLEncoder.encode(key, "utf-8"), null, 3000);
            results.add(res);
        }
        Date end = new Date();
        for(String res : results) {
            System.out.println("=================================================");
            System.out.println(res);
        }
        System.out.println("========================耗时=========================");
        System.out.println(end.getTime() - start.getTime());
    }
}
