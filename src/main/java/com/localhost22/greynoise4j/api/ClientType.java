package com.localhost22.greynoise4j.api;

import java.beans.ConstructorProperties;
import java.util.List;

public enum ClientType {

    /**
     * The Greynoise community api client.
     * @see <a href="https://docs.greynoise.io/docs/using-the-greynoise-community-api">Greynoise Community API</a>
     */
    COMMUNITY("https://api.greynoise.io/v3/community/", Endpoint.COMMUNITY),

    /**
     * The Greynoise enterprise api client.
     * @see <a href="https://docs.greynoise.io/docs/using-the-greynoise-api">Greynoise Enterprise API</a>.
     */
    ENTERPRISE(
            "https://api.greynoise.io/v2/",
            Endpoint.NOISE_QUICK,
            Endpoint.NOISE_CONTEXT,
            Endpoint.RIOT,
            Endpoint.NOISE_MULTI_QUICK,
            Endpoint.META_METADATA
    );

    /**
     * The client type's allowed endpoints.
     */
    private final List<Endpoint> endpoints;

    /**
     * The client type's url.
     */
    private final String url;

    /**
     * Create a client type for the specified url and endpoints.
     * @param url       url
     * @param endpoints endpoints
     */
    @ConstructorProperties({"url", "endpoints"})
    ClientType(final String url, final Endpoint... endpoints) {
        this.url = url;
        this.endpoints = List.of(endpoints);
    }

    public String getUrl() {
        return url;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public Endpoint validate(final Endpoint endpoint) {
        if (this.allows(endpoint)) {
            return endpoint;
        }
        return null;
    }

    public boolean allows(final Endpoint endpoint) {
        return this.endpoints.contains(endpoint);
    }

}
