package net.enderman999517.funnymodfortesting.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.enderman999517.funnymodfortesting.world.biome.ModBiomes;
import net.kyrptonaught.customportalapi.portal.PortalPlacer;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockLocating;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(PortalPlacer.class)
public class PortalPlacerMixin {
    @WrapMethod(method = "createDestinationPortal")
    private static Optional<BlockLocating.Rectangle> dontGenerateReturnPortalsInOceandim(ServerWorld world, BlockPos blockPos, BlockState frameBlock, Direction.Axis axis, Operation<Optional<BlockLocating.Rectangle>> original) {
        // probably not the best way but it works so oh well
        if (!world.isClient && !world.getBiome(blockPos).equals(ModBiomes.OCEAN_DIM_BIOME)) {
            return Optional.empty();
        } else return original.call(world, blockPos, frameBlock, axis);
    }
}
