package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

/**
 * QuickHostInformation is a small data class that may be a response from
 * the enterprise {@link com.localhost22.greynoise4j.api.Endpoint#NOISE_QUICK} route.
 */
public final class QuickHostInformation implements GreynoiseResponse {

    /**
     * The host's ip.
     */
    private String ip;

    /**
     * A Greynoise API response code.
     */
    private String code;

    /**
     * Whether the host has noise.
     */
    private boolean noise;

    /**
     * Whether the host is in the riot data set.
     */
    private boolean riot;

    /**
     * Constructor to instantiate a blank information structure.
     */
    public QuickHostInformation() {
    }

    /**
     * Get the host's ip.
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get the response's associated code.
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get whether the host is in the riot dataset.
     * @return if the host is in the riot data set
     */
    public boolean isRiot() {
        return riot;
    }

    /**
     * Get whether the host has been active in the last 90 days (has noise).
     * @return if the host "has noise"
     */
    public boolean hasNoise() {
        return noise;
    }

}
