package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;

public class ScytheItem extends AbstractStatusEffectStoringItem {
    public static boolean isItemListEmpty(ItemStack stack) {
        var nbt = stack.getNbt();
        if (stack == null || !stack.hasNbt()) return true;
        if (nbt == null || !nbt.contains("CustomEffects")) return true;
        return nbt.getList("CustomEffects", 10).isEmpty();
    }

    public ScytheItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf, boolean useOnOthers) {
        super(settings, damagesUser, damageTypeRegistryKey, damageAmount, clearable, useOnSelf, useOnOthers);
    }
}
