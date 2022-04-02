package com.localhost22.greynoise4j.api;

/**
 * The IllegalEndpointException is an exception thrown when
 * a {@link com.localhost22.greynoise4j.client.GreynoiseClient} tries to access
 * a disallowed api root.
 */
public class IllegalEndpointException extends RuntimeException {

    /**
     * Create an illegal endpoint exception for the specified endpoint.
     * @param endpoint the endpoint that was attempted to be used.
     */
    public IllegalEndpointException(final Endpoint endpoint) {
        super(
                String.format("usage of endpoint %s not allowed!", endpoint.path())
        );
    }

}
