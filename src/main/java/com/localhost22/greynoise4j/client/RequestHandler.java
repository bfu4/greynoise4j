package com.localhost22.greynoise4j.client;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;

@FunctionalInterface
public interface RequestHandler<T> {

    static <S> RequestHandler<S> getFormHandler(final MultiMap form) {
        return (req) -> req.sendForm(form);
    }

    Future<HttpResponse<T>> handle(HttpRequest<T> request);

}
