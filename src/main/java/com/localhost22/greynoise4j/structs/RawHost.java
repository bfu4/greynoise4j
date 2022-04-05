package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

import java.util.List;

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


    public static class HashData {

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

    public static class ScanData {
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
