package net.enderman999517.funnymodfortesting.item;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.item.custom.AmoghItem;
import net.enderman999517.funnymodfortesting.item.custom.DebugItem;
import net.enderman999517.funnymodfortesting.item.custom.OverpoweredItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.enderman999517.funnymodfortesting.block.ModBlocks.DEBUG_BLOCK;

public class ModItems {

    public static final DebugItem DEBUG_ITEM = new DebugItem(new Item.Settings());
    public static final BlockItem DEBUG_BLOCK = new BlockItem(ModBlocks.DEBUG_BLOCK, new Item.Settings());


    //custom
    public static final Item OVERPOWERED = registerItem("overpowered",
            new OverpoweredItem(new FabricItemSettings()));
    public static final Item AMOGH = registerItem("amogh",
            new AmoghItem(new FabricItemSettings()));

    //items
    public static final Item AMOGH_ESSENCE = registerItem("amogh_essence",
            new Item(new FabricItemSettings()));

    ////armor + hats
    //public static final Item LTF_HAIR = registerItem("ltf_hair",
    //        new ArmorItem(ModArmorMaterials.HAIR, ArmorItem.Type.HELMET, new FabricItemSettings()));


    //spawn eggs
    public static final Item AMOGH_SPAWN_EGG = registerItem("amogh_spawn_egg",
            new SpawnEggItem(ModEntities.AMOGH, 0xf86518, 0x3b3d0f, new FabricItemSettings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(FunnyModForTesting.MOD_ID, name), item);
    }

    private static void addItemsToSpawnEggItemGroup(FabricItemGroupEntries entries) {
        entries.add(AMOGH_SPAWN_EGG);
    }

    public static void registerModItems() {
        FunnyModForTesting.LOGGER.info("Registering Mod Items for " + FunnyModForTesting.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggItemGroup);
    }

    public static void init() {
        Registry.register(Registries.ITEM, new Identifier(FunnyModForTesting.MOD_ID, "debug_item"), DEBUG_ITEM);
        Registry.register(Registries.ITEM, Registries.BLOCK.getId(ModBlocks.DEBUG_BLOCK), DEBUG_BLOCK);
    }
}
