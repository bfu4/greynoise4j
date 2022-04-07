// CHECKSTYLE:TodoComment:OFF
package com.localhost22.greynoise4j.api;

import io.netty.util.internal.StringUtil;
import io.vertx.core.http.HttpMethod;

import java.beans.ConstructorProperties;

/**
 * The enumerator {@code Endpoint} standardizes
 * the known / allowed Greynoise API endpoints, and the
 * associated {@link HttpMethod HttpMethods}.
 */
public enum Endpoint {

    /**
     * The Greynoise Community <em>only</em> endpoint.
     * This will get data that is serializable into a
     * {@link com.localhost22.greynoise4j.structs.HostInformation} instance.
     */
    COMMUNITY(StringUtil.EMPTY_STRING, HttpMethod.GET),

    /**
     * The Greynoise Enterprise noise/context endpoint.
     * This will get data that is serializable into a
     * {@link com.localhost22.greynoise4j.structs.HostContextInformation} instance.
     */
    NOISE_CONTEXT("/noise/context/", HttpMethod.GET),

    /**
     * The Greynoise Enterprise noise/quick endpoint.
     * This will get data that is serializable into a
     * {@link com.localhost22.greynoise4j.structs.QuickHostInformation} instance.
     */
    NOISE_QUICK("/noise/quick/", HttpMethod.GET),

    /**
     * The Greynoise Enterprise noise/multi/quick endpoint, which
     * will get multiple ips with data returned from {@link #NOISE_QUICK}.
     * This returns data that is serializable into a {@link com.localhost22.greynoise4j.structs.QuickHostInformation}
     * array, specified by a type: {@code QuickHostInformation.class}.
     */
    NOISE_MULTI_QUICK("/noise/multi/quick", HttpMethod.POST),

    /**
     * The Greynoise Enterprise noise/multi/context endpoint, which
     * will get multiple ips with data returned from {@link #NOISE_CONTEXT}.
     * This returns data that is serializable into a {@link com.localhost22.greynoise4j.structs.HostContextInformation}
     * array, specified by a type: {@code HostContextInformation.class}.
     */
    NOISE_MULTI_CONTEXT("/noise/multi/context", HttpMethod.POST),

    /**
     * The Greynoise Enterprise riot endpoint.
     * This will return data that is serializable into a
     * {@link com.localhost22.greynoise4j.structs.HostRiotInformation} instance.
     */
    RIOT("/riot/", HttpMethod.GET),

    /**
     * The Greynoise Enterprise /meta/metadata endpoint, which should return
     * something along the lines of {@link com.localhost22.greynoise4j.structs.HostMetadata}.
     * TODO: This is an assumption, I don't have an enterprise license, so I cannot check this.
     */
    META_METADATA("/meta/metadata/", HttpMethod.GET);

    /**
     * The endpoint's associated allowed http method.
     */
    private final HttpMethod method;

    /**
     * The path, that is used in a {@link ClientType ClientType's} api url.
     * The data is used in the following format:
     * {@code
     * {this.method}  {this.path}    HTTP/X
     * Host: {ClientType#url}
     * }
     */
    private final String path;

    /**
     * An 'Endpoint' is a known constant consisting of a path
     * and an allowed http method. All endpoint's confine to
     * known Greynoise api routes.
     * @param path   path
     * @param method method
     */
    @ConstructorProperties({"path", "method"})
    Endpoint(final String path, final HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    /**
     * Get the path.
     * @return path
     */
    public String path() {
        return this.path;
    }

    /**
     * Get the allowed method.
     * @return method
     */
    public HttpMethod getMethod() {
        return this.method;
    }

}
