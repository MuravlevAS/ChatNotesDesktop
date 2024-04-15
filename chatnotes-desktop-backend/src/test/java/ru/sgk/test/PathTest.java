package ru.sgk.test;

import org.cactoos.text.Concatenated;
import org.cactoos.text.TextOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sgk.chatnoteddesktop.datastore.ChatNotesLocalDataFolder;
import ru.sgk.chatnoteddesktop.datastore.sqlite.ChatNotesDBPath;

import java.io.File;

public class PathTest {
    @Test
    public void pathTest() throws Exception {
        Assertions.assertEquals(new Concatenated(new ChatNotesLocalDataFolder(), new TextOf(File.separator), new TextOf("data.db")).asString(),
                new ChatNotesDBPath().asString());

        Assertions.assertEquals(new Concatenated(
                        new ChatNotesLocalDataFolder(),
                        new TextOf(File.separator),
                        new TextOf("test.db")
                ).asString(),
                new ChatNotesDBPath(new TextOf("test.db")).asString());

        Assertions.assertEquals(new Concatenated(
                        new TextOf("/test"),
                        new TextOf(File.separator),
                        new TextOf("test.db")
                ).asString(),
                new ChatNotesDBPath(new TextOf("/test"), new TextOf("test.db")).asString());
    }
}
