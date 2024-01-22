package io.github.thesupergamer20578.chromatic.core;

import io.github.thesupergamer20578.chromatic.api.Chromatic;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    @Override
    public void onInitialize() {
        Chromatic.addModule(new Core());
    }
}
