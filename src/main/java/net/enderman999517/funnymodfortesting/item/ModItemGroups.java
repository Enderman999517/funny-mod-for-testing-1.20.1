package net.enderman999517.funnymodfortesting.item;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.ModBlocks;
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
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.funnymodfortesting"))
                    .icon(() -> new ItemStack(ModItems.OVERPOWERED)).entries((displayContext, entries) ->  {

                        //custom
                        entries.add(ModItems.OVERPOWERED);
                        entries.add(ModItems.AMOGH);

                        //items
                        entries.add(ModItems.AMOGH_ESSENCE);

                        //blocks
                        entries.add(ModBlocks.LTF_BLOCK);

                        ////armor
                        //entries.add(ModItems.LTF_HAIR);

                        //spawn eggs
                        entries.add(ModItems.AMOGH_SPAWN_EGG);

                    }).build());

    public static void registerItemGroups() {
        FunnyModForTesting.LOGGER.info("Registering Item Groups for " + FunnyModForTesting.MOD_ID);
    }

}
