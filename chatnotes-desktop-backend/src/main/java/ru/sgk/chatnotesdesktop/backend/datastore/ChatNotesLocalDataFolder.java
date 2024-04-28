package ru.sgk.chatnotesdesktop.backend.datastore;

import org.cactoos.text.TextEnvelope;

/**
 * Chat notes data folder
 */
public final class ChatNotesLocalDataFolder extends TextEnvelope {
    /**
     * Ctor.
     */
    public ChatNotesLocalDataFolder() {
        super(new LocalDataFolder("ChatNotes"));
    }
}
