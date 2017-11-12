package net.ourteam.service;

import net.ourteam.util.JSONUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by zhizoo on 2016/6/22.
 */
@Service
public class IDCardAuthService {
    protected static final Logger LOG = LoggerFactory.getLogger(IDCardAuthService.class);
    protected static final String APIX_KEY = "aa1c5ee0b9524e43595890f103d21049";
    protected static final String APIX_URL = "http://v.apix.cn/apixcredit/idcheck/idcard";

    public String IDCardAuthRequest(String realname,String idcard) throws IOException {
        LOG.info("id card authentication for"+realname+idcard);
        String queryString = "cardno="+ idcard +"&name="+ realname + "&type=idcard";
        String responseString = null;
        StringBuffer stringBuffer = new StringBuffer();
        HttpClient httpClient = new HttpClient();
        HttpMethod httpMethod = new GetMethod(APIX_URL);
        try {
            if (StringUtils.isNotBlank(queryString)) {
                httpMethod.setQueryString(URIUtil.encodeQuery(queryString));
            }
            httpMethod.setRequestHeader("apix-key",APIX_KEY);
            httpMethod.setRequestHeader("content-type","application/json");
            httpMethod.setRequestHeader("accept","application/json");

            httpClient.executeMethod(httpMethod);
            responseString = httpMethod.getResponseBodyAsString();
        } catch (URIException e) {
            LOG.info(e.toString());
        } catch (ClientProtocolException e) {
            LOG.info(e.toString());
        } catch (IOException e) {
            LOG.info(e.toString());
        } catch (Exception e) {
            LOG.info("WTF exception "+e.toString());
        } finally {
            httpMethod.releaseConnection();
        }
        return IDCardAuthResult(responseString);
    }

    private String IDCardAuthResult(String resultJSON) throws IOException {
        Map<String,Object> result = JSONUtils.toObject(resultJSON, Map.class);
        try {
            if (Integer.valueOf(result.get("code").toString())==0) {
                String data = result.get("data").toString();
                LOG.info("success "+data);
                return data;
            } else {
                LOG.info(result.get("msg").toString());
            }
        } catch (Exception e) {
            LOG.info("WTF exception "+e.toString());
        }
        return "invalid";
    }
}
