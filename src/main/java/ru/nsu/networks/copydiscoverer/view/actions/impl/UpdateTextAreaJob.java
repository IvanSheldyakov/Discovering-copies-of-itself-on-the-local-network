package ru.nsu.networks.copydiscoverer.view.actions.impl;

import ru.nsu.networks.copydiscoverer.application.model.TextAreaContext;
import ru.nsu.networks.copydiscoverer.view.actions.ChangeTextArea;

import java.awt.*;
import java.util.Arrays;

public class UpdateTextAreaJob extends ChangeTextArea {

    public UpdateTextAreaJob(TextAreaContext context, String data) {
        super(context,data);
    }

    @Override
    protected void execute() {
        textService.add(data);
        context.getArea().setText(textService.filter());
    }

    @Override
    public void run() {
        execute();
    }
}
