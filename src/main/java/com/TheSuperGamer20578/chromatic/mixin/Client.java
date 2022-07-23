package com.TheSuperGamer20578.chromatic.mixin;

import com.TheSuperGamer20578.chromatic.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import org.jglr.jchroma.JChroma;
import org.jglr.jchroma.effects.CustomKeyboardEffect;
import org.jglr.jchroma.effects.StaticKeyboardEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class Client {
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        Screens screen = Screens.of(client.currentScreen == null ? null : client.currentScreen.getClass());
        JChroma chroma = JChroma.getInstance();
        ModConfig config = ModConfig.INSTANCE;
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
                ClientPlayerEntity player = client.player;
                if (player != null) {
                    if (!player.getAbilities().invulnerable) {
                        double health = player.getHealth() / player.getMaxHealth();
                        ColourRef healthColour = ColourRef.fromInt(config.healthColour);
                        layout[0][3] = healthColour.multiply(health == 0 ? 0 : health > .25 ? 1 : health * 4);
                        layout[0][4] = healthColour.multiply(health < .25 ? 0 : health > .5 ? 1 : (health - .25) * 4);
                        layout[0][5] = healthColour.multiply(health < .5 ? 0 : health > .75 ? 1 : (health - .5) * 4);
                        layout[0][6] = healthColour.multiply(health < .75 ? 0 : (health - .75) * 4);

                        if (player.getAir() < player.getMaxAir()) {
                            float oxygen = (float) player.getAir() / player.getMaxAir();
                            ColourRef oxygenColour = ColourRef.fromInt(config.oxygenColour);
                            layout[0][7] = oxygenColour.multiply(oxygen == 0 ? 0 : oxygen > .25 ? 1 : oxygen * 4);
                            layout[0][8] = oxygenColour.multiply(oxygen < .25 ? 0 : oxygen > .5 ? 1 : (oxygen - .25) * 4);
                            layout[0][9] = oxygenColour.multiply(oxygen < .5 ? 0 : oxygen > .75 ? 1 : (oxygen - .5) * 4);
                            layout[0][10] = oxygenColour.multiply(oxygen < .75 ? 0 : (oxygen - .75) * 4);
                        }

                        float hunger = player.getHungerManager().getFoodLevel() / 20f;
                        ColourRef hungerColour = ColourRef.fromInt(config.hungerColour);
                        layout[0][11] = hungerColour.multiply(hunger == 0 ? 0 : hunger > .25 ? 1 : hunger * 4);
                        layout[0][12] = hungerColour.multiply(hunger < .25 ? 0 : hunger > .5 ? 1 : (hunger - .25) * 4);
                        layout[0][13] = hungerColour.multiply(hunger < .5 ? 0 : hunger > .75 ? 1 : (hunger - .5) * 4);
                        layout[0][14] = hungerColour.multiply(hunger < .75 ? 0 : (hunger - .75) * 4);
                    }

                    ColourRef selectedColour = ColourRef.fromInt(config.items.selectedColour);
                    ColourRef stackableColour = ColourRef.fromInt(config.items.stackableColour);
                    ColourRef toolColour = ColourRef.fromInt(config.items.toolColour);
                    ColourRef otherColour = ColourRef.fromInt(config.items.otherColour);
                    ColourRef emptyColour = ColourRef.fromInt(config.items.emptyColour);
                    float minBrightness = config.items.minBrightness / (float) 0xff;
                    for (int i = 0; i < 9; i++) {
                        ItemStack item = player.getInventory().getStack(i);
                        boolean current = player.getInventory().selectedSlot == i;
                        if (item.getCount() == 0) {
                            layout[1][i + 2] = emptyColour;
                        }
                        else {
                            if (item.isStackable()) {
                                layout[1][i + 2] = (current ? selectedColour : stackableColour).multiply(Math.max((float) item.getCount() / item.getMaxCount(), minBrightness));
                            } else if (item.isDamageable()) {
                                layout[1][i + 2] = (current ? selectedColour : toolColour).multiply(Math.max((item.getMaxDamage() - (float) item.getDamage()) / item.getMaxDamage(), minBrightness));
                            } else {
                                layout[1][i + 2] = current ? selectedColour : otherColour;
                            }
                        }
                    }
                }

                chroma.createKeyboardEffect(new CustomKeyboardEffect(layout));
                break;
        }
    }
}
