package net.enderman999517.funnymodfortesting.item;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.item.custom.OverpoweredItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    //custom
    public static final Item OVERPOWERED = registerItem("overpowered",
            new OverpoweredItem(new FabricItemSettings()));


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
}
