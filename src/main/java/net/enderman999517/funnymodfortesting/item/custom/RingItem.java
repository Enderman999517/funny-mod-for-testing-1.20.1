package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RingItem extends Item {
    private int shaderMode;
    private final List<RingCallback> callbacks = new ArrayList<>();
    public RingItem(Settings settings) {
        super(settings);
    }

    public void registerRingCallback(RingCallback callback) {
        this.callbacks.add(callback);
    }
}
