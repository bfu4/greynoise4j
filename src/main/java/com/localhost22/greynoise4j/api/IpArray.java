package com.localhost22.greynoise4j.api;

/**
 * IpArray is a JSON serializable data class used in
 * enterprise MULTI requests.
 */
public final class IpArray {

    /**
     * Create a new ip array with the specified ip addresses.
     * @param ips ips to put in the IpArray instance
     * @return a new ip array
     */
    public static IpArray create(final String... ips) {
        return new IpArray(ips);
    }

    /**
     * The ip addresses.
     */
    private final String[] ips;

    /**
     * Create a new serializable IpArray to create the serializable data class.
     * The format that this object should serialize to should be
     * <code>
     * {"ips": []}
     * </code>
     * @param ips ip addresses
     */
    private IpArray(final String... ips) {
        this.ips = ips;
    }

    /**
     * Get the ips.
     * @return ip addresses
     */
    public String[] getIps() {
        return ips;
    }

}
