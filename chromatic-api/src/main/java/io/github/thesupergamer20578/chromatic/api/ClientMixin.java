package io.github.thesupergamer20578.chromatic.api;

import io.github.thesupergamer20578.chroma.Chroma;
import io.github.thesupergamer20578.chroma.Colour;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.freedesktop.dbus.exceptions.DBusException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(MinecraftClient.class)
public class ClientMixin {
    @Inject(at = @At("HEAD"), method = "joinWorld")
    private void joinWorld(CallbackInfo ci) {
        Chromatic.effectQueue.clear();
        for (Module module : Chromatic.modules) {
            module.reset(Chromatic.getInstance());
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        Screen screen = client.currentScreen;
        Driver driver;
        try {
            driver = Chroma.getDriver();
            assert driver != null;
        } catch (DBusException e) {
            throw new RuntimeException(e);
        }

        for (Module module : Chromatic.modules) {
            module.tick(Chromatic.getInstance(), client, player, screen);
        }

        final AtomicBoolean end = new AtomicBoolean(false);
        Chromatic.effectQueue.removeIf(effect -> {
            if (end.get()) {
                return false;
            }
            EffectResult result = effect.next(driver, client, player, screen);
            if (result == EffectResult.CONTINUE) {
                end.set(true);
            }
            return result == EffectResult.END;
        });

        Colour[][] matrix = new Colour[6][22];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Colour(0x000000);
            }
        }
        end.set(false);
        for (List<Overlay> overlays : Chromatic.overlays.values()) {
            overlays.removeIf(overlay -> {
                if (end.get()) {
                    return false;
                }
                EffectResult result = overlay.apply(matrix, client, player, screen);
                if (result == EffectResult.CONTINUE) {
                    end.set(true);
                }
                return result == EffectResult.END;
            });
            if (end.get()) {
                break;
            }
        }
    }
}
