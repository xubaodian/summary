package xbd.summary.test.threadpooltest;

import xdb.summary.common.httputils.HttpUtils;
import xdb.summary.common.threadpool.intf.RequestTask;

/**
 *
 */
public class RequestBaidu implements RequestTask<String> {

    private String url;

    public RequestBaidu(String url) {
        this.url = url;
    }

    @Override
    public String excute() {
        return HttpUtils.get(url, null, 30000);
    }
}
