package ru.sgk.chatnotesdesktop.backend;

import java.time.LocalDateTime;

public interface Message<C extends Chat<?>> {
    C chat();
    String text();
    Message<C> withText(String text);
    LocalDateTime modifiedDatetime();

}
