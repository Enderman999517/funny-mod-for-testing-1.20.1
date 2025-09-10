package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RingItem extends Item {
    public RingItem(Settings settings) {
        super(settings);
    }

    int toggle = 0;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (toggle % 2 == 0) {
            toggle = 0;
            if (!world.isClient) {
                if (user instanceof ModEntityData modEntityData) {
                    modEntityData.setHidden(true);
                    toggle ++;
                }
                return TypedActionResult.success(user.getStackInHand(hand), false);
            }
        } else {
            if (!world.isClient) {
                if (user instanceof ModEntityData modEntityData) {
                    modEntityData.setHidden(false);
                    toggle ++;
                }
                return TypedActionResult.success(user.getStackInHand(hand), false);
            }
        }

        return TypedActionResult.success(user.getStackInHand(hand), true);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient) {
            if (entity instanceof ModEntityData modEntityData) {
                modEntityData.setHidden(true);
            }
        }
        return ActionResult.SUCCESS;
    }
}
