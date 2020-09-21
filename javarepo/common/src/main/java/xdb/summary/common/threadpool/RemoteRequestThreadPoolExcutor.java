package xdb.summary.common.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *   远程请求线程池相关配置
 */
public class RemoteRequestThreadPoolExcutor {
    // 机器核数
    private static int  coreNum = Runtime.getRuntime().availableProcessors();

    /**
     *  远程请求耗时一般较大，开销远大于线程切换的开销，
     *  故此corePoolSize和maximumPoolSize可以设置较大，
     *  具体数值之能在实验中验证较为合适的数值
     */
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            coreNum * 2,
            coreNum * 10,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(500),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    /**
     * @param tasks 待执行任务
     * @return 执行任务的结果list
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static <T> List<T> executeTasks(List<RequestFuture<T>> tasks) throws ExecutionException, InterruptedException {
        List<T> results = new ArrayList<>();
        List<Future<T>> futures = new ArrayList<>();
        tasks.forEach((RequestFuture reqFuture) -> {
            futures.add(threadPoolExecutor.submit(reqFuture));
        });
        for (Future<T> future : futures) {
            results.add(future.get());
        }
        return results;
    }
}
