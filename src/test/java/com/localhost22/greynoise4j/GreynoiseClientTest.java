package com.localhost22.greynoise4j;

import com.google.gson.GsonBuilder;
import com.localhost22.greynoise4j.api.Endpoint;
import com.localhost22.greynoise4j.structs.HostContextInformation;
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

    private static GreynoiseClient communityClient;

    private static GreynoiseClient enterpriseClient;

    private static int timeout;

    private static String query;

    private static String miraiTag;

    @BeforeAll
    public static void setup() {
        communityClient = GreynoiseClient.community();
        enterpriseClient = GreynoiseClient.enterprise(System.getenv("ENTERPRISE_KEY"));
        timeout = 15;
        query = "8.129.210.247";
        miraiTag = "Mirai";
    }

    @Test
    @DisplayName("Community API | Test IP (8.129.210.247) Query")
    public void testWellKnownIpQuery() throws InterruptedException {
        VertxTestContext ctx = new VertxTestContext();
        Future<HostInformation> response = communityClient.getHostInformation(query);

        response.onSuccess((data) -> {
            ctx.completeNow();
            Assertions.assertNotNull(data);
            Assertions.assertEquals(query, data.getIp());
        });

        Assertions.assertTrue(ctx.awaitCompletion(timeout, TimeUnit.SECONDS));
        if (ctx.failed()) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Enterprise API | Test Context (8.129.210.247) Query")
    public void testWellKnownContextQuery() throws InterruptedException {
        VertxTestContext ctx = new VertxTestContext();
        Future<HostContextInformation> response = enterpriseClient.getHostContextInformation(query);
        response.onSuccess((data) -> {
            ctx.completeNow();
            Assertions.assertNotNull(data);
            Assertions.assertTrue(data.getTags().contains(miraiTag));
        });

        Assertions.assertTrue(ctx.awaitCompletion(timeout, TimeUnit.SECONDS));
        if (ctx.failed()) {
            Assertions.fail();
        }
    }

}
