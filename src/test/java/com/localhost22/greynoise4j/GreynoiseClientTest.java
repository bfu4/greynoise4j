// CHECKSTYLE:MagicNumber:OFF
package com.localhost22.greynoise4j;

import com.localhost22.greynoise4j.api.IllegalEndpointException;
import com.localhost22.greynoise4j.client.GreynoiseClient;
import com.localhost22.greynoise4j.structs.HostContextInformation;
import com.localhost22.greynoise4j.structs.HostInformation;
import com.localhost22.greynoise4j.structs.HostRiotInformation;
import com.localhost22.greynoise4j.structs.QuickHostInformation;
import io.vertx.core.Future;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * This test class contains tests for Greynoise API routes.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(VertxExtension.class)
public class GreynoiseClientTest {

    /**
     * The request timeout.
     */
    private static final int TIMEOUT = 15;

    /**
     * A known malicious address in the Greynoise API that is tagged with
     * a {@link #MIRAI_TAG mirai} tag.
     */
    private static final String MALICIOUS_ADDRESS = "8.129.210.247";

    /**
     * A known google address.
     */
    private static final String GOOGLE_ADDRESS = "66.102.6.228";

    /**
     * The known trust value for the known {@link #GOOGLE_ADDRESS google address}.
     */
    private static final String GOOGLE_KNOWN_TRUST = "1";

    /**
     * Greynoise's tag for Mirai.
     */
    private static final String MIRAI_TAG = "Mirai";

    /**
     * The community client instance.
     */
    private static GreynoiseClient communityClient;

    /**
     * The enterprise client instance.
     */
    private static GreynoiseClient enterpriseClient;

    /**
     * Pre-testing tasks.
     */
    @BeforeAll
    public static void setup() {
        communityClient = GreynoiseClient.community();
        enterpriseClient = GreynoiseClient.enterprise(System.getenv("ENTERPRISE_KEY"));
    }

    /**
     * Test that the community client can get {@link HostInformation information} about an ip using the
     * {@link com.localhost22.greynoise4j.api.Endpoint#COMMUNITY community} route.
     * @param ctx test context
     * @throws InterruptedException if the request is interrupted.
     */
    @DisplayName("Community API | Test IP (8.129.210.247) Query")
    @Order(1)
    @Test
    public void testWellKnownIpQuery(final VertxTestContext ctx) throws InterruptedException {
        Future<HostInformation> response = communityClient.getHostInformation(MALICIOUS_ADDRESS);

        response.onSuccess((data) -> {
            ctx.completeNow();
            Assertions.assertNotNull(data);
            Assertions.assertEquals(MALICIOUS_ADDRESS, data.getIp());
        });

        Assertions.assertTrue(ctx.awaitCompletion(TIMEOUT, TimeUnit.SECONDS));
        if (ctx.failed()) {
            Assertions.fail();
        }
    }

    /**
     * Test that the community client throws an {@link IllegalEndpointException exception} when
     * trying to access enterprise routes.
     */
    @DisplayName("Community API | Test Community is NOT Enterprise")
    @Order(2)
    @Test
    public void testCommunityIsNotEnterprise() {
        Assertions.assertThrows(
                IllegalEndpointException.class,
                () -> communityClient.getQuickHostInformation(GOOGLE_ADDRESS),
                "not allowed"
        );
    }

    /**
     * Test that the enterprise client gets correct responses when making a
     * {@link com.localhost22.greynoise4j.api.Endpoint#NOISE_CONTEXT context} query
     * to a known ip address.
     * @param ctx test context
     * @throws InterruptedException if the request is interrupted
     */
    @DisplayName("Enterprise API | Test Context (8.129.210.247) Query")
    @Order(3)
    @Test
    public void testWellKnownContextQuery(final VertxTestContext ctx) throws InterruptedException {
        Future<HostContextInformation> response = enterpriseClient.getHostContextInformation(MALICIOUS_ADDRESS);

        response.onSuccess((data) -> {
            ctx.completeNow();
            Assertions.assertNotNull(data);
            Assertions.assertTrue(data.getTags().contains(MIRAI_TAG));
        });

        Assertions.assertTrue(ctx.awaitCompletion(TIMEOUT, TimeUnit.SECONDS));
        if (ctx.failed()) {
            Assertions.fail();
        }
    }

    /**
     * Test that the enterprise client gets correct responses when making a
     * {@link com.localhost22.greynoise4j.api.Endpoint#RIOT riot} request to a known
     * ip address.
     * @param ctx test context
     * @throws InterruptedException if the request is interrupted
     */
    @DisplayName("Enterprise API | Test Riot (66.102.6.228) Query")
    @Order(4)
    @Test
    public void testWellKnownRiotQuery(final VertxTestContext ctx) throws InterruptedException {
        Future<HostRiotInformation> response = enterpriseClient.getHostRiotInformation(GOOGLE_ADDRESS);

        response.onSuccess((data) -> {
            ctx.completeNow();
            Assertions.assertNotNull(data);
            Assertions.assertEquals(data.getTrustLevel(), GOOGLE_KNOWN_TRUST);
        });

        Assertions.assertTrue(ctx.awaitCompletion(TIMEOUT, TimeUnit.SECONDS));
        if (ctx.failed()) {
            Assertions.fail();
        }
    }

    /**
     * Test that the enterprise client gets the correct when making a
     * {@link com.localhost22.greynoise4j.api.Endpoint#NOISE_MULTI_QUICK multi/quick}
     * request to known ip addresses.
     * @param ctx test context
     * @throws InterruptedException if the request is interrupted
     */
    @DisplayName("Enterprise API | Test Quick Multi (66.102.6.228, 8.129.210.247) Query")
    @Order(5)
    @Test
    public void testQuickMultiQuery(final VertxTestContext ctx) throws InterruptedException {
        Future<QuickHostInformation[]> response = enterpriseClient.getQuickHostInformation(GOOGLE_ADDRESS, MALICIOUS_ADDRESS);

        response.onSuccess((data) -> {
            ctx.completeNow();
            Assertions.assertNotNull(data);
            Assertions.assertEquals(data.length, 2);
            Assertions.assertTrue(Arrays.stream(data).allMatch((info) -> {
                final String ip = info.getIp();
                return ip.equals(GOOGLE_ADDRESS) || ip.equals(MALICIOUS_ADDRESS);
            }));
        });

        Assertions.assertTrue(ctx.awaitCompletion(TIMEOUT, TimeUnit.SECONDS));
        if (ctx.failed()) {
            Assertions.fail();
        }
    }

}
