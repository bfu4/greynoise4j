package com.localhost22.greynoise4j.structs;

import com.localhost22.greynoise4j.api.GreynoiseResponse;

/**
 * Host metadata is a data class used in {@link HostContextInformation}.
 */
public final class HostMetadata implements GreynoiseResponse {

    /**
     * The host's ASN.
     */
    private String asn;

    /**
     * The host's category.
     */
    private String category;

    /**
     * The host's city.
     */
    private String city;

    /**
     * The host's country.
     */
    private String country;

    /**
     * The host's country code.
     */
    private String countryCode;

    /**
     * The host's organization.
     */
    private String organization;

    /**
     * The host's operating system.
     */
    private String os;

    /**
     * The host's rDNS.
     */
    private String rdns;

    /**
     * The host's region.
     */
    private String region;

    /**
     * Whether the host is using tor.
     */
    private boolean tor;

    /**
     * Constructor to instantiate a blank information structure.
     */
    public HostMetadata() {
    }

    /**
     * Get the host's ASN.
     * @return asn
     */
    public String getAsn() {
        return asn;
    }

    /**
     * Get the host's category.
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the host's city.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the host's country.
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get the host's country code.
     * @return country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Get the host's organization.
     * @return organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Get the host's operating system.
     * @return operating system
     */
    public String getOs() {
        return os;
    }

    /**
     * Get the host's rDNS.
     * @return rdns
     */
    public String getRdns() {
        return rdns;
    }

    /**
     * Get the host's region.
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Get whether the host is using tor.
     * @return if the host is using tor
     */
    public boolean isTor() {
        return tor;
    }

}
