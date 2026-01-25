package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ImpersonateSwordItem extends SwordItem {

    public ImpersonateSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    //@Override
    //public boolean damage(DamageSource source) {
    //    Entity attacker = source.getAttacker();
    //    if (attacker != null) {
    //        return super.damage(ModDamageSources.of(attacker.getWorld(), ModDamageTypes.IMPERSONATE_DAMAGE, attacker));
    //    } else return false;
    //}

    //@Override
    //public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
    //    return super.postHit(stack, target, attacker);
    //}
}
