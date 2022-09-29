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


import javax.swing.*;
import java.net.*;

public class DatagramPacketSender extends Thread {

    private DatagramPacketCreator packetCreator;
    private MulticastPublisher publisher;
    private final InetSocketAddressConfig conf;

    public DatagramPacketSender(InetSocketAddressConfig conf, MulticastPublisher publisher) {
        this.conf = conf;
        this.publisher = publisher;

    }

    @Override
    public void run() {

        packetCreator = new DatagramPacketCreatorImpl();
        IpActionMessage message = initMessage(Action.UPDATE);
        DatagramPacket packet = packetCreator.create(conf.getGroupIpAddress(),
                Integer.parseInt(conf.getPort()), message);

        publisher.send(packet);

    }

    public static IpActionMessage initMessage(Action action) {
        IpActionMessage message = new IpActionMessage();
        message.setAction(action);
        return message;
    }

}
