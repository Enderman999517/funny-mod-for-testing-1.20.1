package net.enderman999517.funnymodfortesting.networking;

import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EntityPosTracker {
    private static final HashMap<UUID, List<Vec3d>> poss = new HashMap<>();

    public static void putPosToList(UUID uuid, Vec3d pos) {
        if (poss.get(uuid) == null) {
            poss.put(uuid, new ArrayList<>());
            poss.get(uuid).add(pos);
        } else poss.get(uuid).add(pos);
    }

    public List<Vec3d> getPoss(UUID uuid) {
        return poss.getOrDefault(uuid, null);
    }
}
