package io.github.thesupergamer20578.chromatic;

import io.github.thesupergamer20578.chroma.Chroma;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.freedesktop.dbus.exceptions.DBusException;

public class PreInit implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        Driver driver;
        try {
            driver = Chroma.getDriver();
        } catch (DBusException e) {
            throw new RuntimeException("Could not initialise Chroma driver", e);
        }
        if (driver == null) {
            throw new RuntimeException("Could not initialise Chroma driver: no driver found");
        }
        Util.LOGGER.info("Chroma initialised. Using {}", driver.getClass().getName());
    }
}
