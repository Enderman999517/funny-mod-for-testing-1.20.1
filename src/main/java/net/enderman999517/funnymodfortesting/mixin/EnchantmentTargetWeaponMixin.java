package net.enderman999517.funnymodfortesting.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net/minecraft/enchantment/EnchantmentTarget$11")
public class EnchantmentTargetWeaponMixin {
    @ModifyReturnValue(method = "isAcceptableItem", at = @At("TAIL"))
    private boolean acceptScytheItemAsWeapon(boolean original, Item item) {
        return original || item == ModItems.SCYTHE;
    }
}
