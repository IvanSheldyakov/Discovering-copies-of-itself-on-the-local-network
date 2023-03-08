package ru.nsu.networks.copydiscoverer.view;

import ru.nsu.networks.copydiscoverer.Utility;
import ru.nsu.networks.copydiscoverer.application.model.Action;
import ru.nsu.networks.copydiscoverer.application.model.InetSocketAddressConfig;
import ru.nsu.networks.copydiscoverer.application.model.IpActionMessage;
import ru.nsu.networks.copydiscoverer.application.model.TextAreaContext;
import ru.nsu.networks.copydiscoverer.application.services.DatagramPacketCreator;
import ru.nsu.networks.copydiscoverer.application.services.InetSocketAddressConfigReader;
import ru.nsu.networks.copydiscoverer.application.services.MulticastPublisher;
import ru.nsu.networks.copydiscoverer.application.services.impl.DatagramPacketCreatorImpl;
import ru.nsu.networks.copydiscoverer.application.services.impl.InetSocketAddressConfigReaderImpl;
import ru.nsu.networks.copydiscoverer.application.services.Observer;
import ru.nsu.networks.copydiscoverer.application.services.impl.MulticastPublisherImpl;
import ru.nsu.networks.copydiscoverer.application.threads.DatagramPacketReceiver;
import ru.nsu.networks.copydiscoverer.application.threads.DatagramPacketSender;
import ru.nsu.networks.copydiscoverer.view.actions.impl.RemoveFromTextAreaJob;
import ru.nsu.networks.copydiscoverer.view.actions.impl.UpdateTextAreaJob;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedHashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class CopyFinderApp extends JFrame implements Observer {

    private DatagramPacketReceiver messageReceiver;

    private DatagramPacketSender messageSender;

    private MulticastPublisher publisher;
    private TextArea textArea;

    private TextAreaContext context;

    private LinkedHashSet<String> copies;

    private InetSocketAddressConfig config;

    public CopyFinderApp() {
        super("Copy finder");

    }

    public void startApp() {
        setBasicParametersOfFrame();
        initTextField();
        addExitOnCloseListener();

        readConfig();

        setVisible(true);

        initMessageSender();
        initMessageReceiver();
        startExecutors();

    }

    private void setBasicParametersOfFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Utility.WINDOW_WIDTH,Utility.WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);


    }

    private void initTextField() {
        textArea = new TextArea();
        copies = new LinkedHashSet<>();
        context = new TextAreaContext(textArea,copies);
        this.add(textArea);
    }

    private void addExitOnCloseListener() {
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                DatagramPacketCreator creator = new DatagramPacketCreatorImpl();

                publisher.send(creator.create(config.getGroupIpAddress(),Integer.parseInt(config.getPort()),
                        DatagramPacketSender.initMessage(Action.DELETE)));
                e.getWindow().dispose();
            }
        });
    }
    private void readConfig() {
        InetSocketAddressConfigReader configReader = new InetSocketAddressConfigReaderImpl();
        config = configReader.read();
    }

    private void initMessageSender() {
        publisher = new MulticastPublisherImpl();
        messageSender = new DatagramPacketSender(config,publisher);
        messageSender.setDaemon(true);
    }

    private void initMessageReceiver() {
        messageReceiver = new DatagramPacketReceiver(config);
        messageReceiver.setDaemon(true);
        messageReceiver.addObserver(this);
    }


    private void startExecutors() {
        ScheduledExecutorService executorForSender = Executors.newSingleThreadScheduledExecutor();
        ExecutorService executorForReceiver = Executors.newSingleThreadExecutor();
        executorForSender.scheduleAtFixedRate(messageSender,2,2, TimeUnit.SECONDS);
        executorForReceiver.execute(messageReceiver);
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof IpActionMessage) {

            IpActionMessage message = (IpActionMessage) obj;
            executeActionFromMessage(message);

        } else {
            throw new RuntimeException();
        }
    }



    private void executeActionFromMessage(IpActionMessage message) {
        String ip = message.getIp();
        switch (message.getAction()) {
            case UPDATE -> {
                SwingUtilities.invokeLater(new UpdateTextAreaJob(context,ip));
            }
            case DELETE -> {
                SwingUtilities.invokeLater(new RemoveFromTextAreaJob(context,ip));
            }
        }
    }

}
