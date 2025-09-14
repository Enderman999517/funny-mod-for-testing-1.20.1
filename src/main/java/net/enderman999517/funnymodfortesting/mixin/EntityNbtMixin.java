package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.networking.FunnyModForTestingSync;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityNbtMixin implements ModEntityData {
    private boolean hidden = false;

    @Inject(method = "writeNbt(Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/nbt/NbtCompound;", at = @At("RETURN"))
    private void writeModData(NbtCompound nbt, CallbackInfoReturnable<CallbackInfo> cir) {
        nbt.putBoolean("hidden", hidden);
    }

    @Inject(method = "readNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("RETURN"))
    private void readModData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("hidden")) {
            hidden = nbt.getBoolean("hidden");
        }
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
        Entity entity = (Entity)(Object)this;
        if (!entity.getWorld().isClient) {
            FunnyModForTestingSync.syncHiddenFlag(entity, hidden);
        }
    }
}
