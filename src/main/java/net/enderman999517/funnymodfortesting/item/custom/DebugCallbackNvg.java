package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@FunctionalInterface
public interface DebugCallbackNvg {
    void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected);
}
