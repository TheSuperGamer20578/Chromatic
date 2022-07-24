package com.TheSuperGamer20578.chromatic.effects;

import com.TheSuperGamer20578.chromatic.ColourRef;
import com.TheSuperGamer20578.chromatic.IEffect;
import com.TheSuperGamer20578.chromatic.ModConfig;
import com.TheSuperGamer20578.chromatic.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.jglr.jchroma.effects.KeyboardEffect;
import org.jglr.jchroma.effects.StaticKeyboardEffect;

public class Damage implements IEffect {
    private long time = 0;

    @Override
    public boolean noScreenOnly() {
        return false;
    }

    @Override
    public @Nullable KeyboardEffect next(MinecraftClient client, @Nullable ClientPlayerEntity player, Screens currentScreen) {
        time++;
        if (time > ModConfig.INSTANCE.health.damageFlashDuration)
            return null;
        return new StaticKeyboardEffect(ColourRef.fromInt(ModConfig.INSTANCE.health.damageColour));
    }
}
