package com.localhost22.greynoise4j.structs;

public final class RawHost {

    private HashData[] hassh;
    private ScanData[] scan;
    private WebData web;
    private boolean seen;
    private boolean spoofable;
    private String[] tags;
    private boolean vpn;
    private String vpnService;

    public RawHost() {

    }

    public HashData[] getHassh() {
        return hassh;
    }

    public ScanData[] getScan() {
        return scan;
    }

    public WebData getWeb() {
        return web;
    }

    public boolean isSeen() {
        return seen;
    }

    public boolean isSpoofable() {
        return spoofable;
    }

    public boolean hasVpn() {
        return vpn;
    }


    public String getVpnService() {
        return vpnService;
    }

    public String[] getTags() {
        return tags;
    }

    static class HashData {

        String fingerprint;
        int port;

        public HashData() {

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

        public ScanData() {

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

        public WebData() {

        }

        public String[] getPaths() {
            return paths;
        }

        public String[] getUseragents() {
            return useragents;
        }
    }
}
