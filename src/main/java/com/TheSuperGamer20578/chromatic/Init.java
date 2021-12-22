package com.TheSuperGamer20578.chromatic;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jglr.jchroma.JChroma;
import org.jglr.jchroma.effects.WaveDirection;
import org.jglr.jchroma.effects.WaveKeyboardEffect;


public class Init implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Chromatic");

    @Override
    public void onInitialize() {
        ModConfig.init();
        JChroma chroma = JChroma.getInstance();
        chroma.createKeyboardEffect(new WaveKeyboardEffect(WaveDirection.LEFT_TO_RIGHT));
        LOGGER.info("Chromatic initialised");
    }

}
