package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RingItem extends Item {
    private int shaderMode;
    private final List<RingCallback> callbacks = new ArrayList<>();
    public RingItem(Settings settings) {
        super(settings);
    }

    public void registerRingCallback(RingCallback callback) {
        this.callbacks.add(callback);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        ItemStack stack1;
        if (entity instanceof PlayerEntity playerEntity) {
            stack1 = playerEntity.getStackInHand(Hand.OFF_HAND);
        } else stack1 = Items.AIR.getDefaultStack();

        if (entity instanceof ModEntityData modEntityData && stack1.isOf(ModItems.RING)) {
            if (world.isClient) {
                this.callbacks.get(shaderMode).inventoryTick(stack, world, entity, slot, selected);
            }
            if (!world.isClient && !modEntityData.isHidden()) {
                modEntityData.setHidden(!modEntityData.isHidden());
                modEntityData.setRenderingOverlay(!modEntityData.isRenderingOverlay());
            }
        }
    }
}
