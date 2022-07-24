package com.TheSuperGamer20578.chromatic.mixin;

import com.TheSuperGamer20578.chromatic.*;
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
            case ADVANCEMENTS:
            case BEACON:
            case BLAST_FURNACE:
            case BOOK:
            case BREWING:
            case CARTOGRAPHY:
            case CRAFTING:
            case ENCHANTING:
            case FORGING:
            case FURNACE:
            case GRINDSTONE:
            case HOPPER:
            case HORSE:
            case INVENTORY:
            case LECTERN:
            case LOOM:
            case MERCHANT:
            case SHULKER:
            case SMITHING:
            case SMOKER:
            case STONE_CUTTER:
            case SERVERS:
            case MULTIPLAYER_WARNING:
            case ACCESSIBILITY:
            case CHAT_OPTIONS:
            case GAME_OPTIONS:
            case LANGUAGES:
            case MOUSE_OPTIONS:
            case ONLINE_OPTIONS:
            case OPTIONS:
            case SKIN_OPTIONS:
            case SOUND:
            case VIDEO_SETTINGS:
            case RESOURCE_PACKS:
            case GAME_RULES:
            case OPTIMIZE_WORLD:
            case SELECT_WORLD:
            case BACKUP_PROMPT:
            case CONFIRM_LINK:
            case CONNECT:
            case CREDITS:
            case CUSTOMIZE_BUFFET:
            case CUSTOMIZE_FLAT:
            case DATAPACK_FAIL:
            case DEMO:
            case DIALOG:
            case DISCONNECTED:
            case DOWNLOAD_TERRAIN:
            case FATAL_ERROR:
            case MENU:
            case GAMEMODE_SELECT:
            case LOADING:
            case NOTICE:
            case LAN:
            case OUT_OF_MEMORY:
            case PRESETS:
            case PROGRESS:
            case SAVE:
            case STATS:
            case TITLE:
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
                if (player != null)
                    Layouts.applyStatus(layout, player);
                chroma.createKeyboardEffect(new CustomKeyboardEffect(layout));
                break;
        }
    }
}
