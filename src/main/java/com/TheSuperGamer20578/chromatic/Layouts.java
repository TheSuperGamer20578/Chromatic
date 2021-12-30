package com.TheSuperGamer20578.chromatic;

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
}
