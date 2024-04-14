package ru.sgk.chatnoteddesktop.datastore;

import org.cactoos.scalar.ScalarEnvelope;
import ru.sgk.chatnoteddesktop.datastore.LocalDataFolder;

/**
 * Chat notes data folder
 */
public final class ChatNotesLocalDataFolder extends ScalarEnvelope<String> {
    /**
     * Ctor.
     */
    public ChatNotesLocalDataFolder() {
        super(new LocalDataFolder("ChatNotes"));
    }
}
