package com.TheSuperGamer20578.chromatic.mixin;

import com.TheSuperGamer20578.chromatic.*;
import com.TheSuperGamer20578.chromatic.effects.Damage;
import com.TheSuperGamer20578.chromatic.effects.Regen;
import io.github.thesupergamer20578.chroma.Chroma;
import io.github.thesupergamer20578.chroma.Colour;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.freedesktop.dbus.exceptions.DBusException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class Client {
    private float lastHealth = -1;

    @Inject(at = @At("HEAD"), method = "joinWorld")
    private void joinWorld(CallbackInfo ci) {
        Util.effectQueue.clear();
        lastHealth = -1;
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;
        Screens screen = Screens.of(client.currentScreen == null ? null : client.currentScreen.getClass());
        Driver driver;
        try {
            driver = Chroma.getDriver();
            assert driver != null;
        } catch (DBusException e) {
            throw new RuntimeException(e);
        }
        ModConfig config = ModConfig.INSTANCE;

        // Regen/damage check
        if (player != null) {
            float health = player.getHealth();
            if (Util.effectQueue.isEmpty() && lastHealth != -1) {
                if (health > lastHealth)
                    Util.effectQueue.add(new Regen());
                else if (health < lastHealth && lastHealth-health > config.health.damageThreshold)
                    Util.effectQueue.add(new Damage());
            }
            lastHealth = health;
        }

        IEffect effect = Util.effectQueue.peek();
        while (effect != null && (!effect.noScreenOnly() || screen == Screens.NONE)) {
            if (effect.next(driver, client, player, screen)) {
                return;
            }
            effect = Util.effectQueue.poll();
        }

        switch (screen) {
            case OTHER:
                driver.staticKeyboardEffect(new Colour(config.backgroundColour));
                break;
            case ANVIL:
            case BOOK_EDIT:
            case COMMAND_BLOCK:
            case CREATIVE_INVENTORY:
            case JIGSAW:
            case MINECART_COMMAND_BLOCK:
            case SIGN:
            case STRUCTURE_BLOCK:
            case SOCIAL_INTERACTIONS:
            case CONTROLS:
            case KEYBINDS:
            case CREATE_WORLD:
            case EDIT_WORLD:
            case ADD_SERVER:
            case CHAT:
            case DIRECT_CONNECT:
            case SLEEPING:
                driver.staticKeyboardEffect(new Colour(config.chatColour));
                break;
            case DEATH:
                // TODO Should this be in the config?
                driver.staticKeyboardEffect(new Colour(0xff0000));
                break;
            case NONE:
                Colour[][] layout = Layouts.main();
                Layouts.applyTint(layout, player);
                if (player != null)
                    Layouts.applyStatus(layout, player, true);
                driver.customKeyboardEffect(layout);
                break;
        }
    }
}
