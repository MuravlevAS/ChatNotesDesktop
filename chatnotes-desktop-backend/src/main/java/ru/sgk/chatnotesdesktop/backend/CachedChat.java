package ru.sgk.chatnotesdesktop.backend;

import ru.sgk.chatnotesdesktop.backend.datastore.StoredChat;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CachedChat<M extends Message<?>> implements StoredChat<M> {
    private final StoredChat<M> origin;
    private final long time;
    private final TimeUnit timeUnit;
    private List<M> messages = null;
    private Date expiration = new Date(0);

    public CachedChat(StoredChat<M> origin) {
        this.origin = origin;
        time = 1;
        timeUnit = TimeUnit.MINUTES;
    }

    public CachedChat(StoredChat<M> origin, long time, TimeUnit timeUnit) {
        this.origin = origin;
        this.time = time;
        this.timeUnit = timeUnit;
    }

    @Override
    public UUID id() {
        return origin.id();
    }

    @Override
    public String title() {
        return origin.title();
    }

    @Override
    public CachedChat<M> withTitle(String title) {
        return new CachedChat<>(origin.withTitle(title));
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public CachedChat<M> withDescription(String description) {
        return new CachedChat<>(origin.withDescription(description));
    }

    @Override
    public LocalDateTime modifiedDatetime() {
        return origin.modifiedDatetime();
    }

    @Override
    public Collection<M> messages() {
        long now = new Date().getTime();
        if (now > expiration.getTime()) {
            messages = new ArrayList<>(origin.messages());
            expiration = new Date(now + timeUnit.toMillis(time));
        }
        return messages;
    }

    @Override
    public void sendMessage(String text) {
        expiration = new Date(0);
        origin.sendMessage(text);
    }
}
