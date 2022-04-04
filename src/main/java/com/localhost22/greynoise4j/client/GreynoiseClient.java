package com.localhost22.greynoise4j.client;

import com.localhost22.greynoise4j.api.ClientType;
import com.localhost22.greynoise4j.api.Endpoint;
import com.localhost22.greynoise4j.api.IllegalEndpointException;
import com.localhost22.greynoise4j.structs.HostContextInformation;
import com.localhost22.greynoise4j.structs.HostInformation;
import com.localhost22.greynoise4j.structs.HostRiotInformation;
import com.localhost22.greynoise4j.structs.QuickHostInformation;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.util.internal.StringUtil;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClientOptions;
import org.jetbrains.annotations.NotNull;

import java.beans.ConstructorProperties;

/**
 * The Greynoise Client is an extension of the {@link Client},
 * in which the methods are specific to the Greynoise API.
 */
public final class GreynoiseClient extends Client {

    /**
     * The known constant for the name of the client's user agent.
     */
    private static final String USER_AGENT = "greynoise4j/1.0";

    /**
     * Create a Greynoise community client that does not use an API key.
     * @return community client
     */
    public static GreynoiseClient community() {
        return community(StringUtil.EMPTY_STRING);
    }

    /**
     * Create a Greynoise community client that does use an api key.
     * @param apiKey api key
     * @return community client
     */
    public static GreynoiseClient community(final String apiKey) {
        return community(apiKey, new WebClientOptions());
    }

    public static GreynoiseClient enterprise(final String apiKey) {
        return enterprise(apiKey, new WebClientOptions());
    }

    public static GreynoiseClient community(final String apiKey, final WebClientOptions options) {
        return new GreynoiseClient(ClientType.COMMUNITY, apiKey, options);
    }

    public static GreynoiseClient enterprise(final String apiKey, final WebClientOptions options) {
        return new GreynoiseClient(ClientType.ENTERPRISE, apiKey, options);
    }

    private final ClientType clientType;
    private final String apiKey;

    @ConstructorProperties({"clientType", "apiKey"})
    private GreynoiseClient(final ClientType clientType, final String apiKey, WebClientOptions options) {
        super(
                Vertx.vertx(),
                options.setUserAgent(USER_AGENT).setFollowRedirects(true)
        );
        this.clientType = clientType;
        this.apiKey = apiKey;
        // Setup a shutdown hook.
        Thread hook = new Thread(this::close);
        Runtime.getRuntime().addShutdownHook(hook);
    }

    public Future<HostInformation> getHostInformation(final String host) {
        return this.request(HostInformation.class, Endpoint.COMMUNITY, host, HttpRequest::send);
    }

    public Future<QuickHostInformation> getQuickHostInformation(final String host) {
        return this.request(QuickHostInformation.class, Endpoint.NOISE_QUICK, host, HttpRequest::send);
    }

    public Future<QuickHostInformation> getMultiQuickHostInformation(@NotNull final String... hosts) {
        MultiMap form = MultiMap
                .caseInsensitiveMultiMap()
                .add("ips", getGson().toJson(hosts));
        // TODO: I don't have a key, this might not work?. Find a way to test.
        return this.request(
                QuickHostInformation.class,
                Endpoint.NOISE_MULTI_QUICK,
                form
        );
    }

    public Future<HostContextInformation> getHostContextInformation(final String host) {
        return this.request(HostContextInformation.class, Endpoint.NOISE_CONTEXT, host, HttpRequest::send);
    }

    public Future<HostRiotInformation> getHostRiotInformation(final String host) {
        return this.request(HostRiotInformation.class, Endpoint.RIOT, host, HttpRequest::send);
    }

    public <T> Future<T> request(final Class<T> type, final Endpoint endpoint, final MultiMap form) {
        return this.request(type, endpoint, StringUtil.EMPTY_STRING, RequestHandler.getFormHandler(form));
    }

    public <T> Future<T> request(final Class<T> type,
                                 final Endpoint endpoint,
                                 final String queryString,
                                 final RequestHandler handler) {
        final Endpoint requestEndpoint = this.clientType.validate(endpoint);
        if (requestEndpoint == null) {
            throw new IllegalEndpointException(endpoint);
        }
        HttpRequest<Buffer> request = super
                .request(requestEndpoint.getMethod(), this.clientType.getUrl() + requestEndpoint.path() + queryString)
                .putHeader("key", this.apiKey)
                .putHeader(HttpHeaders.ACCEPT.toString(), HttpHeaderValues.APPLICATION_JSON.toString());
        return this.handle(handler, request, type);
    }

    public ClientType getClientType() {
        return this.clientType;
    }

}
