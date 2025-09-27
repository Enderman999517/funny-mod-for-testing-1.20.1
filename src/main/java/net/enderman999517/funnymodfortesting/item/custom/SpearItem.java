package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.networking.EntityMovementTracker;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;

public class SpearItem extends SwordItem {
    private final float speedScale;
    private final float maxExtraDamage;

    public SpearItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, float speedScale, float maxDamage) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.speedScale = speedScale;
        this.maxExtraDamage = maxDamage;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient) {
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
        return super.postHit(stack, target, attacker);
    }
}
