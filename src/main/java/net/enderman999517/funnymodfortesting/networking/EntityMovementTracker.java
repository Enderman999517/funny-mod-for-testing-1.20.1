package net.enderman999517.funnymodfortesting.networking;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.UUID;

public class EntityMovementTracker {
    private static final HashMap<UUID, Vec3d> lastTickPos = new HashMap<>();
    private static final HashMap<UUID, Double> lastCalculatedSpeed = new HashMap<>();
    private static final HashMap<UUID, Double> lastCalculatedDx = new HashMap<>();
    private static final HashMap<UUID, Double> lastCalculatedDz = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (Entity entity : server.getOverworld().iterateEntities()) {
                UUID id = entity.getUuid();
                Vec3d currentPos = entity.getPos();
                Vec3d last = lastTickPos.get(id);
                if (last != null) {
                    double dx = currentPos.x - last.x;
                    double dz = currentPos.z - last.z;
                    double horizontalSpeed = Math.sqrt(dx * dx + dz * dz);
                    lastCalculatedSpeed.put(id, horizontalSpeed);
                    lastCalculatedDx.put(id, dx);
                    lastCalculatedDz.put(id, dz);
                }
                lastTickPos.put(id, currentPos);
            }
        });
    }
    public static double getHSpeed(UUID id) {
        return lastCalculatedSpeed.getOrDefault(id, 0.0);
    }
    public static Vec3d getLastTickPos(UUID id) {
        return lastTickPos.getOrDefault(id, new Vec3d(0,0,0));
    }
    public static double getDx(UUID id) {
        return lastCalculatedDx.getOrDefault(id, 0.0);
    }
    public static double getDz(UUID id) {
        return lastCalculatedDz.getOrDefault(id, 0.0);
    }
}
