package com.localhost22.greynoise4j.client;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;

/**
 * Request handler is a functional interface used to describe an action
 * on a request that should handle the actual sending of the request,
 * such as {@link HttpRequest#send()}, to return a future http response.
 * This interface is necessary when form data is used, so that it is possible to utilise
 * either a lambda function of {@link HttpRequest#send()} or a created instance of
 * a request handler specific to the form used in the request, using {@link #getFormHandler(MultiMap)}.
 * This is utilised in {@link GreynoiseClient#getMultiQuickHostInformation(String...)}, where a post
 * request is utilised and the data is sent in a multimap form.
 */
@FunctionalInterface
public interface RequestHandler {

    /**
     * An ease-of-access method for creating a form handler for a specific form.
     * @param form form
     * @return form handler
     */
    static RequestHandler getFormHandler(final MultiMap form) {
        return (req) -> req.sendForm(form);
    }

    Future<HttpResponse<Buffer>> handle(HttpRequest<Buffer> request);

}
