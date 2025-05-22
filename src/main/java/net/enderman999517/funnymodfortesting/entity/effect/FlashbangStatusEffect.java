package net.enderman999517.funnymodfortesting.entity.effect;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FlashbangStatusEffect extends StatusEffect {
    private int debugMode;
    private final List<DebugCallback> callbacks = new ArrayList<>();

    public void registerDebugCallback(DebugCallback callback) {
        this.callbacks.add(callback);
    }

    protected FlashbangStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity.getWorld().isClient) {
            //this.callbacks.get(debugMode).onApplied(entity, attributes, amplifier);


            RenderSystem.setShaderFogColor(1,1,1,1);
            BackgroundRenderer.setFogBlack();
            BackgroundRenderer.applyFog(new Camera(), BackgroundRenderer.FogType.FOG_SKY, 2, true, 1); //TODO
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity.getWorld().isClient) {
            BackgroundRenderer.clearFog();
            //this.callbacks.get(debugMode).onApplied(entity, attributes, amplifier);
        }
    }
}
