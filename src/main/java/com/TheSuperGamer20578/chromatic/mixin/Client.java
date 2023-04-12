package com.TheSuperGamer20578.chromatic.mixin;

import com.TheSuperGamer20578.chromatic.*;
import com.TheSuperGamer20578.chromatic.effects.Damage;
import com.TheSuperGamer20578.chromatic.effects.Regen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jglr.jchroma.JChroma;
import org.jglr.jchroma.effects.CustomKeyboardEffect;
import org.jglr.jchroma.effects.KeyboardEffect;
import org.jglr.jchroma.effects.StaticKeyboardEffect;
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
        JChroma chroma = JChroma.getInstance();
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
            KeyboardEffect next = effect.next(client, player, screen);
            if (next != null) {
                chroma.createKeyboardEffect(next);
                return;
            }
            effect = Util.effectQueue.poll();
        }

        switch (screen) {
            case OTHER:
                chroma.createKeyboardEffect(new StaticKeyboardEffect(ColourRef.fromInt(config.backgroundColour)));
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
                chroma.createKeyboardEffect(new StaticKeyboardEffect(ColourRef.fromInt(config.chatColour)));
                break;
            case DEATH:
                chroma.createKeyboardEffect(new StaticKeyboardEffect(new ColourRef(255, 0, 0)));
                break;
            case NONE:
                ColourRef[][] layout = Layouts.main();
                Layouts.applyTint(layout, player);
                if (player != null)
                    Layouts.applyStatus(layout, player, true);
                chroma.createKeyboardEffect(new CustomKeyboardEffect(layout));
                break;
        }
    }
}
