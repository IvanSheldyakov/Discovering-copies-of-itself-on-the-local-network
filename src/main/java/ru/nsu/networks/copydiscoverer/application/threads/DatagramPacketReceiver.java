package ru.nsu.networks.copydiscoverer.application.threads;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.networks.copydiscoverer.Utility;
import ru.nsu.networks.copydiscoverer.application.model.InetSocketAddressConfig;
import ru.nsu.networks.copydiscoverer.application.model.IpActionMessage;
import ru.nsu.networks.copydiscoverer.application.services.InetSocketAddressConfigReader;
import ru.nsu.networks.copydiscoverer.application.services.Observable;
import ru.nsu.networks.copydiscoverer.application.services.Observer;
import ru.nsu.networks.copydiscoverer.application.services.impl.InetSocketAddressConfigReaderImpl;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

public class DatagramPacketReceiver extends Thread implements Observable {

    private final List<Observer> observers = new ArrayList<>();
    private MulticastSocket socket;

    private InetSocketAddress groupAddress;

    private InetSocketAddressConfig config;


    public DatagramPacketReceiver(InetSocketAddressConfig config) {
        this.config = config;
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyAllObservers(Object obj) {
        for (Observer observer : observers) {
            observer.update(obj);
        }
    }

    @Override
    public void run() {
        initMulticastSocket();
        while (true) {
            DatagramPacket packet = receivePacketFromSocket();
            IpActionMessage message = readMessageFromPacket(packet);
            notifyAllObservers(message);
        }

    }

    private void initMulticastSocket() {
        try {
            InetAddress mcastAddress = InetAddress.getByName(config.getGroupIpAddress());
            groupAddress = new InetSocketAddress(mcastAddress,Integer.parseInt(config.getPort()));
            socket = new MulticastSocket(Integer.parseInt(config.getPort()));
            socket.joinGroup(groupAddress,socket.getNetworkInterface());
        } catch (IOException e) {
            socket.close();
            throw new UncheckedIOException(e);
        }
    }
    private DatagramPacket receivePacketFromSocket() {
        try {
            byte[] buf = new byte[Utility.BUFFER_SIZE_FOR_DATAGRAM_PACKET];
            DatagramPacket recv = new DatagramPacket(buf, Utility.BUFFER_SIZE_FOR_DATAGRAM_PACKET);
            socket.receive(recv);
            return recv;
        } catch (IOException e) {
            socket.close();
            throw new UncheckedIOException(e);
        }
    }

    private IpActionMessage readMessageFromPacket(DatagramPacket packet) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            IpActionMessage message = objectMapper.readValue(packet.getData(), IpActionMessage.class);
            message.setIp(packet.getAddress().toString());
            return message;
        }catch (IOException e) {
            socket.close();
            throw new UncheckedIOException(e);
        }
    }

}
