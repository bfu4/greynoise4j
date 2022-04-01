package com.localhost22.greynoise4j.api;

import io.netty.util.internal.StringUtil;
import io.vertx.core.http.HttpMethod;

import java.beans.ConstructorProperties;

public enum Endpoint {

    COMMUNITY(StringUtil.EMPTY_STRING, HttpMethod.GET),
    NOISE_CONTEXT("/noise/context/", HttpMethod.GET),
    NOISE_QUICK("/noise/quick/", HttpMethod.GET),
    NOISE_MULTI_QUICK("/noise/multi/quick", HttpMethod.POST),
    RIOT("/riot/", HttpMethod.GET),
    META_METADATA("/meta/metadata/", HttpMethod.GET);

    private final HttpMethod method;
    private final String path;

    @ConstructorProperties({"path", "method"})
    Endpoint(final String path, final HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    public String path() {
        return this.path;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

}
