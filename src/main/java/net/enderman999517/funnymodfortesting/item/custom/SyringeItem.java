package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;

public class SyringeItem extends AbstractStatusEffectStoringItem{
    public SyringeItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf, boolean hitOnOthers, int effectSwapping, boolean stacksAmp, boolean useOnOthers, int attackDamage, float attackSpeed, ToolMaterial toolMaterial) {
        super(settings, damagesUser, damageTypeRegistryKey, damageAmount, clearable, useOnSelf, hitOnOthers, effectSwapping, stacksAmp, useOnOthers, attackDamage, attackSpeed, toolMaterial);
    }
}
