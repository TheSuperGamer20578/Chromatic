package com.TheSuperGamer20578.chromatic.effects;

import com.TheSuperGamer20578.chromatic.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.jglr.jchroma.effects.CustomKeyboardEffect;
import org.jglr.jchroma.effects.KeyboardEffect;

public class Regen implements IEffect {
    private long time = 0;

    @Override
    public boolean noScreenOnly() {
        return true;
    }

    @Override
    public @Nullable KeyboardEffect next(MinecraftClient client, @Nullable ClientPlayerEntity player, Screens currentScreen) {
        time++;
        if (time > ModConfig.INSTANCE.health.regenFlashDuration)
            return null;

        ColourRef[][] layout = Layouts.main();
        if (player == null)
            return new CustomKeyboardEffect(layout);
        Layouts.applyStatus(layout, player);

        double health = player.getHealth() / player.getMaxHealth();
        ColourRef colour = ColourRef.fromInt(ModConfig.INSTANCE.health.regenColour);
        if (health > .75)
            layout[0][6] = colour.multiply((health - .75) * 4);
        else if (health > .5)
            layout[0][5] = colour.multiply((health - .5) * 4);
        else if (health > .25)
            layout[0][4] = colour.multiply((health - .25) * 4);
        else
            layout[0][3] = colour.multiply(health * 4);

        return new CustomKeyboardEffect(layout);
    }
}
