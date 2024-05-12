package ru.sgk.chatnotesdesktop;

import javafx.scene.Node;

public interface Displayable<T extends Node> {
    T displayableObject();
}
