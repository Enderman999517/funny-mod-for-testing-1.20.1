package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            this.callbacks.get(shaderMode).use(world, user, hand);
        }
        if (!world.isClient) {
            if (user instanceof ModEntityData modEntityData) {
                modEntityData.setHidden(!modEntityData.isHidden());
                modEntityData.setRenderingOverlay(!modEntityData.isRenderingOverlay());
            }
            return TypedActionResult.success(user.getStackInHand(hand), false);
        } return TypedActionResult.success(user.getStackInHand(hand), true);
    }
}
