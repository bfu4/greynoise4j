package com.localhost22.greynoise4j.structs;

import java.util.List;

/**
 * The host context information is a data class used
 * for turning json data into an object from a
 * {@link com.localhost22.greynoise4j.client.GreynoiseClient#getHostContextInformation(String)}
 * request.
 */
public final class HostContextInformation {

    /**
     * The actor.
     */
    private String actor;

    /**
     * Whether this host is a bot.
     */
    private boolean bot;

    /**
     * The host's classification.
     */
    private String classification;

    /**
     * CVEs that the host has been detected to exploit.
     */
    private String[] cve;

    /**
     * When this host was <em>first seen</em>.
     */
    private String firstSeen;

    /**
     * The ip address.
     */
    private String ip;

    /**
     * When this host was last seen.
     */
    private String lastSeen;

    /**
     * Host metadata.
     */
    private HostMetadata metadata;

    /**
     * Host raw data.
     */
    private RawHost rawData;

    /**
     * Constructor to instantiate a blank information structure.
     */
    public HostContextInformation() {
    }

    /**
     * Get the actor.
     * @return actor
     */
    public String getActor() {
        return actor;
    }


    /**
     * Get whether this host is a bot.
     * @return whether the host is a bot
     */
    public boolean isBot() {
        return bot;
    }

    /**
     * Get the host's classification.
     * @return classification
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Get a list of cves the host is known th exploit.
     * @return cves
     */
    public List<String> getCves() {
        return List.of(cve);
    }

    /**
     * Get when the host was first seen.
     * @return first seen
     */
    public String getFirstSeen() {
        return firstSeen;
    }

    /**
     * Get the hosts ip.
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get when the host was last seen.
     * @return last seen
     */
    public String getLastSeen() {
        return lastSeen;
    }

    /**
     * Get the host's metadata.
     * @return metadata
     */
    public HostMetadata getMetadata() {
        return metadata;
    }

    /**
     * Get the host's raw data.
     * @return raw data
     */
    public RawHost getRawData() {
        return rawData;
    }

}
