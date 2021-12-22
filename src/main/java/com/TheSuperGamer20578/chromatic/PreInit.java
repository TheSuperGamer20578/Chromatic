package com.TheSuperGamer20578.chromatic;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jglr.jchroma.JChroma;

public class PreInit implements PreLaunchEntrypoint {
    public static final Logger LOGGER = LogManager.getLogger("Chromatic");

    @Override
    public void onPreLaunch() {
        JChroma chroma = JChroma.getInstance();
        chroma.init();
        LOGGER.info("JChroma initialised");
    }
}
