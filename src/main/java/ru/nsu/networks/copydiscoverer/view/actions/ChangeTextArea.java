package ru.nsu.networks.copydiscoverer.view.actions;

import ru.nsu.networks.copydiscoverer.application.model.TextAreaContext;
import ru.nsu.networks.copydiscoverer.application.services.TextService;
import ru.nsu.networks.copydiscoverer.application.services.impl.TextServiceImpl;

import java.awt.*;

public abstract class ChangeTextArea implements Runnable{
    protected final TextAreaContext context;
    protected final TextService textService;
    protected final String data;

    public ChangeTextArea(TextAreaContext context, String data) {
        this.context = context;
        this.data = data;
        textService = new TextServiceImpl(context.getText());
    }

    abstract protected void execute();
}
