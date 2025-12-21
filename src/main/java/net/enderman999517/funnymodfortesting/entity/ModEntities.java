package net.enderman999517.funnymodfortesting.entity;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.entity.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static void registerModEntities() {
        FunnyModForTesting.LOGGER.info("Registering Entities for " + FunnyModForTesting.MOD_ID);
    }

    public static final EntityType<ExplosiveProjectileEntity> EXPLOSIVE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(FunnyModForTesting.MOD_ID, "explosive_projectile"),
            FabricEntityTypeBuilder.<ExplosiveProjectileEntity>create(SpawnGroup.MISC, ExplosiveProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f)).build());

    public static final EntityType<BlockPlacingProjectileEntity> BLOCK_PLACING_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(FunnyModForTesting.MOD_ID, "block_placing_projectile"),
            FabricEntityTypeBuilder.<BlockPlacingProjectileEntity>create(SpawnGroup.MISC, BlockPlacingProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f)).build());

    public static final EntityType<StatusEffectProjectileEntity> STATUS_EFFECT_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(FunnyModForTesting.MOD_ID, "status_effect_projectile"),
            FabricEntityTypeBuilder.<StatusEffectProjectileEntity>create(SpawnGroup.MISC, StatusEffectProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f)).build());

    public static final EntityType<AmoghEntity> AMOGH = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(FunnyModForTesting.MOD_ID, "amogh"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AmoghEntity::new)
                    .dimensions(EntityDimensions.fixed(3f, 3f)).build());

    public static final EntityType<HiddenEntity> HIDDEN = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(FunnyModForTesting.MOD_ID, "hidden"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HiddenEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f)).build());

}
