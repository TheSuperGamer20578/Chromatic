package io.github.thesupergamer20578.chromatic;

import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.gui.screen.multiplayer.*;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.client.gui.screen.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum Screens {
    // Ingame
    ANVIL(AnvilScreen.class),
    BOOK_EDIT(BookEditScreen.class),
    COMMAND_BLOCK(CommandBlockScreen.class),
    CREATIVE_INVENTORY(CreativeInventoryScreen.class),
    JIGSAW(JigsawBlockScreen.class),
    MINECART_COMMAND_BLOCK(MinecartCommandBlockScreen.class),
    SIGN(SignEditScreen.class),
    STRUCTURE_BLOCK(StructureBlockScreen.class),

    // Multiplayer
    SOCIAL_INTERACTIONS(SocialInteractionsScreen.class),

    // Options
    CONTROLS(ControlsOptionsScreen.class),
    KEYBINDS(KeybindsScreen.class),

    // World
    CREATE_WORLD(CreateWorldScreen.class),
    EDIT_WORLD(EditWorldScreen.class),

    // Main
    ADD_SERVER(AddServerScreen.class),
    CHAT(ChatScreen.class),
    DEATH(DeathScreen.class),
    DIRECT_CONNECT(DirectConnectScreen.class),
    SLEEPING(SleepingChatScreen.class),
    NONE(null),
    OTHER(Screen.class);

    @Nullable public final Class<? extends Screen> value;
    Screens(@Nullable Class<? extends Screen> screen) {
        value = screen;
    }

    private static final Map<@Nullable Class<? extends Screen>, Screens> cache = new HashMap<>();

    public static Screens of(@Nullable Class<? extends Screen> key) {
        return cache.computeIfAbsent(key, k -> {
            if (k == null) return NONE;
            for (Screens screen : Screens.values()) {
                if (screen.value != null && screen.value.isAssignableFrom(k)) {
                    return screen;
                }
            }
            throw new IllegalStateException("Unreachable");
        });
    }
}
