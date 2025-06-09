package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTestingClient;
import net.enderman999517.funnymodfortesting.item.ModItems;
import net.enderman999517.funnymodfortesting.sound.ModSounds;
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
import java.util.Objects;

public class NvgItem extends Item implements Equipment {

    private int toggle = 0;
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
        if (world.isClient) {
            if (entity.isPlayer() && FunnyModForTestingClient.NVG_TOGGLE.wasPressed()) {
                // no clue why you need to invert it but it works so i dont really care
                if (!Objects.requireNonNull(entity.getWorld().getClosestPlayer(entity, 2)).getInventory().main.stream().anyMatch(stack1 -> stack1.isOf(ModItems.NVG_GOGGLES))) {
                    this.callbacks.get(debugMode).inventoryTick(stack, world, entity, slot, selected);
                    if (toggle % 2 == 0) {
                        entity.playSound(ModSounds.NVG_TURN_ON, 1f, 1f);
                        toggle++;
                    } else {
                        entity.playSound(ModSounds.NVG_TURN_OFF, 1f, 1f);
                        toggle++;
                    }
                }
            }
        }
    }
}
