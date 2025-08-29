package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StatusEffectStoringItem extends Item {
    public StatusEffectStoringItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf) {
        super(settings);
        this.damagesUser = damagesUser;
        this.damageTypeRegistryKey = damageTypeRegistryKey;
        this.damageAmount = damageAmount;
        this.clearable = clearable;
        this.useOnSelf = useOnSelf;
    }
    private final Collection<StatusEffectInstance> playerEffectsList = new ArrayList<>();
    private final Collection<StatusEffectInstance> itemEffectsList = new ArrayList<>();
    private final boolean damagesUser;
    private final RegistryKey<DamageType> damageTypeRegistryKey;
    private final float damageAmount;
    private final boolean clearable;
    private final boolean useOnSelf;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (clearable) {
            if (Screen.hasShiftDown()) {
                clearItemEffects();
            } else useItem(world, user);
        } else {
            if (Screen.hasShiftDown() && useOnSelf) {
                putEffectsOnTarget(user);
            } else {
                useItem(world, user);
            }
        }

        return TypedActionResult.success(itemStack, world.isClient);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        putEffectsOnTarget(target);
        clearItemEffects();
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PotionUtil.buildTooltip(itemEffectsList.stream().toList(), tooltip, 1f);
        super.appendTooltip(stack, world, tooltip, context);
    }

    private void clearPlayerEffects(PlayerEntity user){
        user.clearStatusEffects();
    }

    private void getPlayerEffects(PlayerEntity user){
        playerEffectsList.addAll(user.getStatusEffects());
    }

    private void putEffectsOnItem(){
        itemEffectsList.addAll(playerEffectsList);
        playerEffectsList.clear();
    }

    private void clearItemEffects(){
        itemEffectsList.clear();
    }

    private void useItem(World world, PlayerEntity user) {
        if (!world.isClient) {
            getPlayerEffects(user);
            clearPlayerEffects(user);
            putEffectsOnItem();
            if (damagesUser) {
                DamageSource damageSource = new DamageSource(
                        world.getRegistryManager()
                                .get(RegistryKeys.DAMAGE_TYPE)
                                .entryOf(damageTypeRegistryKey));
                user.damage(damageSource, damageAmount);
            }
        }
    }

    private void putEffectsOnTarget(LivingEntity target) {
        for (int i = 0; i < itemEffectsList.size(); i++) {
            target.addStatusEffect(itemEffectsList.stream().toList().get(i));
        }
    }
}
