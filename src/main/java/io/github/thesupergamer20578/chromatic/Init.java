package io.github.thesupergamer20578.chromatic;

import io.github.thesupergamer20578.chroma.Chroma;
import io.github.thesupergamer20578.chroma.WaveDirection;
import io.github.thesupergamer20578.chroma.drivers.Driver;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import org.freedesktop.dbus.exceptions.DBusException;


public class Init implements ModInitializer {
    @Override
    public void onInitialize() {
        ModConfig.init();
        Driver driver;
        try {
            driver = Chroma.getDriver();
            assert driver != null;
        } catch (DBusException e) {
            throw new RuntimeException(e);
        }
        driver.waveKeyboardEffect(WaveDirection.LEFT_TO_RIGHT);
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal("chromaticconfig")
            .executes(context -> {
                MinecraftClient.getInstance().send(() -> MinecraftClient.getInstance().setScreen(AutoConfig.getConfigScreen(ModConfig.class, null).get()));
                return 1;
            })
        ));
        Util.LOGGER.info("Chromatic initialised");
    }

}
