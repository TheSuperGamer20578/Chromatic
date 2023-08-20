package io.github.thesupergamer20578.chromatic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

public final class Util {
    private Util() {}
    public static final Logger LOGGER = LogManager.getLogger("Chromatic");
    public static final Queue<Effect> effectQueue = new LinkedList<>();
}
