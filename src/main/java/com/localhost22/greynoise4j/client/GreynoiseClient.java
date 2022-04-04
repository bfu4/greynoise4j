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
     * The key of the header for the api key.
     */
    private static final String KEY_HEADER = "key";

    /**
     * The key used when appending json data to a {@link #getQuickHostInformation(String...)} request.
     */
    private static final String MULTI_IPS_KEY = "ips";

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

    /**
     * Create a Greynoise enterprise client with the provided api key.
     * @param apiKey api key
     * @return enterprise client
     */
    public static GreynoiseClient enterprise(final String apiKey) {
        return enterprise(apiKey, new WebClientOptions());
    }

    /**
     * Create a new Greynoise community client with the provided api key and client options.
     * @param apiKey  api key
     * @param options client options
     * @return community client
     */
    public static GreynoiseClient community(final String apiKey, final WebClientOptions options) {
        return new GreynoiseClient(ClientType.COMMUNITY, apiKey, options);
    }

    /**
     * Create a new enterprise client with the provided api key and client options.
     * @param apiKey  api key
     * @param options client options
     * @return enterprise client
     */
    public static GreynoiseClient enterprise(final String apiKey, final WebClientOptions options) {
        return new GreynoiseClient(ClientType.ENTERPRISE, apiKey, options);
    }

    /**
     * The client's associated type.
     */
    private final ClientType clientType;

    /**
     * The client's api key, if provided.
     */
    private final String apiKey;

    /**
     * Create a Greynoise client of the specified type, with a provided api key
     * and client options.
     * @param clientType type of client
     * @param apiKey     the Greynoise API key
     * @param options    the client's options {@link io.vertx.ext.web.client.WebClientOptions}
     */
    @ConstructorProperties({"clientType"})
    private GreynoiseClient(final ClientType clientType, final String apiKey, final WebClientOptions options) {
        super(
                Vertx.vertx(),
                options.setUserAgent(USER_AGENT).setFollowRedirects(true)
        );
        this.clientType = clientType;
        this.apiKey = apiKey;
    }

    /**
     * Get the host information of a provided host using the community api.
     * If the client is an {@link ClientType#ENTERPRISE enterprise client}, this will throw an error.
     * An enterprise client is better off using {@link #getQuickHostInformation(String)}.
     * @param host host
     * @return host information
     */
    public Future<HostInformation> getHostInformation(final String host) {
        return this.request(HostInformation.class, Endpoint.COMMUNITY, host, HttpRequest::send);
    }

    /**
     * Get the host information using the enterprise api's {@code /noise/quick} route.
     * @param host host
     * @return information
     */
    public Future<QuickHostInformation> getQuickHostInformation(final String host) {
        return this.request(QuickHostInformation.class, Endpoint.NOISE_QUICK, host, HttpRequest::send);
    }

    /**
     * Get multiple hosts information using the enterprise api's {@code /noise/multi/quick} route
     * using a POST request.
     * @param hosts the hosts to get the information of.
     * @return host information
     */
    public Future<QuickHostInformation> getQuickHostInformation(@NotNull final String... hosts) {
        MultiMap form = MultiMap
                .caseInsensitiveMultiMap()
                .add(MULTI_IPS_KEY, getGson().toJson(hosts));
        return this.request(
                QuickHostInformation.class,
                Endpoint.NOISE_MULTI_QUICK,
                form
        );
    }

    /**
     * Get the hosts <em>context</em> information using the enterprise api's {@code /noise/context} route.
     * @param host host
     * @return context information, see {@link com.localhost22.greynoise4j.structs.HostContextInformation}.
     */
    public Future<HostContextInformation> getHostContextInformation(final String host) {
        return this.request(HostContextInformation.class, Endpoint.NOISE_CONTEXT, host, HttpRequest::send);
    }

    /**
     * Get the hosts <em>riot</em> information using the enterprise api's {@code /riot} route.
     * @param host host
     * @return context information, see {@link com.localhost22.greynoise4j.structs.HostRiotInformation}.
     */
    public Future<HostRiotInformation> getHostRiotInformation(final String host) {
        return this.request(HostRiotInformation.class, Endpoint.RIOT, host, HttpRequest::send);
    }

    /**
     * Request data from a specified endpoint without a query string and using a form.
     * @param type     type
     * @param endpoint endpoint
     * @param form     form
     * @param <T>      type constraint
     * @return a future of the requested data type.
     */
    public <T> Future<T> request(final Class<T> type, final Endpoint endpoint, final MultiMap form) {
        return this.request(type, endpoint, StringUtil.EMPTY_STRING, RequestHandler.getFormHandler(form));
    }

    /**
     * Request data from a specified endpoint with a query string and without a form.
     * @param type        type
     * @param endpoint    endpoint
     * @param queryString query string
     * @param handler     request handler that returns a {@link io.vertx.ext.web.client.HttpResponse}
     * @param <T>         return type constraint
     * @return data or null
     */
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
                .putHeader(KEY_HEADER, this.apiKey)
                .putHeader(HttpHeaders.ACCEPT.toString(), HttpHeaderValues.APPLICATION_JSON.toString());
        return this.handle(handler, request, type);
    }

    /**
     * Get the client's type.
     * @return client type
     */
    public ClientType getClientType() {
        return this.clientType;
    }

}
