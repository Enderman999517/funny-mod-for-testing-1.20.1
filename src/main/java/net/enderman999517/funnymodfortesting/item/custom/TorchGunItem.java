package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.entity.custom.BlockPlacingProjectileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TorchGunItem extends Item {
    public static Block block = Blocks.TORCH;

    public TorchGunItem(Settings settings) {
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
                SoundEvents.BLOCK_LAVA_POP,
                SoundCategory.NEUTRAL,
                0.5F,
                0.58F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!world.isClient) {
            BlockPlacingProjectileEntity blockPlacingProjectileEntity = new BlockPlacingProjectileEntity(user, world);
            blockPlacingProjectileEntity.setItem(itemStack);
            blockPlacingProjectileEntity.setBlock(block);
            blockPlacingProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1F, 0.0F);
            world.spawnEntity(blockPlacingProjectileEntity);
        }

        //user.incrementStat(Stats.USED.getOrCreateStat(this));
        //if (!user.getAbilities().creativeMode) {
        //    itemStack.decrement(1);
        //}

        return TypedActionResult.success(itemStack, world.isClient);
    }

}
