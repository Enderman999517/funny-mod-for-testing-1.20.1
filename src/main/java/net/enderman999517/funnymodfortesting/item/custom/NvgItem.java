package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTestingClient;
import net.enderman999517.funnymodfortesting.item.DebugCallback;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NvgItem extends Item implements Equipment {

    private int debugMode;
    private final List<> callbacks = new ArrayList<>();

    public void registerDebugCallback( callback) {
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
        if (entity.isPlayer() && FunnyModForTestingClient.NVG_TOGGLE.wasPressed() && entity.getArmorItems().equals(ModItems.NVG_GOGGLES)) {
            this.callbacks.get(debugMode).use(world, entity, slot, selected);
        }
    }
}
