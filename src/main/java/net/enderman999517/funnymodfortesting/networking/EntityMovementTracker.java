package net.enderman999517.funnymodfortesting.networking;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.UUID;

public class EntityMovementTracker {
    private static final HashMap<UUID, Vec3d> lastTickPos = new HashMap<>();
    private static final HashMap<UUID, Double> lastCalculatedSpeed = new HashMap<>();

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
                }
                lastTickPos.put(id, currentPos);
            }
        });
    }

    public static double getHSpeed(UUID id) {
        return lastCalculatedSpeed.getOrDefault(id, 0.0);
    }
}
