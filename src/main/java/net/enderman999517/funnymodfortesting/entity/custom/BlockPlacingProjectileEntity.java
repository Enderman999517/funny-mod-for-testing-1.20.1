package net.enderman999517.funnymodfortesting.entity.custom;

import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class BlockPlacingProjectileEntity extends ThrownItemEntity {
    private Block block;
    public Item item = this.block.asItem();


    public BlockPlacingProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BlockPlacingProjectileEntity(LivingEntity livingEntity, World world) {
        super(ModEntities.BLOCK_PLACING_PROJECTILE, livingEntity, world);
    }

    public Block setBlock(Block block) {
        this.block = block;
        return block;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.TORCH;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.getWorld().setBlockState(getBlockPos(), block.getDefaultState(), 3);
        }
        this.discard();
        super.onBlockHit(blockHitResult);
    }
}
