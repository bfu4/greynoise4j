package com.localhost22.greynoise4j.structs;

public class HostMetadata {

    private String asn;
    private String category;
    private String city;
    private String country;
    private String countryCode;
    private String organization;
    private String os;
    private String rdns;
    private String region;
    private boolean tor;

    public HostMetadata() {

    }

    public String getAsn() {
        return asn;
    }

    public String getCategory() {
        return category;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getOrganization() {
        return organization;
    }

    public String getOs() {
        return os;
    }

    public String getRdns() {
        return rdns;
    }

    public String getRegion() {
        return region;
    }

    public boolean isTor() {
        return tor;
    }

}
