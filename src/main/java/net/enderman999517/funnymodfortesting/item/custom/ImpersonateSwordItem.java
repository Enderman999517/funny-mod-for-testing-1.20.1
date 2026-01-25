package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKeys;

public class ImpersonateSwordItem extends SwordItem {

    public ImpersonateSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean damage(DamageSource source) {
        DamageSource damageSource = new DamageSource(
                source.getAttacker().getWorld().getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(ModDamageSources.IMPERSONATE_DAMAGE));

        return super.damage(damageSource);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.postHit(stack, target, attacker);
    }
}
