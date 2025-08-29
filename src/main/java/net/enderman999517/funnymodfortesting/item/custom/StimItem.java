package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;

public class StimItem extends AbstractStatusEffectStoringItem{
    public StimItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf, boolean useOnOthers) {
        super(settings, damagesUser, damageTypeRegistryKey, damageAmount, clearable, useOnSelf, useOnOthers);
    }
}
