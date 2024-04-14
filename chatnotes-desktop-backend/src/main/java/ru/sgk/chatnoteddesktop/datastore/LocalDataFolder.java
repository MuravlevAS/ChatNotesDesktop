package ru.sgk.chatnoteddesktop.datastore;

import org.cactoos.Scalar;

import java.io.File;

/**
 * Folder with local data of application
 */
public final class LocalDataFolder implements Scalar<String> {
    private final String appFolderName;

    /**
     * ctor
     * @param appFolderName Application folder name.
     */
    public LocalDataFolder(String appFolderName) {
        this.appFolderName = appFolderName;
    }

    @Override
    public String value() {
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
        appDataFolder += File.separator + this.appFolderName;
        return appDataFolder;
    }
}
