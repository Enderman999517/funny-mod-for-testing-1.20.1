package net.enderman999517.funnymodfortesting.mixin;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.ModEntityData;
import net.enderman999517.funnymodfortesting.networking.FunnyModForTestingNetworking;
import net.enderman999517.funnymodfortesting.networking.FunnyModForTestingSync;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityClientDespawnMixin implements ModEntityData {
    private boolean hidden = false;

    //@Inject(method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("RETURN"))
    @ModifyVariable(
            method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    private NbtCompound writeModData(NbtCompound nbt) {
        nbt.putBoolean("hidden", hidden);
        return nbt;
    }

    //@Inject(method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("RETURN"))
    @Redirect(
            method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/NbtCompound;putBoolean(Ljava/lang/String;Z)V"
            )
    )
    private void readModData(NbtCompound nbt, String key, boolean value) {
        if (key.equals("hidden")) {
            nbt.putBoolean(key, hidden);
        } else {
            nbt.putBoolean(key, value);
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
            FunnyModForTesting.LOGGER.info("test1");
        }
    }
}
