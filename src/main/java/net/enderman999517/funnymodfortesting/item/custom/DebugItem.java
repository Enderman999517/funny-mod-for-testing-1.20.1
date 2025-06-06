package net.enderman999517.funnymodfortesting.item.custom;


import net.enderman999517.funnymodfortesting.item.DebugCallbackUse;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DebugItem extends Item {
    private int debugMode;
    private final List<DebugCallbackUse> callbacks = new ArrayList<>();

    public DebugItem(Settings settings) {
        super(settings);
    }

    public void registerDebugCallback(DebugCallbackUse callback) {
        this.callbacks.add(callback);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (world.isClient) {
            this.callbacks.get(debugMode).use(world, player, hand);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }

    @Override
    public boolean hasGlint(ItemStack itemStack_1) {
        return true;
    }
}
