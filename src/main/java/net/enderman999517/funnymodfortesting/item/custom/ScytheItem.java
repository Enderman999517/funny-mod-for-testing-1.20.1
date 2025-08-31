package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;

public class ScytheItem extends AbstractStatusEffectStoringItem {

    public ScytheItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf, boolean useOnOthers) {
        super(settings, damagesUser, damageTypeRegistryKey, damageAmount, clearable, useOnSelf, useOnOthers);
    }
}
