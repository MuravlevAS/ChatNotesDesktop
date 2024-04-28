package ru.sgk.chatnotesdesktop.backend.datastore.sqlite;

import org.cactoos.Text;
import org.cactoos.text.Concatenated;
import org.cactoos.text.TextEnvelope;
import org.cactoos.text.TextOf;
import ru.sgk.chatnotesdesktop.backend.datastore.ChatNotesLocalDataFolder;

import java.io.File;

/**
 * db path
 */
public final class ChatNotesDBPath extends TextEnvelope {

    /**
     * Path with default db name and location
     *
     * @see ChatNotesLocalDataFolder
     */
    public ChatNotesDBPath() {

        this(new ChatNotesLocalDataFolder(), new TextOf("data.db"));
    }

    /**
     * Ctor.
     *
     * @param dbname name of the database.
     */
    public ChatNotesDBPath(Text dbname) {

        this(new ChatNotesLocalDataFolder(), dbname);
    }

    /**
     * Ctor.
     *
     * @param location folder where the database persists.
     * @param dbname   name of the database.
     */
    public ChatNotesDBPath(Text location, Text dbname) {

        super(new Concatenated(location, new TextOf(File.separator), dbname));
    }
}
