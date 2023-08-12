package com.TheSuperGamer20578.chromatic.effects;

import com.TheSuperGamer20578.chromatic.IEffect;
import com.TheSuperGamer20578.chromatic.ModConfig;
import com.TheSuperGamer20578.chromatic.Screens;
import io.github.thesupergamer20578.chroma.Colour;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class Damage implements IEffect {
    private long time = 0;

    @Override
    public boolean noScreenOnly() {
        return false;
    }

    @Override
    public boolean next(Driver driver, MinecraftClient client, @Nullable ClientPlayerEntity player, Screens currentScreen) {
        time++;
        if (time > ModConfig.INSTANCE.health.damageFlashDuration)
            return false;
        driver.staticKeyboardEffect(new Colour(ModConfig.INSTANCE.health.damageColour));
        return true;
    }
}
