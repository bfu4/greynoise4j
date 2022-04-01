package com.localhost22.greynoise4j.structs;

import java.util.List;

public class HostContextInformation {

    private String actor;
    private boolean bot;
    private String classification;
    private String[] cve;
    private String firstSeen;
    private String ip;
    private String lastSeen;
    private HostMetadata metadata;
    private RawHost rawData;

    public HostContextInformation() {

    }

    public String getActor() {
        return actor;
    }

    public boolean isBot() {
        return bot;
    }

    public String getClassification() {
        return classification;
    }

    public List<String> getCves() {
        return List.of(cve);
    }

    public String getFirstSeen() {
        return firstSeen;
    }

    public String getIp() {
        return ip;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public HostMetadata getMetadata() {
        return metadata;
    }

    public RawHost getRawData() {
        return rawData;
    }

}
