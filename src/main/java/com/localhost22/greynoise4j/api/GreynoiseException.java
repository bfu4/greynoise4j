package com.localhost22.greynoise4j.api;

import io.netty.util.internal.StringUtil;
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
     * Create a Greynoise exception with a specified format and objects to format.
     * @param fmt     format
     * @param objects objects
     * @return exception
     */
    public static GreynoiseException create(final String fmt, final Object... objects) {
        return new GreynoiseException(String.format(fmt, objects), StringUtil.EMPTY_STRING);
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
        this(message, response.bodyAsString());
    }

    /**
     * Create a new Greynoise exception with the specified message and addition.
     * @param message  message
     * @param addition addition
     */
    GreynoiseException(final String message, final String addition) {
        super(message + addition);
    }

}
