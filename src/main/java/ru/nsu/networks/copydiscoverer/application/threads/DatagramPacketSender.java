package ru.nsu.networks.copydiscoverer.application.threads;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.networks.copydiscoverer.application.model.Action;
import ru.nsu.networks.copydiscoverer.application.model.InetSocketAddressConfig;
import ru.nsu.networks.copydiscoverer.application.model.IpActionMessage;
import ru.nsu.networks.copydiscoverer.application.services.DatagramPacketCreator;
import ru.nsu.networks.copydiscoverer.application.services.MulticastPublisher;
import ru.nsu.networks.copydiscoverer.application.services.impl.DatagramPacketCreatorImpl;
import ru.nsu.networks.copydiscoverer.application.services.impl.MulticastPublisherImpl;


import java.net.*;

public class DatagramPacketSender extends Thread {

    private DatagramPacketCreator packetCreator;
    private MulticastPublisher publisher;
    private final InetSocketAddressConfig conf;

    public DatagramPacketSender(InetSocketAddressConfig conf) {
        this.conf = conf;

    }

    @Override
    public void run() {

        packetCreator = new DatagramPacketCreatorImpl();
        publisher = new MulticastPublisherImpl();
        IpActionMessage message = initMessage();
        DatagramPacket packet = packetCreator.create(conf.getGroupIpAddress(),
                Integer.parseInt(conf.getPort()), message);

        publisher.send(packet);

    }

    private IpActionMessage initMessage() {
        IpActionMessage message = new IpActionMessage();
        message.setAction(Action.UPDATE);
        return message;
    }

}
