package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.minecraft.client.MinecraftClient;
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

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) return TypedActionResult.pass(user.getStackInHand(hand));

        MinecraftClient client = MinecraftClient.getInstance();

        if (client.world != null) {
            for (Entity entity : client.world.getEntities()) {
                if (entity != user && entity instanceof ModEntityData modEntityData) {
                    if (modEntityData.isHidden()) {
                        //client.world.removeEntity(entity.getId(), Entity.RemovalReason.DISCARDED);
                        FunnyModForTesting.LOGGER.info(String.valueOf(entity));
                    }
                }
            }
        }

        return TypedActionResult.success(user.getStackInHand(hand), world.isClient);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient) {
            FunnyModForTesting.LOGGER.info("test2");
            if (entity instanceof ModEntityData modEntityData) {
                modEntityData.setHidden(true);
                FunnyModForTesting.LOGGER.info("test");
            }
        }
        return ActionResult.SUCCESS;
    }
}
