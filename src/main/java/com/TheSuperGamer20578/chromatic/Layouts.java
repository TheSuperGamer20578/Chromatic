package com.TheSuperGamer20578.chromatic;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Layouts {
    public static ColourRef[][] main() {
        ModConfig config = ModConfig.INSTANCE;
        ColourRef bgd = ColourRef.fromInt(config.backgroundColour);
        ColourRef cht = ColourRef.fromInt(config.chatColour);
        ColourRef mov = ColourRef.fromInt(config.movementColour);
        ColourRef esc = ColourRef.fromInt(config.ESCColour);
        ColourRef tab = ColourRef.fromInt(config.tabColour);
        ColourRef mod = ColourRef.fromInt(config.modifierColour);
        ColourRef inv = ColourRef.fromInt(config.inventoryColour);
        return new ColourRef[][] {
                {bgd, esc, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, tab, inv, mov, inv, bgd, cht, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, bgd, mov, mov, mov, inv, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, mod, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, cht, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, mod, bgd, bgd, bgd, bgd, bgd, mov, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd}
        };
    }

    public static void applyStatus(ColourRef[][] layout, @NotNull ClientPlayerEntity player) {
        ModConfig config = ModConfig.INSTANCE;
        if (!player.getAbilities().invulnerable) {
            double health = player.getHealth() / player.getMaxHealth();
            boolean hardcore = player.world.getLevelProperties().isHardcore();
            ColourRef healthColour;
            if (player.hasStatusEffect(StatusEffects.POISON)) {
                healthColour = hardcore ? ColourRef.fromInt(config.health.hardcorePoisonedColour) : ColourRef.fromInt(config.health.poisonedColour);
            } else if (player.hasStatusEffect(StatusEffects.WITHER)) {
                healthColour = hardcore ? ColourRef.fromInt(config.health.hardcoreWitheredColour) : ColourRef.fromInt(config.health.witheredColour);
            } else if (player.isFrozen()) {
                healthColour = hardcore ? ColourRef.fromInt(config.health.hardcoreFrozenColour) : ColourRef.fromInt(config.health.frozenColour);
            } else {
                healthColour = hardcore ? ColourRef.fromInt(config.health.hardcoreNormalColour) : ColourRef.fromInt(config.health.normalColour);
            }
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
}
