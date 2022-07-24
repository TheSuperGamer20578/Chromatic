package com.TheSuperGamer20578.chromatic;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.jglr.jchroma.effects.KeyboardEffect;

public interface IEffect {
    boolean noScreenOnly();

    /**
     * @param client The Minecraft client
     * @param player The current player object
     * @param currentScreen The currently active screen
     * @return The next frame in the animation or null if the end has been reached
     */
    @Nullable KeyboardEffect next(MinecraftClient client, @Nullable ClientPlayerEntity player, Screens currentScreen);
}
