package com.localhost22.greynoise4j;

import com.localhost22.greynoise4j.client.Client;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

/**
 * This test class contains test cases for a wrapped {@link Vertx Vertx} client.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(VertxExtension.class)
public final class HttpClientTest {

    /**
     * The request timeout.
     */
    private static final int TIMEOUT = 15;

    /**
     * The instance of a basic http client.
     */
    private static Client client;

    /**
     * Pre-testing tasks.
     */
    @BeforeAll
    public static void setup() {
        client = new ClientImpl(Vertx.vertx(), new WebClientOptions());
    }

    /**
     * Test that the http client can make a simple HTTP(s) request to {@code google.com}
     * and receive a {@link HttpStatus#SC_OK 200 OK}.
     * @param ctx test context
     * @throws InterruptedException if the request was interrupted
     */
    @DisplayName("Test HTTP GET Request")
    @Order(1)
    @Test
    public void testHttpGet(final VertxTestContext ctx) throws InterruptedException {
        client.get("https://google.com").send().onSuccess((res) -> {
            ctx.completeNow();
            Assertions.assertEquals(res.statusCode(), HttpStatus.SC_OK);
        });

        Assertions.assertTrue(ctx.awaitCompletion(TIMEOUT, TimeUnit.SECONDS));
    }

    /**
     * The test case's {@link Client http client} implementation.
     */
    private static class ClientImpl extends Client {

        /**
         * Create a new client with the provided vertx instance and options.
         * @param vertx   vertx instance
         * @param options options
         */
        ClientImpl(final Vertx vertx, final WebClientOptions options) {
            super(vertx, options);
        }

    }
}
