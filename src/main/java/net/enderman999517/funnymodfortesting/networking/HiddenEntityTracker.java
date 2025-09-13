package net.enderman999517.funnymodfortesting.networking;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HiddenEntityTracker {
    private static final Set<Integer> HIDDEN_ENTITY_IDS = ConcurrentHashMap.newKeySet();

    public static void hide(int entityId) {
        HIDDEN_ENTITY_IDS.add(entityId);
    }

    public static void show(int entityId) {
        HIDDEN_ENTITY_IDS.remove(entityId);
    }

    public static boolean isHidden(int entityId) {
        return HIDDEN_ENTITY_IDS.contains(entityId);
    }

    public static void clear() {
        HIDDEN_ENTITY_IDS.clear();
    }
}
