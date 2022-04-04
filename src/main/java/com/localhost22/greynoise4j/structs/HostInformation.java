package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

/**
 * The host information is a data class used
 * for turning json data into an object from a
 * {@link com.localhost22.greynoise4j.client.GreynoiseClient#getHostInformation(String)}
 * request.
 */
public final class HostInformation implements GreynoiseResponse {

    /**
     * The host's ip address.
     */
    private String ip;

    /**
     * Whether the host has been active in the past 90 days.
     */
    private boolean noise;

    /**
     * Whether the host is in the <a href="https://docs.greynoise.io/docs/riot-data">RIOT</a>
     * data set.
     */
    private boolean riot;

    /**
     * The classification of the host.
     */
    private String classification;

    /**
     * The name of the host.
     */
    private String name;

    /**
     * The visualizer link of th host.
     */
    private String link;

    /**
     * When the host was last seen.
     */
    private String lastSeen;

    /**
     * A message associated with the request, such as "Success".
     */
    private String message;

    /**
     * Constructor to instantiate a blank information structure.
     */
    public HostInformation() {
    }

    /**
     * Get the host's classification.
     * @return classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Get the host's ip address.
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get when the host was last seen.
     * @return last seen.
     */
    public String getLastSeen() {
        return lastSeen;
    }

    /**
     * Get the host's visualizer link.
     * @return link
     */
    public String getLink() {
        return link;
    }

    /**
     * Get the message associated with the response.
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the name of the host.
     * @return host
     */
    public String getName() {
        return name;
    }

    /**
     * Get whether the host has been active in the past 90 days.
     * @return if the host has noise
     */
    public boolean hasNoise() {
        return noise;
    }

    /**
     * Get whether the host is in the <a href="https://docs.greynoise.io/docs/riot-data">RIOT</a>
     * data set.
     * @return if the host is in the riot data set
     */
    public boolean isRiot() {
        return riot;
    }
}
