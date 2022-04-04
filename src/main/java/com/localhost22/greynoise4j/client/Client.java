package com.localhost22.greynoise4j.client;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.localhost22.greynoise4j.structs.GreynoiseException;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import java.beans.ConstructorProperties;

/**
 * The client is an abstract definition of an http
 * client that uses custom options, runs on <em>vertx</em>,
 * and uses <em>Gson</em> for deserialization.
 */
public abstract class Client {

    /**
     * The client's options.
     */
    private final WebClientOptions options;

    /**
     * The Gson instance.
     */
    private final Gson gson;

    /**
     * The internal, vertx {@link WebClient WebClient}.
     */
    private final WebClient client;

    /**
     * Create a new client with the provided vertx instance and options.
     * @param vertx   vertx instance
     * @param options options
     */
    @ConstructorProperties({"options"})
    Client(final Vertx vertx, final WebClientOptions options) {
        this.options = options;
        this.client = WebClient.create(vertx, options);
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    }

    /**
     * Close the client.
     */
    public final void close() {
        this.client.close();
    }

    /**
     * Handle a request to return the response data as an object {@see type}.
     * @param handler the request handler, deals with how the request is sent
     * @param request the actual request
     * @param type    the type of data to return
     * @param <T>     the type constraint for the return data and handler
     * @return data or null.
     * @throws GreynoiseException if there was an exception, such as a rate limit
     */
    protected <T> Future<T> handle(final RequestHandler handler, final HttpRequest<Buffer> request, final Class<T> type) {
        Future<HttpResponse<Buffer>> result = handler.handle(request);
        if (result.failed()) {
            return null;
        }
        return result.map((resp) -> {
            if (resp.statusCode() == HttpResponseStatus.TOO_MANY_REQUESTS.code()) {
                throw GreynoiseException.rateLimit(resp);
            }
            // Gson never fails.
            return gson.fromJson(resp.bodyAsString(), type);
        });
    }

    /**
     * Using {@link WebClient#requestAbs(HttpMethod, String)} in the background,
     * use the http GET method to get data from the specified url.
     * @param url url to get data from
     * @return http request
     */
    public HttpRequest<Buffer> get(final String url) {
        return this.request(HttpMethod.GET, url);
    }

    /**
     * Using {@link WebClient#requestAbs(HttpMethod, String)} in the background,
     * use the http POST method to post data to the specified url.
     * @param url url to post data to
     * @return http request
     */
    public HttpRequest<Buffer> post(final String url) {
        return this.request(HttpMethod.POST, url);
    }

    /**
     * Using {@link WebClient#requestAbs(HttpMethod, String)} in the background,
     * use the http PUT method to put data to the specified url.
     * @param url url to put data to
     * @return http request
     */
    public HttpRequest<Buffer> put(final String url) {
        return this.request(HttpMethod.PUT, url);
    }

    /**
     * Using {@link WebClient#requestAbs(HttpMethod, String)} in the background,
     * use the http DELETE method at the specified url.
     * @param url url to send a DELETE request to
     * @return http request
     */
    public HttpRequest<Buffer> delete(final String url) {
        return this.request(HttpMethod.DELETE, url);
    }

    /**
     * Using {@link WebClient#requestAbs(HttpMethod, String)} in the background,
     * use the http PATCH method at the specified url.
     * @param url url to send a PATCH request to
     * @return http request
     */
    public HttpRequest<Buffer> patch(final String url) {
        return this.request(HttpMethod.PATCH, url);
    }

    /**
     * A wrapper function for {@link WebClient#requestAbs(HttpMethod, String)},
     * to make requesting easier when [this] publicly accessible method is used.
     * @param method the http method
     * @param url    the url to request
     * @return an http request
     */
    public HttpRequest<Buffer> request(final HttpMethod method, final String url) {
        return this.client.requestAbs(method, url);
    }

    /**
     * Get the gson instance.
     * @return gson
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * Get the vertx web client.
     * @return web client
     */
    public WebClient getClient() {
        return client;
    }

    /**
     * Get the client's options.
     * @return options
     */
    public WebClientOptions getOptions() {
        return options;
    }

}
