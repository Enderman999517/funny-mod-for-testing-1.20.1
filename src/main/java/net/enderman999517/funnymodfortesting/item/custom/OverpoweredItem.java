package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OverpoweredItem extends Item {
    public OverpoweredItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.damage(attacker.getRecentDamageSource(),1000);

        return super.postHit(stack, target, attacker);
    }



    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
