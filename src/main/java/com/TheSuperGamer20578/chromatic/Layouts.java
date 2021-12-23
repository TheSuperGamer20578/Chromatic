package com.TheSuperGamer20578.chromatic;

import org.jglr.jchroma.utils.ColorRef;

public class Layouts {
    private static final ColourRef BGD = ColourRef.fromInt(ModConfig.INSTANCE.backgroundColour);
    private static final ColourRef CHT = ColourRef.fromInt(ModConfig.INSTANCE.chatColour);
    private static final ColourRef MOV = ColourRef.fromInt(ModConfig.INSTANCE.movementColour);
    private static final ColourRef ESC = ColourRef.fromInt(ModConfig.INSTANCE.ESCColour);
    private static final ColourRef TAB = ColourRef.fromInt(ModConfig.INSTANCE.tabColour);
    private static final ColourRef MOD = ColourRef.fromInt(ModConfig.INSTANCE.modifierColour);
    private static final ColourRef INV = ColourRef.fromInt(ModConfig.INSTANCE.inventoryColour);
    public static final ColourRef[][] MAIN = {
            {BGD, ESC, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, TAB, INV, MOV, INV, BGD, CHT, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, BGD, MOV, MOV, MOV, INV, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, MOD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, CHT, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, MOD, BGD, BGD, BGD, BGD, BGD, MOV, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD}
    };
}
