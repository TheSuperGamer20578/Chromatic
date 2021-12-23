package com.TheSuperGamer20578.chromatic;

import org.jglr.jchroma.utils.ColorRef;

public class Layouts {
    private static final ColorRef BGD = ColourRef.fromInt(ModConfig.INSTANCE.backgroundColour);
    private static final ColorRef CHT = ColourRef.fromInt(ModConfig.INSTANCE.chatColour);
    private static final ColorRef MOV = ColourRef.fromInt(ModConfig.INSTANCE.movementColour);
    private static final ColorRef ESC = ColourRef.fromInt(ModConfig.INSTANCE.ESCColour);
    private static final ColorRef TAB = ColourRef.fromInt(ModConfig.INSTANCE.tabColour);
    private static final ColorRef MOD = ColourRef.fromInt(ModConfig.INSTANCE.modifierColour);
    private static final ColorRef INV = ColourRef.fromInt(ModConfig.INSTANCE.inventoryColour);
    public static final ColorRef[][] MAIN = {
            {BGD, ESC, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, TAB, INV, MOV, INV, BGD, CHT, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, BGD, MOV, MOV, MOV, INV, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, MOD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, CHT, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD},
            {BGD, MOD, BGD, BGD, BGD, BGD, BGD, MOV, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD, BGD}
    };
}
