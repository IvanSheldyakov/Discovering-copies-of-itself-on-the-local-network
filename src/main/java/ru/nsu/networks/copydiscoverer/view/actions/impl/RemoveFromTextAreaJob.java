package ru.nsu.networks.copydiscoverer.view.actions.impl;

import ru.nsu.networks.copydiscoverer.application.model.TextAreaContext;
import ru.nsu.networks.copydiscoverer.view.actions.ChangeTextArea;

import java.awt.*;
import java.util.Arrays;

public class RemoveFromTextAreaJob extends ChangeTextArea {

    public RemoveFromTextAreaJob(TextAreaContext context, String data) {
        super(context, data);
    }

    @Override
    protected void execute() {
        textService.remove(data);
        context.getArea().setText(textService.getText());
    }

    @Override
    public void run() {
        execute();
    }
}
