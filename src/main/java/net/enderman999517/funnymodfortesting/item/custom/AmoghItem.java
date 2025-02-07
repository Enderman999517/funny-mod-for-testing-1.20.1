package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.entity.custom.ExplosiveProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AmoghItem extends Item {
    public AmoghItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ITEM_CROSSBOW_SHOOT,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );


        if (!world.isClient) {
            ExplosiveProjectileEntity explosiveProjectileEntity = new ExplosiveProjectileEntity(user, world);
            explosiveProjectileEntity.setItem(itemStack);
            explosiveProjectileEntity.setPower(3);
            explosiveProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2F, 0.0F);
            explosiveProjectileEntity.setGlowing(true);
            world.spawnEntity(explosiveProjectileEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient);
    }
}
