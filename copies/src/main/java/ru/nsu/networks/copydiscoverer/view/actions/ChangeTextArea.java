package ru.nsu.networks.copydiscoverer.view.actions;

import ru.nsu.networks.copydiscoverer.application.services.TextService;
import ru.nsu.networks.copydiscoverer.application.services.impl.TextServiceImpl;

import java.awt.*;

abstract public class ChangeTextArea implements Runnable{
    protected final TextArea area;
    protected final TextService textService = new TextServiceImpl();
    protected final String data;

    public ChangeTextArea(TextArea area, String data) {
        this.area = area;
        this.data = data;
    }

    abstract protected void execute();
}
