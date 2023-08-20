package io.github.thesupergamer20578.chromatic;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@SuppressWarnings("CanBeFinal")
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

    @ConfigEntry.Gui.CollapsibleObject
    public Tint tint = new Tint();

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

        @ConfigEntry.ColorPicker
        public int regenColour = 0x00ff00;

        public int regenFlashDuration = 10;

        @ConfigEntry.ColorPicker
        public int damageColour = 0xff1100;

        public int damageFlashDuration = 5;

        public int damageThreshold = 5;
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

    public static class Tint {
        public boolean enabled = true;

        public boolean preserveBlack = true;

        @ConfigEntry.ColorPicker
        public int waterColour = 0x5555aa;

        @ConfigEntry.ColorPicker
        public int fireColour = 0xff5555;

        @ConfigEntry.ColorPicker
        public int rainColour = 0x00aaaa;

        @ConfigEntry.ColorPicker
        public int stormColour = 0x2f444a;

        @ConfigEntry.ColorPicker
        public int powderSnowColour = 0xffffff;

        @ConfigEntry.ColorPicker
        public int snowColour = 0xdddddd;
    }
}
