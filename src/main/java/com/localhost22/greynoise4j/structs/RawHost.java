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
     * HashData is a subclass for data inside a host's raw data consisting
     * of known fingerprints for associated ports.
     */
    public static final class HashData {

        /**
         * The fingerprint.
         */
        private String fingerprint;

        /**
         * The port.
         */
        private int port;

        /**
         * Constructor to instantiate a blank information structure.
         */
        HashData() {
        }

        /**
         * Get the port.
         * @return port
         */
        public int getPort() {
            return port;
        }

        /**
         * Get the fingerprint.
         * @return fingerprint
         */
        public String getFingerprint() {
            return fingerprint;
        }

    }

    /**
     * ScanData is a subclass consisting of a host's raw scan data.
     * It has an associated protocol with a port.
     */
    public static final class ScanData {

        /**
         * The protocol.
         */
        private String protocol;

        /**
         * The port.
         */
        private int port;

        /**
         * Constructor to instantiate a blank information structure.
         */
        ScanData() {
        }

        /**
         * Get the port.
         * @return port
         */
        public int getPort() {
            return port;
        }

        /**
         * Get the protocol.
         * @return protocol
         */
        public String getProtocol() {
            return protocol;
        }

    }

    /**
     * WebData is a subclass consisting of a host's raw <em>web</em> data.
     * It may identify known paths and user agents.
     */
    public static final class WebData {

        /**
         * The paths.
         */
        private String[] paths;

        /**
         * The user agents.
         */
        private String[] useragents;

        /**
         * Constructor to instantiate a blank information structure.
         */
        WebData() {
        }

        /**
         * Get the paths.
         * @return paths
         */
        public String[] getPaths() {
            return paths;
        }

        /**
         * Get the user agents.
         * @return user agents
         */
        public String[] getUseragents() {
            return useragents;
        }

    }
}
