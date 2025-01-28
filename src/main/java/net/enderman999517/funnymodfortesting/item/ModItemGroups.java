package net.enderman999517.funnymodfortesting.item;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup FUNNY_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(FunnyModForTesting.MOD_ID, "funny"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.funny"))
                    .icon(() -> new ItemStack(ModItems.OVERPOWERED)).entries((displayContext, entries) ->  {


                    }).build());

    public static void registerItemGroups() {
        FunnyModForTesting.LOGGER.info("Registering Item Groups for " + FunnyModForTesting.MOD_ID);
    }

}
