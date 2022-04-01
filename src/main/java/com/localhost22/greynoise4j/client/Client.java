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

public abstract class Client {

    private final WebClientOptions options;
    private final Gson gson;
    private final WebClient client;

    @ConstructorProperties({"options"})
    Client(final Vertx vertx, final WebClientOptions options) {
        this.options = options;
        this.client = WebClient.create(vertx, options);
        this.gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    }

    public final void close() {
        this.client.close();
    }

    protected <T> Future<T> handle(final RequestHandler<T> handler, final HttpRequest<Buffer> request, final Class<T> type) {
        Future<HttpResponse<Buffer>> result = request.send();
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

    public HttpRequest<Buffer> get(final String url) {
        return this.request(HttpMethod.GET, url);
    }

    public HttpRequest<Buffer> post(final String url) {
        return this.request(HttpMethod.POST, url);
    }

    public HttpRequest<Buffer> put(final String url) {
        return this.request(HttpMethod.PUT, url);
    }

    public HttpRequest<Buffer> delete(final String url) {
        return this.request(HttpMethod.DELETE, url);
    }

    public HttpRequest<Buffer> patch(final String url) {
        return this.request(HttpMethod.PATCH, url);
    }

    public HttpRequest<Buffer> request(final HttpMethod method, final String url) {
        return this.client.requestAbs(method, url);
    }

    public Gson getGson() {
        return gson;
    }

    public WebClient getClient() {
        return client;
    }

    public WebClientOptions getOptions() {
        return options;
    }

}
