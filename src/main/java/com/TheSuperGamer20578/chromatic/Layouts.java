package com.TheSuperGamer20578.chromatic;

import com.TheSuperGamer20578.chromatic.mixin.EntityAccessor;
import io.github.thesupergamer20578.chroma.Colour;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Layouts {
    private static final Colour BLACK = new Colour(0x000000);

    public static Colour[][] main() {
        ModConfig config = ModConfig.INSTANCE;
        Colour bgd = new Colour(config.backgroundColour);
        Colour cht = new Colour(config.chatColour);
        Colour mov = new Colour(config.movementColour);
        Colour esc = new Colour(config.ESCColour);
        Colour tab = new Colour(config.tabColour);
        Colour mod = new Colour(config.modifierColour);
        Colour inv = new Colour(config.inventoryColour);
        return new Colour[][] {
                {bgd, esc, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, tab, inv, mov, inv, bgd, cht, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, bgd, mov, mov, mov, inv, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, mod, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, cht, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd},
                {bgd, mod, bgd, bgd, bgd, bgd, bgd, mov, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd, bgd}
        };
    }

    public static void applyStatus(Colour[][] layout, @NotNull ClientPlayerEntity player, boolean tint) {
        ModConfig config = ModConfig.INSTANCE;
        if (!player.getAbilities().invulnerable) {
            double health = player.getHealth() / player.getMaxHealth();
            boolean hardcore = player.world.getLevelProperties().isHardcore();
            Colour healthColour;
            if (player.hasStatusEffect(StatusEffects.POISON)) {
                healthColour = hardcore ? new Colour(config.health.hardcorePoisonedColour) : new Colour(config.health.poisonedColour);
            } else if (player.hasStatusEffect(StatusEffects.WITHER)) {
                healthColour = hardcore ? new Colour(config.health.hardcoreWitheredColour) : new Colour(config.health.witheredColour);
            } else if (player.isFrozen()) {
                healthColour = hardcore ? new Colour(config.health.hardcoreFrozenColour) : new Colour(config.health.frozenColour);
            } else {
                healthColour = hardcore ? new Colour(config.health.hardcoreNormalColour) : new Colour(config.health.normalColour);
            }
            // TODO Tinting is not yet implemented in Chroma
//            if (tint) healthColour = healthColour.tint(tint(player));
            layout[0][3] = healthColour.multiply(health == 0 ? 0 : health > .25 ? 1 : health * 4);
            layout[0][4] = healthColour.multiply(health < .25 ? 0 : health > .5 ? 1 : (health - .25) * 4);
            layout[0][5] = healthColour.multiply(health < .5 ? 0 : health > .75 ? 1 : (health - .5) * 4);
            layout[0][6] = healthColour.multiply(health < .75 ? 0 : (health - .75) * 4);

            if (player.getAir() < player.getMaxAir()) {
                float oxygen = (float) player.getAir() / player.getMaxAir();
                Colour oxygenColour = new Colour(config.oxygenColour);
                // TODO Tinting is not yet implemented in Chroma
//                if (tint) oxygenColour = oxygenColour.tint(tint(player));
                layout[0][7] = oxygenColour.multiply(oxygen == 0 ? 0 : oxygen > .25 ? 1 : oxygen * 4);
                layout[0][8] = oxygenColour.multiply(oxygen < .25 ? 0 : oxygen > .5 ? 1 : (oxygen - .25) * 4);
                layout[0][9] = oxygenColour.multiply(oxygen < .5 ? 0 : oxygen > .75 ? 1 : (oxygen - .5) * 4);
                layout[0][10] = oxygenColour.multiply(oxygen < .75 ? 0 : (oxygen - .75) * 4);
            }

            float hunger = player.getHungerManager().getFoodLevel() / 20f;
            Colour hungerColour = new Colour(config.hungerColour);
            // TODO Tinting is not yet implemented in Chroma
//            if (tint) hungerColour = hungerColour.tint(tint(player));
            layout[0][11] = hungerColour.multiply(hunger == 0 ? 0 : hunger > .25 ? 1 : hunger * 4);
            layout[0][12] = hungerColour.multiply(hunger < .25 ? 0 : hunger > .5 ? 1 : (hunger - .25) * 4);
            layout[0][13] = hungerColour.multiply(hunger < .5 ? 0 : hunger > .75 ? 1 : (hunger - .5) * 4);
            layout[0][14] = hungerColour.multiply(hunger < .75 ? 0 : (hunger - .75) * 4);
        }

        // TODO Tinting is not yet implemented in Chroma
        Colour selectedColour = new Colour(config.items.selectedColour);
//        if (tint) selectedColour = selectedColour.tint(tint(player));
        Colour stackableColour = new Colour(config.items.stackableColour);
//        if (tint) stackableColour = stackableColour.tint(tint(player));
        Colour toolColour = new Colour(config.items.toolColour);
//        if (tint) toolColour = toolColour.tint(tint(player));
        Colour otherColour = new Colour(config.items.otherColour);
//        if (tint) otherColour = otherColour.tint(tint(player));
        Colour emptyColour = new Colour(config.items.emptyColour);
//        if (tint) emptyColour = emptyColour.tint(tint(player));
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

    @Nullable
    public static Colour tint(@Nullable ClientPlayerEntity player) {
        ModConfig.Tint config = ModConfig.INSTANCE.tint;
        if (player == null || !config.enabled) {
            return null;
        }

        if (player.inPowderSnow || player.wasInPowderSnow) {
            return new Colour(config.powderSnowColour);
        }
        if (player.isSubmergedInWater()) {
            return new Colour(config.waterColour);
        }
        if (player.isOnFire()) {
            return new Colour(config.fireColour);
        }
        if (((EntityAccessor) player).invokeIsBeingRainedOn()) {
            if (player.world.isThundering()) {
                return new Colour(config.stormColour);
            }
            return new Colour(config.rainColour);
        }
        if (
            player.world.isRaining()
            && player.world.getBiome(player.getBlockPos()).value().getPrecipitation(player.getBlockPos()) == Biome.Precipitation.SNOW
            && !player.world.getBiome(player.getBlockPos()).value().doesNotSnow(player.getBlockPos())
            && player.world.isSkyVisible(player.getBlockPos())
            && player.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, player.getBlockPos()).getY() <= player.getBlockPos().getY()
        ) {
            return new Colour(config.snowColour);
        }
        return null;
    }

    public static void applyTint(Colour[][] layout, @Nullable ClientPlayerEntity player) {
        Colour tint = tint(player);
        if (tint == null) {
            return;
        }
        for (byte row = 0; row < layout.length; row++) {
            for (byte key = 0; key < layout[row].length; key++) {
                if (ModConfig.INSTANCE.tint.preserveBlack && layout[row][key].equals(BLACK)) {
                    continue;
                }
                // TODO Tinting is not yet implemented in Chroma
                // layout[row][key] = layout[row][key].tint(tint);
            }
        }
    }
}
