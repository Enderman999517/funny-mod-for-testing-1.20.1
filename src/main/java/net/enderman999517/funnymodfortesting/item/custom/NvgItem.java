package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.FunnyModForTestingClient;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NvgItem extends Item implements Equipment {

    private int debugMode;
    private final List<DebugCallbackNvg> callbacks = new ArrayList<>();

    public void registerDebugCallback(DebugCallbackNvg callback) {
        this.callbacks.add(callback);
    }

    public NvgItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return this.equipAndSwap(this, world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltips.funnymodfortesting.nvg", FunnyModForTestingClient.NVG_TOGGLE.getBoundKeyLocalizedText()));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //Iterable<ItemStack> itemStacks = new Iterable<ItemStack>() {
        //    @Override
        //    public @NotNull Iterator<ItemStack> iterator() {
        //        return new Iterator<ItemStack>() {
        //            @Override
        //            public boolean hasNext() {
        //                return true;
        //            }
//
        //            @Override
        //            public ItemStack next() {
        //                return new ItemStack(ModItems.NVG_GOGGLES);
        //            }
        //        };
        //    }
        //};
        //if (entity.isPlayer() && FunnyModForTestingClient.NVG_TOGGLE.wasPressed()) {
        //    FunnyModForTesting.LOGGER.info("test1");
        //    if(entity.getArmorItems().equals(itemStacks)) {
        //        FunnyModForTesting.LOGGER.info("test");
        //        this.callbacks.get(debugMode).inventoryTick(stack, world, entity, slot, selected);
        //    }
        //}
        if (entity.isPlayer() && FunnyModForTestingClient.NVG_TOGGLE.wasPressed()) {
            FunnyModForTesting.LOGGER.info("test1");
            if (Objects.requireNonNull(entity.getWorld().getClosestPlayer(entity, 2)).getInventory().main.stream().anyMatch(stack1 -> stack1.isOf(ModItems.NVG_GOGGLES))) {
                FunnyModForTesting.LOGGER.info("test");
                this.callbacks.get(debugMode).inventoryTick(stack, world, entity, slot, selected);
            }
        }
    }
}
