package com.localhost22.greynoise4j.api;

public class IllegalEndpointException extends RuntimeException {

    public IllegalEndpointException(final Endpoint endpoint) {
        super(
                String.format("usage of endpoint %s not allowed!", endpoint.path())
        );
    }

}
