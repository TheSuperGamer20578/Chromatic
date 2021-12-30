package com.TheSuperGamer20578.chromatic;

import net.fabricmc.api.ModInitializer;
import org.jglr.jchroma.JChroma;
import org.jglr.jchroma.effects.WaveDirection;
import org.jglr.jchroma.effects.WaveKeyboardEffect;


public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        ModConfig.init();
        JChroma chroma = JChroma.getInstance();
        chroma.createKeyboardEffect(new WaveKeyboardEffect(WaveDirection.LEFT_TO_RIGHT));
        Util.LOGGER.info("Chromatic initialised");
    }

}
