package com.TheSuperGamer20578.chromatic;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import org.jglr.jchroma.JChroma;
import org.jglr.jchroma.effects.WaveDirection;
import org.jglr.jchroma.effects.WaveKeyboardEffect;


public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        ModConfig.init();
        JChroma chroma = JChroma.getInstance();
        chroma.createKeyboardEffect(new WaveKeyboardEffect(WaveDirection.LEFT_TO_RIGHT));
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("chromaticconfig")
            .executes(context -> {
                MinecraftClient.getInstance().send(() -> MinecraftClient.getInstance().setScreen(AutoConfig.getConfigScreen(ModConfig.class, null).get()));
                return 1;
            })
        );
        Util.LOGGER.info("Chromatic initialised");
    }

}
