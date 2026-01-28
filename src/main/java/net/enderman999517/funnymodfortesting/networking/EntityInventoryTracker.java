package net.enderman999517.funnymodfortesting.networking;

import net.minecraft.entity.player.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EntityInventoryTracker {
    private static final HashMap<UUID, List<PlayerInventory>> inventories = new HashMap<>();

    public static List<PlayerInventory> getInvList(UUID uuid) {
        return inventories.getOrDefault(uuid, null);
    }
    public static void putInvToList(UUID uuid, PlayerInventory playerInventory) {
        if (inventories.get(uuid) == null) {
            inventories.put(uuid, new ArrayList<>());
            inventories.get(uuid).add(playerInventory);
        } else inventories.get(uuid).add(playerInventory);
    }
    public static void clear(UUID uuid) {
        inventories.get(uuid).clear();
    }
}
