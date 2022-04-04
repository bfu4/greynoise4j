package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

/**
 * The RawHost class is a data structure class for "raw data" in a
 * {@link HostContextInformation} request.
 */
public final class RawHost implements GreynoiseResponse {

    /**
     * Hash data associated with the host.
     */
    private HashData[] hassh;

    /**
     * Scan data associated with the host.
     */
    private ScanData[] scan;

    /**
     * Web data associated with the host.
     */
    private WebData web;

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
     * Constructor to instantiate a blank information structure.
     */
    public RawHost() {
    }

    /**
     * Get the hash data.
     * @return hash data
     */
    public HashData[] getHassh() {
        return hassh;
    }

    /**
     * Get the scan data.
     * @return scan data
     */
    public ScanData[] getScan() {
        return scan;
    }

    /**
     * Get the web data.
     * @return web data
     */
    public WebData getWeb() {
        return web;
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
    public String[] getTags() {
        return tags;
    }

    static class HashData {

        String fingerprint;
        int port;

        HashData() {

        }

        public int getPort() {
            return port;
        }

        public String getFingerprint() {
            return fingerprint;
        }
    }

    static class ScanData {
        String protocol;
        int port;

        ScanData() {

        }

        public int getPort() {
            return port;
        }

        public String getProtocol() {
            return protocol;
        }

    }

    static class WebData {
        String[] paths;
        String[] useragents;

        WebData() {

        }

        public String[] getPaths() {
            return paths;
        }

        public String[] getUseragents() {
            return useragents;
        }
    }
}
