package net.enderman999517.funnymodfortesting.block.entity;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.block.entity.renderer.MikuBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<MikuBlockEntity> MIKU_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(FunnyModForTesting.MOD_ID, "miku_be"),
                    FabricBlockEntityTypeBuilder.create(MikuBlockEntity::new,
                            ModBlocks.MIKU_PLUSH).build());

    public static void registerBlockEntities() {
        FunnyModForTesting.LOGGER.info("Registering block entities for " + FunnyModForTesting.MOD_ID);
    }
}
