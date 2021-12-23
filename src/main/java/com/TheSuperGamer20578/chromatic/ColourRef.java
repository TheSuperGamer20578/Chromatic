package com.TheSuperGamer20578.chromatic;

import org.jglr.jchroma.utils.ColorRef;

public class ColourRef extends ColorRef {
    public ColourRef(int red, int green, int blue) {
        super(red, green, blue);
    }

    public static ColourRef fromInt(int colour) {
        short red = (short) (colour >> 4);
        short green = (short) (colour >> 2 & 0xff);
        short blue = (short) (colour & 0xff);
        return new ColourRef(red, green, blue);
    }
}
