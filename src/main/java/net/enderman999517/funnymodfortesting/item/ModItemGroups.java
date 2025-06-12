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
                        entries.add(ModItems.FLASHBANG);
                        entries.add(ModItems.NVG_GOGGLES);

                        //items
                        entries.add(ModItems.AMOGH_ESSENCE);
                        entries.add(ModItems.HAPPY_MELON_SLICE);

                        //blocks
                        entries.add(ModBlocks.LTF_BLOCK);
                        entries.add(ModBlocks.BRAINROTIFIER);
                        entries.add(ModBlocks.SHOWCASE_BLOCK_W);
                        entries.add(ModBlocks.SHOWCASE_BLOCK_B);
                        entries.add(ModBlocks.HAPPY_MELON_BLOCK);
                        //entries.add(ModBlocks.HAPPY_MELON_BLOCK_2);
                        //entries.add(ModBlocks.HAPPY_MELON_BLOCK_3);

                        ////armor
                        //entries.add(ModItems.LTF_HAIR);

                        //spawn eggs
                        entries.add(ModItems.AMOGH_SPAWN_EGG);

                    }).build());

    public static void registerItemGroups() {
        FunnyModForTesting.LOGGER.info("Registering Item Groups for " + FunnyModForTesting.MOD_ID);
    }

}
