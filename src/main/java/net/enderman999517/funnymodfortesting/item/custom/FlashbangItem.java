package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.entity.custom.StatusEffectProjectileEntity;
import net.enderman999517.funnymodfortesting.entity.effect.ModStatusEffects;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FlashbangItem extends Item {
    public FlashbangItem(Settings settings) {
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
            StatusEffectProjectileEntity statusEffectProjectileEntity = new StatusEffectProjectileEntity(user, world);
            statusEffectProjectileEntity.setItem(itemStack);
            //statusEffectProjectileEntity.setEffect(StatusEffects.BLINDNESS);
            statusEffectProjectileEntity.setEffect(ModStatusEffects.FLASHBANG);
            statusEffectProjectileEntity.setRadius(3);
            statusEffectProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1F, 0.0F);
            statusEffectProjectileEntity.setGlowing(true);
            world.spawnEntity(statusEffectProjectileEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient);
    }
}
