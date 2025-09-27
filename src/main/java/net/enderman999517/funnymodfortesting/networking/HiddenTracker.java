package net.enderman999517.funnymodfortesting.networking;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HiddenTracker {
    private static final Map<UUID, Boolean> HIDDEN_FLAGS = new HashMap<>();

    public static void setHidden(UUID uuid, boolean hidden) {
        HIDDEN_FLAGS.put(uuid, hidden);
    }

    public static boolean isHidden(UUID uuid) {
        return HIDDEN_FLAGS.getOrDefault(uuid, false);
    }

    public static void remove(UUID uuid) {
        HIDDEN_FLAGS.remove(uuid);
    }

    public static Map<UUID, Boolean> getAll() {
        return HIDDEN_FLAGS;
    }
}
