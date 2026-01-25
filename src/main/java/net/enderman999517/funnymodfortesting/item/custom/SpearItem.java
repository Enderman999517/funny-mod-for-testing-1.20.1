package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.damage.ModDamageTypes;
import net.enderman999517.funnymodfortesting.networking.EntityMovementTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class SpearItem extends SwordItem {
    private final float speedScale;
    private final float maxExtraDamage;
    private boolean canDoExtraDamage = false;
    private final int maxChargedTicks;
    private final double kbStrength;
    private final int maxUseTime;

    public int chargedTicks;

    public SpearItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, float speedScale, float maxExtraDamage, int maxChargedTicks, double kbStrength, int maxUseTime) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.speedScale = speedScale;
        this.maxExtraDamage = maxExtraDamage;
        this.maxChargedTicks = maxChargedTicks;
        this.kbStrength = kbStrength;
        this.maxUseTime = maxUseTime;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient) {
            if (canDoExtraDamage) {
                double hSpeed = EntityMovementTracker.getHSpeed(attacker.getUuid());
                float extra = (float) Math.min(maxExtraDamage, hSpeed * speedScale);
                if (extra > 0f) {
                    DamageSource source = new DamageSource(
                            attacker.getWorld().getRegistryManager()
                                    .get(RegistryKeys.DAMAGE_TYPE)
                                    .entryOf(ModDamageTypes.SCYTHE_DAMAGE));
                    target.damage(ModDamageSources.of(attacker.getWorld(), ModDamageTypes.SCYTHE_DAMAGE, attacker), extra);
                }
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return maxUseTime;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        this.canDoExtraDamage = true;
        user.getItemCooldownManager().set(this, maxChargedTicks);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        this.canDoExtraDamage = false;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        chargedTicks = getMaxUseTime(stack) - remainingUseTicks;

        if (!user.getWorld().isClient) {
            List<Entity> nearby = user.getWorld().getOtherEntities(
                    user,
                    new Box(user.raycast(2, 1, true).getPos(), user.raycast(2,1,true).getPos()).expand(0.1),
                    e -> e != user && e !=user.getControllingVehicle()
            );
            for (Entity other : nearby) {
                if (other instanceof LivingEntity otherL) {
                    if (chargedTicks <= maxChargedTicks) {
                        double hSpeed = EntityMovementTracker.getHSpeed(user.getUuid()) - EntityMovementTracker.getHSpeed(otherL.getUuid());
                        double closingX = EntityMovementTracker.getDx(user.getUuid()) - EntityMovementTracker.getDx(otherL.getUuid());
                        double closingZ = EntityMovementTracker.getDz(user.getUuid()) - EntityMovementTracker.getDz(otherL.getUuid());
                        float extra = (float) Math.min(maxExtraDamage, hSpeed * speedScale) * 2 * ((float) (-chargedTicks + maxChargedTicks) / maxChargedTicks);
                        if (extra > 5f) {
                            DamageSource source = new DamageSource(
                                    user.getWorld().getRegistryManager()
                                            .get(RegistryKeys.DAMAGE_TYPE)
                                            .entryOf(DamageTypes.PLAYER_ATTACK));
                            otherL.damage(source, extra);
                            otherL.takeKnockback(kbStrength, -closingX, -closingZ);
                        }
                    }
                }
            }
        }
    }
}
