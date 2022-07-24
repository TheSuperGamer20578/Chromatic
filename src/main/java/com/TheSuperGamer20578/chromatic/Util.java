package com.TheSuperGamer20578.chromatic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class Util {
    public static final Logger LOGGER = LogManager.getLogger("Chromatic");
    public static Queue<IEffect> effectQueue = new LinkedList<>();
}
