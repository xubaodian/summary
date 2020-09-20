package xdb.summary.common.httputils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class HttpUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    // get 请求方法
    public static String get(String url, Map<String, String> headers, int timeout) {
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
            StringBuffer sb = new StringBuffer();
            int code = response.getStatusLine().getStatusCode();
            if (code < 200 || code >= 400) {
                logger.error("remote query failed, response code: " + code);
                return sb.toString();
            }
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity, Charset.forName("UTF-8"));
        } catch (Exception ex) {
            logger.error("get remote query failed，url is： " + url);
            logger.error("errorStackTrace:", ex.getCause());
            return null;
        }
    }
}
