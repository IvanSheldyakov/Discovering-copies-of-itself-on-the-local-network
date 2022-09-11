package ru.nsu.networks.copydiscoverer.application.model;

public class IpActionMessage {
    private String ip;
    private Action action;

    public IpActionMessage(String ip, Action action) {
        this.ip = ip;
        this.action = action;
    }

    public IpActionMessage() {

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
