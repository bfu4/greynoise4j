package com.localhost22.greynoise4j.api;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import org.jetbrains.annotations.NotNull;

/**
 * The Greynoise exception is an extension of a runtime exception thrown
 * when there is an api error.
 */
public final class GreynoiseException extends RuntimeException {

    /**
     * Create a rate limit exception with the specified http response.
     * @param resp the http response
     * @return greynoise exception to throw
     */
    public static GreynoiseException rateLimit(final HttpResponse<Buffer> resp) {
        return create("Rated limited!", resp);
    }

    /**
     * Create an exception with the specified message and http response.
     * @param message  message
     * @param response response
     * @return a new Greynoise exception to throw
     */
    public static GreynoiseException create(final String message, @NotNull final HttpResponse<Buffer> response) {
        return new GreynoiseException(message, response);
    }

    /**
     * Create a new Greynoise exception with the specified message and http response.
     * This is typically instantiated to throw during a
     * {@link com.localhost22.greynoise4j.client.Client#request(io.vertx.core.http.HttpMethod, String)}.
     * @param message  message
     * @param response response
     */
    GreynoiseException(final String message, @NotNull final HttpResponse<Buffer> response) {
        super(message + response.bodyAsString());
    }

}
