package io.github.thesupergamer20578.chromatic.effects;

import io.github.thesupergamer20578.chroma.Colour;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import io.github.thesupergamer20578.chromatic.Effect;
import io.github.thesupergamer20578.chromatic.Layouts;
import io.github.thesupergamer20578.chromatic.ModConfig;
import io.github.thesupergamer20578.chromatic.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class Regen implements Effect {
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

        Colour[][] layout = Layouts.mainLayout();
        if (player == null) {
            driver.customKeyboardEffect(layout);
            return true;
        }
        Layouts.applyTint(layout, player);
        Layouts.applyStatus(layout, player, true);

        double health = player.getHealth() / player.getMaxHealth();
        Colour colour = new Colour(ModConfig.INSTANCE.health.regenColour);
        if (health > 0.75)
            layout[0][6] = colour.multiply((health - 0.75) * 4);
        else if (health > 0.5)
            layout[0][5] = colour.multiply((health - 0.5) * 4);
        else if (health > 0.25)
            layout[0][4] = colour.multiply((health - 0.25) * 4);
        else
            layout[0][3] = colour.multiply(health * 4);

        driver.customKeyboardEffect(layout);
        return true;
    }
}
