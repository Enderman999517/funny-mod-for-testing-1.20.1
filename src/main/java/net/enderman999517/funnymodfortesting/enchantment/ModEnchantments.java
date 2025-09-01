package net.enderman999517.funnymodfortesting.enchantment;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.enchantment.custom.ConcentrationEnchantment;
import net.enderman999517.funnymodfortesting.enchantment.custom.TestEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {

   public static Enchantment TEST = registerEnchantment("test",
           new TestEnchantment());
   public static Enchantment CONCENTRATION = registerEnchantment("concentration",
           new ConcentrationEnchantment());

    private static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(FunnyModForTesting.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        FunnyModForTesting.LOGGER.info("Registering Mod Enchantments for " + FunnyModForTesting.MOD_ID);
    }
}
