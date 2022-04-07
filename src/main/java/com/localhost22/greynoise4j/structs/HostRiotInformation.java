package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

/**
 * HostRiotInformation is data collected from the enterprise
 * {@link com.localhost22.greynoise4j.api.Endpoint#RIOT} route.
 * This should correlate with the <a href="https://docs.greynoise.io/docs/riot-data">RIOT</a> dataset.
 */
public final class HostRiotInformation implements GreynoiseResponse {

    /**
     * The host's ip.
     */
    private String ip;

    /**
     * Whether the host is in the riot data set.
     */
    private boolean riot;

    /**
     * The host's category.
     */
    private String category;

    /**
     * The host's name.
     */
    private String name;

    /**
     * The host's description.
     */
    private String description;

    /**
     * The host's explanation.
     */
    private String explanation;

    /**
     * When the host was last updated.
     */
    private String lastUpdated;

    /**
     * The host's logo url.
     */
    private String logoUrl;

    /**
     * The host's reference.
     */
    private String reference;

    /**
     * The host's trust level.
     * For a host like {@code google.com}, this may be (1 - Reasonably Ignore).
     */
    private String trustLevel;

    /**
     * Constructor to instantiate a blank information structure.
     */
    public HostRiotInformation() {
    }

    /**
     * Get the host's ip.
     * @return ip address
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get whether the host is in the riot data set.
     * @return whether the host is in the riot data set
     */
    public boolean isRiot() {
        return riot;
    }

    /**
     * Get the host's category.
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the host's name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the host's description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the host's explanation.
     * @return explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Get when the host was last updated.
     * @return last updated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Get the host's logo url.
     * @return logo url
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * Get the host's reference.
     * @return reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Get the host's trust level.
     * @return trust level
     */
    public String getTrustLevel() {
        return trustLevel;
    }

}
