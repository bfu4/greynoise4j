package com.localhost22.greynoise4j.structs;

import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import org.jetbrains.annotations.NotNull;

public final class GreynoiseException extends RuntimeException {

    public static GreynoiseException rateLimit(final HttpResponse<Buffer> buffer) {
        return create("Rated limited!", buffer);
    }

    public static GreynoiseException create(final String message, @NotNull final HttpResponse<Buffer> response) {
        return new GreynoiseException(message, response);
    }

    protected GreynoiseException(final String message, @NotNull final HttpResponse<Buffer> response) {
        super(message + response.bodyAsString());
    }

}
