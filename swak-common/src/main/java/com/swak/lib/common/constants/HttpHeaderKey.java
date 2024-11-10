package com.swak.lib.common.constants;

import com.swak.lib.common.tools.CollectionTools;

import java.util.Set;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author: ljq
 * @date: 2024/11/10
 */
public interface HttpHeaderKey {

    String TRACE_ID_HEADER = "X-B3-TraceId";

    String TOKEN = "Token";

    String APPID = "AppId";

    // 必须记录的
    Set<String> DEFAULT_NEED_HEADERS = CollectionTools.newToLowerCaseHashSet(APPID, TRACE_ID_HEADER, TOKEN, AUTHORIZATION);
}
