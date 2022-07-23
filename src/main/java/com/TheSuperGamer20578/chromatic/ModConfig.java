package com.TheSuperGamer20578.chromatic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = "chromatic")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.Excluded
    public static ModConfig INSTANCE;

    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    @ConfigEntry.ColorPicker
    public int backgroundColour = 0x000000;

    @ConfigEntry.ColorPicker
    public int chatColour = 0xffffff;

    @ConfigEntry.ColorPicker
    public int oxygenColour = 0x0000ff;

    @ConfigEntry.ColorPicker
    public int hungerColour = 0xffff00;

    @ConfigEntry.ColorPicker
    public int movementColour = 0x00ffff;

    @ConfigEntry.ColorPicker
    public int ESCColour = 0xff0000;

    @ConfigEntry.ColorPicker
    public int tabColour = 0x00ff00;

    @ConfigEntry.ColorPicker
    public int modifierColour = 0x00ff00;

    @ConfigEntry.ColorPicker
    public int inventoryColour = 0x0000ff;

    @ConfigEntry.Gui.CollapsibleObject
    public Health health = new Health();

    @ConfigEntry.Gui.CollapsibleObject
    public Items items = new Items();

    public static class Health {
        @ConfigEntry.ColorPicker
        public int normalColour = 0xff0000;

        @ConfigEntry.ColorPicker
        public int poisonedColour = 0x00ff00;

        @ConfigEntry.ColorPicker
        public int witheredColour = 0x1a0000;

        @ConfigEntry.ColorPicker
        public int frozenColour = 0x00eaff;

        @ConfigEntry.ColorPicker
        public int hardcoreNormalColour = 0xff4000;

        @ConfigEntry.ColorPicker
        public int hardcorePoisonedColour = 0x40ff00;

        @ConfigEntry.ColorPicker
        public int hardcoreWitheredColour = 0x1a0600;

        @ConfigEntry.ColorPicker
        public int hardcoreFrozenColour = 0x00aaff;
    }

    public static class Items {
        @ConfigEntry.ColorPicker
        public int selectedColour = 0x00ff00;

        @ConfigEntry.ColorPicker
        public int toolColour = 0x00ffff;

        @ConfigEntry.ColorPicker
        public int stackableColour = 0x0000ff;

        @ConfigEntry.ColorPicker
        public int otherColour = 0xffffff;

        @ConfigEntry.ColorPicker
        public int emptyColour = 0x000000;

        @ConfigEntry.BoundedDiscrete(max = 0xff)
        public int minBrightness = 50;
    }
}
