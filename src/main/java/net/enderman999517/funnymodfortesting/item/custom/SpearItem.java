package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.networking.EntityMovementTracker;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class SpearItem extends SwordItem {
    private final float speedScale;
    private final float maxExtraDamage;
    private boolean canDoExtraDamage = false;

    public SpearItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, float speedScale, float maxDamage) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.speedScale = speedScale;
        this.maxExtraDamage = maxDamage;
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
                                    .entryOf(ModDamageSources.SCYTHE_DAMAGE));
                    target.damage(source, extra);
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
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        this.canDoExtraDamage = true;
        user.getItemCooldownManager().set(this, 20);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        this.canDoExtraDamage = false;
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}
