package com.swak.lib.common.httpclient;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 封装http请求
 *
 * @author: ljq
 * @date: 2024/11/23
 */
public class SwakHttpClient {

    private String url;

    private String body;

    private Map<String, String> headers;

    private String contentType;

    private Map<String, Object> params;

    private CloseableHttpClient httpClient;

    private HttpclientProperties httpclientProperties;

    private boolean isJsonType = false;

    private HttpReqName httpReqName;

    private String path;

    public static SwakHttpClient create(HttpReqName name) {
        return create(name, "");
    }

    public static SwakHttpClient create(HttpReqName name, String path) {

        SwakHttpClient client = new SwakHttpClient();
        client.httpReqName = name;
        client.path = path;
        Assert.notNull(name, "http 名称不能为空 swak.http-client.name 配置");
        client.httpClient = HttpClientConfig.getSwakHttpClient();
        Assert.notNull(client.httpClient, "httpClient 不能为空");
        client.httpclientProperties = HttpClientConfig.httpclientProperties();
        Assert.notNull(client.httpclientProperties, "httpclientProperties 不能为空");
        return client;
    }

    public SwakHttpClient url(String url) {
        this.url = url;
        return this;
    }

    public SwakHttpClient path(String path) {
        this.path = path;
        return this;
    }

    public SwakHttpClient body(String body) {
        this.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE;
        this.isJsonType = true;
        this.body = body;
        return this;
    }

    public SwakHttpClient headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public SwakHttpClient addHeader(String key, String value) {
        if (MapUtil.isEmpty(this.headers)) {
            this.headers = MapUtil.newHashMap();
        }
        this.headers.put(key, value);
        return this;
    }

    public SwakHttpClient params(Map<String, Object> form) {
        this.contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        this.params = form;
        return this;
    }

    public SwakHttpClient addParam(String key, Object value) {
        this.contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        if (MapUtil.isEmpty(this.params)) {
            this.params = MapUtil.newHashMap();
        }
        this.params.put(key, value);
        return this;
    }

    public String post() throws Exception {

        Assert.notBlank(this.url, "url 不能为空");
        HttpPost request = new HttpPost(getUrl());
        if (MapUtil.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }
        request.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        Optional<StringEntity> entity = getPostEntity();
        if (entity.isPresent()) {
            request.setEntity(entity.get());
        }

        return executeRequest(httpClient, request);
    }

    private String getUrl() {
        return UriComponentsBuilder.fromHttpUrl(url).path(path).toUriString();
    }

    public String get() throws Exception {

        Assert.notBlank(this.url, "url 不能为空");

        URIBuilder uriBuilder = new URIBuilder(getUrl());
        if (MapUtil.isNotEmpty(params)) {
            params.forEach((k, v) -> uriBuilder.addParameter(k, v.toString()));
        }
        URI uri = uriBuilder.build();
        HttpGet request = new HttpGet(uri);

        if (MapUtil.isNotEmpty(headers)) {
            headers.forEach(request::addHeader);
        }

        return executeRequest(httpClient, request);

    }


    private String executeRequest(CloseableHttpClient httpClient, HttpRequestBase request) throws Exception {

        // 设置请求配置 - 请求路由数据先不告了
        HttpclientProperties.Param param = httpclientProperties.getParam(httpReqName);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(param.getConnectTimeout())
                .setSocketTimeout(param.getSocketTimeout())
                .setConnectionRequestTimeout(param.getConnectionRequestTimeout())
                .build();
        request.setConfig(requestConfig);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity responseEntity = response.getEntity();
            String result = Objects.isNull(responseEntity) ? StrUtil.EMPTY : EntityUtils.toString(responseEntity);

            if (statusCode == HttpStatus.SC_OK) {
                return result;
            }
            throw new SwakHttpClientException(statusCode, "Unexpected response" + result);

        }
    }

    private Optional<StringEntity> getPostEntity() throws Exception {

        if (isJsonType && StrUtil.isNotBlank(body)) {
            return Optional.of(new StringEntity(body));
        }
        List<BasicNameValuePair> params = new ArrayList<>();
        if (MapUtil.isNotEmpty(this.params)) {
            this.params.forEach((k, v) -> params.add(new BasicNameValuePair(k, v.toString())));
            return Optional.of(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
        }

        return Optional.empty();

    }
}
