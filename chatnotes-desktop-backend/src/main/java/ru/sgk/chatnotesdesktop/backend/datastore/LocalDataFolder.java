package ru.sgk.chatnotesdesktop.backend.datastore;

import org.cactoos.text.TextEnvelope;

import java.io.File;

/**
 * Folder with local data of application
 */
public final class LocalDataFolder extends TextEnvelope {
    /**
     * ctor
     *
     * @param appFolderName Application folder name.
     */
    public LocalDataFolder(String appFolderName) {
        super(() -> {

            String os = System.getProperty("os.name").toLowerCase();
            String appDataFolder;

            if (os.startsWith("windows")) {
                // Windows platform
                appDataFolder = System.getenv("LOCALAPPDATA");
            } else if (os.startsWith("mac")) {
                // Mac platform
                appDataFolder = System.getProperty("user.home") + "/Library/Application Support";
            } else {
                // Assume Unix/Linux platform
                appDataFolder = System.getProperty("user.home") + "/.local/share";
            }
            appDataFolder += File.separator + appFolderName;
            return appDataFolder;
        });
    }
}
