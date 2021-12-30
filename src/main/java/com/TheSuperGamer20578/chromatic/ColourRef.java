package com.TheSuperGamer20578.chromatic;

import org.jglr.jchroma.utils.ColorRef;

public class ColourRef extends ColorRef {
    public ColourRef(int red, int green, int blue) {
        super(red, green, blue);
    }

    public static ColourRef fromInt(int colour) {
        short red = (short) (colour >> 16);
        short green = (short) (colour >> 8 & 0xff);
        short blue = (short) (colour & 0xff);
        return new ColourRef(red, green, blue);
    }

    public ColourRef multiply(Number number) {
        double n = number.doubleValue();
        return new ColourRef((int) (getRed() * n), (int) (getGreen() * n), (int) (getBlue() * n));
    }
}
