package ru.sgk.chatnotesdesktop.backend;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Chat<M extends Message<?>> {
    String title();
    Chat<M> withTitle(String title);

    String description();
    Chat<M> withDescription(String description);

    LocalDateTime modifiedDatetime();

    Collection<M> messages();

    void sendMessage(String text);


}
