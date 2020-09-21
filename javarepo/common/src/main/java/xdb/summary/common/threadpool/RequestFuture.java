package xdb.summary.common.threadpool;

import xdb.summary.common.threadpool.intf.RequestTask;
import java.util.concurrent.Callable;

/**
 * 任务的实现
 * @param <T>
 */
public class RequestFuture<T> implements Callable<T> {

    private RequestTask<T> requestTask;

    public RequestFuture(RequestTask requestTask) {
        this.requestTask = requestTask;
    }

    @Override
    public T call() throws Exception {
        return requestTask.excute();
    }
}
