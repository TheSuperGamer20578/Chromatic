package com.TheSuperGamer20578.chromatic;

import io.github.thesupergamer20578.chroma.drivers.Driver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;

public interface IEffect {
    boolean noScreenOnly();

    /**
     * Called every tick to update the effect
     * @param driver The Chroma driver
     * @param client The Minecraft client
     * @param player The current player object
     * @param currentScreen The currently active screen
     * @return false if the effect is finished and should be removed from the queue, true otherwise.
     */
    boolean next(Driver driver, MinecraftClient client, @Nullable ClientPlayerEntity player, Screens currentScreen);
}
