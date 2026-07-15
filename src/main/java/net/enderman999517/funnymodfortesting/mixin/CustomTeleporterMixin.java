package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.world.biome.ModBiomes;
import net.kyrptonaught.customportalapi.util.CustomTeleporter;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CustomTeleporter.class)
public class CustomTeleporterMixin {
    @Inject(method = "idkWhereToPutYou", at = @At("HEAD"), cancellable = true)
    private static void tpToMaxHeightInOceandim(ServerWorld world, Entity entity, BlockPos pos, CallbackInfoReturnable<TeleportTarget> cir) {
        // probably not the best way but it works so oh well
        if (!world.getBiome(pos).equals(ModBiomes.OCEAN_DIM_BIOME)) {
            TeleportTarget target = new TeleportTarget(new Vec3d(pos.getX() + .5, 384, pos.getZ() + .5), entity.getVelocity(), entity.getYaw(), entity.getPitch());
            cir.setReturnValue(target);
        }
    }
}
