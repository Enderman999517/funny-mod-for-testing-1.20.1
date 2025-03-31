package net.enderman999517.funnymodfortesting.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

@FunctionalInterface
public interface DebugCallbackUse {
    void use(World world, PlayerEntity player, Hand hand);
}
