package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

public final class HostRiotInformation implements GreynoiseResponse {

    private String ip;

    private boolean riot;

    private String category;

    private String name;

    private String description;

    private String explanation;

    private String lastUpdated;

    private String logoUrl;

    private String reference;

    private String trustLevel;

    /**
     * Constructor to instantiate a blank information structure.
     */
    public HostRiotInformation() {
    }

    public String getIp() {
        return ip;
    }

    public boolean isRiot() {
        return riot;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getReference() {
        return reference;
    }

    public String getTrustLevel() {
        return trustLevel;
    }

}
