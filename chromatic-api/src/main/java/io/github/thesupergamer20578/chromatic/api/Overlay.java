package io.github.thesupergamer20578.chromatic.api;

import io.github.thesupergamer20578.chroma.Colour;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Overlay {
    @NotNull EffectResult apply(
            @NotNull Colour @NotNull [] @NotNull [] matrix,
            @NotNull MinecraftClient client,
            @Nullable ClientPlayerEntity player,
            @Nullable Screen currentScreen
    );
}
