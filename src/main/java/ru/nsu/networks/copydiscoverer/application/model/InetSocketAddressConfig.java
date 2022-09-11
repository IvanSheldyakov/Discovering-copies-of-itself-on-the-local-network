package ru.nsu.networks.copydiscoverer.application.model;

public class InetSocketAddressConfig {

    private final String groupIpAddress;
    private final String port;

    public InetSocketAddressConfig(String groupIpAddress, String port) {
        this.groupIpAddress = groupIpAddress;
        this.port = port;
    }

    public String getGroupIpAddress() {
        return groupIpAddress;
    }

    public String getPort() {
        return port;
    }
}

