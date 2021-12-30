package com.TheSuperGamer20578.chromatic;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.jglr.jchroma.JChroma;

public class PreInit implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        JChroma chroma = JChroma.getInstance();
        chroma.init();
        Util.LOGGER.info("JChroma initialised");
    }
}
