package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The host context information is a data class used
 * for turning json data into an object from a
 * {@link com.localhost22.greynoise4j.client.GreynoiseClient#getHostContextInformation(String)}
 * request.
 */
public final class HostContextInformation implements GreynoiseResponse {

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
     * If the host has been seen.
     */
    private boolean seen;

    /**
     * If the host is spoofable.
     */
    private boolean spoofable;

    /**
     * The tags associated with the host.
     */
    private String[] tags;

    /**
     * TODO: What does this mean again?
     */
    private boolean vpn;

    /**
     * TODO: What does this mean again?
     */
    private String vpnService;

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
     * Get if the host has been seen.
     * @return if the host has been seen
     */
    public boolean wasSeen() {
        return seen;
    }

    /**
     * Get if the host is spoofable.
     * @return if the host is spoofable
     */
    public boolean isSpoofable() {
        return spoofable;
    }

    /**
     * TODO.
     * @return ?
     */
    public boolean hasVpn() {
        return vpn;
    }

    /**
     * TODO.
     * @return ?
     */
    public String getVpnService() {
        return vpnService;
    }

    /**
     * Get the host's tags.
     * @return tags
     */
    public List<String> getTags() {
        if (tags == null) {
            return Collections.emptyList();
        }
        return List.of(tags);
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
