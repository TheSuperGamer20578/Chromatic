package com.TheSuperGamer20578.chromatic.effects;

import com.TheSuperGamer20578.chromatic.*;
import io.github.thesupergamer20578.chroma.Colour;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class Regen implements IEffect {
    private long time = 0;

    @Override
    public boolean noScreenOnly() {
        return true;
    }

    @Override
    public boolean next(Driver driver, MinecraftClient client, @Nullable ClientPlayerEntity player, Screens currentScreen) {
        time++;
        if (time > ModConfig.INSTANCE.health.regenFlashDuration)
            return false;

        Colour[][] layout = Layouts.main();
        if (player == null) {
            driver.customKeyboardEffect(layout);
            return true;
        }
        Layouts.applyTint(layout, player);
        Layouts.applyStatus(layout, player, true);

        double health = player.getHealth() / player.getMaxHealth();
        Colour colour = new Colour(ModConfig.INSTANCE.health.regenColour);
        if (health > .75)
            layout[0][6] = colour.multiply((health - .75) * 4);
        else if (health > .5)
            layout[0][5] = colour.multiply((health - .5) * 4);
        else if (health > .25)
            layout[0][4] = colour.multiply((health - .25) * 4);
        else
            layout[0][3] = colour.multiply(health * 4);

        driver.customKeyboardEffect(layout);
        return true;
    }
}
