package xdb.summary.common.httputils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.Map;

public class HttpUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    // get 请求方法
    public static String get(String url, Map<String, String> headers, int timeout) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
            }
        }
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            return "";
        } catch (Exception ex) {
            logger.error("get remote query failed，url is： " + url);
            logger.error("errorStackTrace:", ex.getCause());
            throw ex;
        }
    }
}
