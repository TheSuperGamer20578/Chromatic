package io.github.thesupergamer20578.chromatic.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public final class Chromatic {
    private static Chromatic INSTANCE;
    static final Logger logger = LogManager.getLogger("Chromatic");
    static final Queue<Effect> effectQueue = new LinkedList<>();
    static final Map<Byte, List<Overlay>> overlays = new TreeMap<>();
    static final List<Module> modules = new ArrayList<>();

    private Chromatic() {}

    static Chromatic getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Chromatic();
        }
        return INSTANCE;
    }

    public static void addModule(Module module) {
        modules.add(module);
        module.init(getInstance());
    }

    public void queueEffect(Effect effect) {
        effectQueue.add(effect);
    }

    public void applyOverlay(Overlay overlay, byte priority) {
        overlays.computeIfAbsent(priority, k -> new ArrayList<>()).add(overlay);
    }
}
