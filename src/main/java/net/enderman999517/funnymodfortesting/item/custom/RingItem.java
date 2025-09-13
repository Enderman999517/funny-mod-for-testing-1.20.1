package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RingItem extends Item {
    public RingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (user instanceof ModEntityData modEntityData) {
                if (!modEntityData.isHidden()) {
                    modEntityData.setHidden(true);
                } else {
                    modEntityData.setHidden(false);
                }
                return TypedActionResult.success(user.getStackInHand(hand), false);
            }
        } return TypedActionResult.success(user.getStackInHand(hand), true);
    }
}
