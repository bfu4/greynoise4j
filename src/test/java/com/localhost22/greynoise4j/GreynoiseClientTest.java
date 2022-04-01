package com.localhost22.greynoise4j;

import com.localhost22.greynoise4j.api.Endpoint;
import com.localhost22.greynoise4j.structs.HostInformation;
import com.localhost22.greynoise4j.client.GreynoiseClient;
import io.vertx.core.Future;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

@ExtendWith(VertxExtension.class)
public class GreynoiseClientTest {

    private static GreynoiseClient client;

    private static String query;

    @BeforeAll
    public static void setup() {
        client = GreynoiseClient.community();
        query = "8.8.8.8";
    }

    @Test
    @DisplayName("Community API | Test IP (8.8.8.8) Query")
    public void testWellKnownIpQuery() throws InterruptedException {
        VertxTestContext ctx = new VertxTestContext();
        Future<HostInformation> response = client.request(HostInformation.class, Endpoint.COMMUNITY, query);

        response.onSuccess((data) -> {
            ctx.completeNow();
            Assertions.assertEquals(query, data.getIp());
            System.out.println(data.getLastSeen());
        });

        Assertions.assertTrue(ctx.awaitCompletion(15, TimeUnit.SECONDS));
        if (ctx.failed()) {
            Assertions.fail();
        }
    }

}
