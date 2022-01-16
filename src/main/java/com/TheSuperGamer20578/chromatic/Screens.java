package com.TheSuperGamer20578.chromatic;

import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.gui.screen.multiplayer.*;
import net.minecraft.client.gui.screen.option.*;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.screen.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum Screens {
    // Advancements
    ADVANCEMENTS(AdvancementsScreen.class),

    // Ingame
    ANVIL(AnvilScreen.class),
    BEACON(BeaconScreen.class),
    BLAST_FURNACE(BlastFurnaceScreen.class),
    BOOK_EDIT(BookEditScreen.class),
    BOOK(BookScreen.class),
    BREWING(BrewingStandScreen.class),
    CARTOGRAPHY(CartographyTableScreen.class),
    COMMAND_BLOCK(CommandBlockScreen.class),
    CRAFTING(CraftingScreen.class),
    CREATIVE_INVENTORY(CreativeInventoryScreen.class),
    ENCHANTING(EnchantmentScreen.class),
    FORGING(ForgingScreen.class),
    FURNACE(FurnaceScreen.class),
    GRINDSTONE(GrindstoneScreen.class),
    HOPPER(HopperScreen.class),
    HORSE(HorseScreen.class),
    INVENTORY(InventoryScreen.class),
    JIGSAW(JigsawBlockScreen.class),
    LECTERN(LecternScreen.class),
    LOOM(LoomScreen.class),
    MERCHANT(MerchantScreen.class),
    MINECART_COMMAND_BLOCK(MinecartCommandBlockScreen.class),
    SHULKER(ShulkerBoxScreen.class),
    SIGN(SignEditScreen.class),
    SMITHING(SmithingScreen.class),
    SMOKER(SmokerScreen.class),
    STONE_CUTTER(StonecutterScreen.class),
    STRUCTURE_BLOCK(StructureBlockScreen.class),

    // Multiplayer
    SERVERS(MultiplayerScreen.class),
    MULTIPLAYER_WARNING(MultiplayerWarningScreen.class),
    SOCIAL_INTERACTIONS(SocialInteractionsScreen.class),

    // Options
    ACCESSIBILITY(AccessibilityOptionsScreen.class),
    CHAT_OPTIONS(ChatOptionsScreen.class),
    CONTROLS(ControlsOptionsScreen.class),
    GAME_OPTIONS(GameOptionsScreen.class),
    KEYBINDS(KeybindsScreen.class),
    LANGUAGES(LanguageOptionsScreen.class),
    MOUSE_OPTIONS(MouseOptionsScreen.class),
    ONLINE_OPTIONS(OnlineOptionsScreen.class),
    OPTIONS(OptionsScreen.class),
    SKIN_OPTIONS(SkinOptionsScreen.class),
    SOUND(SoundOptionsScreen.class),
    VIDEO_SETTINGS(VideoOptionsScreen.class),

    // Pack
    RESOURCE_PACKS(PackScreen.class),

    // World
    CREATE_WORLD(CreateWorldScreen.class),
    GAME_RULES(EditGameRulesScreen.class),
    EDIT_WORLD(EditWorldScreen.class),
    OPTIMIZE_WORLD(OptimizeWorldScreen.class),
    SELECT_WORLD(SelectWorldScreen.class),

    // Main
    ADD_SERVER(AddServerScreen.class),
    BACKUP_PROMPT(BackupPromptScreen.class),
    CHAT(ChatScreen.class),
    CONFIRM_LINK(ConfirmChatLinkScreen.class),
    CONNECT(ConnectScreen.class),
    CREDITS(CreditsScreen.class),
    CUSTOMIZE_BUFFET(CustomizeBuffetLevelScreen.class),
    CUSTOMIZE_FLAT(CustomizeFlatLevelScreen.class),
    DATAPACK_FAIL(DatapackFailureScreen.class),
    DEATH(DeathScreen.class),
    DEMO(DemoScreen.class),
    DIALOG(DialogScreen.class),
    DIRECT_CONNECT(DirectConnectScreen.class),
    DISCONNECTED(DisconnectedScreen.class),
    DOWNLOAD_TERRAIN(DownloadingTerrainScreen.class),
    FATAL_ERROR(FatalErrorScreen.class),
    MENU(GameMenuScreen.class),
    GAMEMODE_SELECT(GameModeSelectionScreen.class),
    LOADING(LevelLoadingScreen.class),
    NOTICE(NoticeScreen.class),
    LAN(OpenToLanScreen.class),
    OUT_OF_MEMORY(OutOfMemoryScreen.class),
    PRESETS(PresetsScreen.class),
    PROGRESS(ProgressScreen.class),
    SAVE(SaveLevelScreen.class),
    SLEEPING(SleepingChatScreen.class),
    STATS(StatsScreen.class),
    TITLE(TitleScreen.class),
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
