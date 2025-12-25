package net.enderman999517.funnymodfortesting.entity.custom;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.entity.ai.HiddenAttackGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;

public class HiddenEntity extends HostileEntity {
    private static final TrackedData<Boolean> HIDDEN = DataTracker.registerData(HiddenEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS).setThickenFog(true).setDarkenSky(true);
    private boolean tracking = false;
    private int ticksRemaining = 100;

    public HiddenEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createHiddenAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5);
    }

    public ServerBossBar getBossBar() {
        return this.bossBar;
    }
    public boolean getTracking() {
        return this.tracking;
    }

    @Override
    public void tick() {
        if (this instanceof ModEntityData modEntityData) {
            if (!getEntityWorld().isClient) {
                if (!modEntityData.isHidden()) {
                    modEntityData.setHidden(true);
                    modEntityData.setRenderingOverlay(true);
                }
                if (this.getHealth() <= this.getMaxHealth()/2) {
                    triggerEvent();
                }
            }
        }
        super.tick();
    }

    private void triggerEvent() {
        if (this.ticksRemaining != 0) {
            this.goalSelector.disableControl(Goal.Control.JUMP);
            this.goalSelector.disableControl(Goal.Control.MOVE);
            this.ticksRemaining--;
        } else {
            this.goalSelector.enableControl(Goal.Control.JUMP);
            this.goalSelector.enableControl(Goal.Control.MOVE);
        }

        LivingEntity livingEntity = this.getTarget();
        ServerWorld serverWorld = (ServerWorld)this.getWorld();

        if (livingEntity != null) {
            int x = MathHelper.floor(this.getX());
            int y = MathHelper.floor(this.getY());
            int z = MathHelper.floor(this.getZ());
            ZombieEntity zombieEntity = new ZombieEntity(this.getWorld());

            for (int i = 0; i < 50; i++) {
                int x1 = x + MathHelper.nextInt(this.random, 1, 4) * MathHelper.nextInt(this.random, -1, 1);
                int y1 = y + MathHelper.nextInt(this.random, 1, 4) * MathHelper.nextInt(this.random, -1, 1);
                int z1 = z + MathHelper.nextInt(this.random, 1, 4) * MathHelper.nextInt(this.random, -1, 1);
                BlockPos blockPos = new BlockPos(x1, y1, z1);

                EntityType<?> entityType = zombieEntity.getType();
                SpawnRestriction.Location location = SpawnRestriction.getLocation(entityType);
                if (SpawnHelper.canSpawn(location, this.getWorld(), blockPos, entityType)
                        && SpawnRestriction.canSpawn(entityType, serverWorld, SpawnReason.TRIGGERED, blockPos, this.getWorld().random)) {
                    zombieEntity.setPosition(x1, y1, z1);

                    if (!this.getWorld().isPlayerInRange(x1, y1, z1, 4.0)
                            && this.getWorld().doesNotIntersectEntities(zombieEntity)
                            && this.getWorld().isSpaceEmpty(zombieEntity)
                            && !this.getWorld().containsFluid(zombieEntity.getBoundingBox())) {
                        zombieEntity.setTarget(livingEntity);
                        zombieEntity.initialize(serverWorld, this.getWorld().getLocalDifficulty(zombieEntity.getBlockPos()), SpawnReason.TRIGGERED, null, null);
                        serverWorld.spawnEntityAndPassengers(zombieEntity);

                        //this.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS)
                        //        .addPersistentModifier(new EntityAttributeModifier("Zombie reinforcement caller charge", -0.05F, EntityAttributeModifier.Operation.ADDITION));
                        //zombieEntity.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS)
                        //        .addPersistentModifier(new EntityAttributeModifier("Zombie reinforcement callee charge", -0.05F, EntityAttributeModifier.Operation.ADDITION));
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new HiddenAttackGoal(this, 1, false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 10));
        this.goalSelector.add(4, new WanderAroundGoal(this, 1, 3));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HIDDEN, true);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.dataTracker.get(HIDDEN)) {
            nbt.putBoolean("hidden", true);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.dataTracker.set(HIDDEN, nbt.getBoolean("hidden"));
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        this.bossBar.setPercent(0f);
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        if (player instanceof ModEntityData modEntityData && modEntityData.isHidden()) {
            this.bossBar.addPlayer((ServerPlayerEntity) modEntityData);
        }
        tracking = true;
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
        tracking = false;
    }
}
