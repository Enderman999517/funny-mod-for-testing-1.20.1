package net.enderman999517.funnymodfortesting.item;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.block.ModBlocks;
import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.enderman999517.funnymodfortesting.entity.ModEntities;
import net.enderman999517.funnymodfortesting.entity.effect.ModStatusEffects;
import net.enderman999517.funnymodfortesting.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // for shader stuff triggered by items, add as below and add to registerModItems()
    public static final DebugItem DEBUG_ITEM = new DebugItem(new Item.Settings());
    public static final BlockItem DEBUG_BLOCK = new BlockItem(ModBlocks.DEBUG_BLOCK, new Item.Settings());
    public static final NvgItem NVG_GOGGLES = new NvgItem(new Item.Settings());


    //custom
    public static final Item OVERPOWERED = registerItem("overpowered",
            new OverpoweredItem(new FabricItemSettings()));
    public static final Item AMOGH = registerItem("amogh",
            new AmoghItem(new FabricItemSettings()));
    public static final Item FLASHBANG = registerItem("flashbang",
            new FlashbangItem(new FabricItemSettings()));
    public static final Item TORCH_GUN = registerItem("torch_gun",
            new TorchGunItem(new FabricItemSettings()));
    public static final Item SCYTHE = registerItem("scythe",
            new ScytheItem(new FabricItemSettings(), true, ModDamageSources.SCYTHE_DAMAGE,4,true, false, true));
    public static final Item STIM = registerItem("stim",
            new StimItem(new FabricItemSettings(), true, ModDamageSources.STIM_DAMAGE,1,false, true, true));

    //items
    public static final Item AMOGH_ESSENCE = registerItem("amogh_essence",
            new Item(new FabricItemSettings()));
    public static final Item HAPPY_MELON_SLICE = registerItem("happy_melon_slice",
            new Item(new FabricItemSettings().food(new FoodComponent.Builder().alwaysEdible().hunger(2).statusEffect(new StatusEffectInstance(ModStatusEffects.BRAINROT, 1), 1).build())));

    //armor + hats
    //public static final Item NVG_GOGGLES = registerItem("nvg_goggles",
    //        new NvgItem(new FabricItemSettings()));
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
        Registry.register(Registries.ITEM, new Identifier(FunnyModForTesting.MOD_ID, "debug_item"), DEBUG_ITEM);
        Registry.register(Registries.ITEM, new Identifier(FunnyModForTesting.MOD_ID, "nvg_goggles"), NVG_GOGGLES);
        Registry.register(Registries.ITEM, Registries.BLOCK.getId(ModBlocks.DEBUG_BLOCK), DEBUG_BLOCK);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggItemGroup);
    }
}
