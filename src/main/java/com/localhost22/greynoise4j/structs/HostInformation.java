package com.localhost22.greynoise4j.structs;

import java.io.Serializable;

public class HostInformation implements Serializable {

    private String ip;
    private boolean noise;
    private boolean riot;
    private String classification;
    private String name;
    private String link;
    private String lastSeen;
    private String message;

    public HostInformation() {

    }

    public String getClassification() {
        return classification;
    }

    public String getIp() {
        return ip;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getLink() {
        return link;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public boolean isNoise() {
        return noise;
    }

    public boolean isRiot() {
        return riot;
    }
}
