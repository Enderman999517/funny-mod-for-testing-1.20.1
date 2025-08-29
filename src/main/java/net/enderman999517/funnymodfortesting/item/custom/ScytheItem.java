package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryKey;

import java.util.Collection;

public class ScytheItem extends AbstractStatusEffectStoringItem {
    public boolean isItemListEmpty = getItemList().isEmpty();

    public ScytheItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf, boolean useOnOthers) {
        super(settings, damagesUser, damageTypeRegistryKey, damageAmount, clearable, useOnSelf, useOnOthers);
    }

    public boolean isItemListEmpty() {
        return this.getItemList().isEmpty();
    }

    public Collection<StatusEffectInstance> getItemList1() {
        return this.getItemList();
    }
}
