package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.damage.ModDamageSources;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScytheItem extends Item {
    public ScytheItem(Settings settings) {
        super(settings);
    }
    private Collection<StatusEffectInstance> playerEffectsList = new ArrayList<>();
    private Collection<StatusEffectInstance> scytheEffectsList = new ArrayList<>();

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            getPlayerEffects(user);
            clearPlayerEffects(user);
            putEffectsOnScythe();
            //user.sendMessage(Text.literal("scytheEffectsList onUse: " + scytheEffectsList.toString()));
            DamageSource damageSource = new DamageSource(
                    world.getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageSources.SCYTHE_DAMAGE));
            user.damage(damageSource, 4);
        }

        return TypedActionResult.success(itemStack, world.isClient);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (int i = 0; i < scytheEffectsList.size(); i++) {
            target.addStatusEffect(scytheEffectsList.stream().toList().get(i));
        }
        //attacker.sendMessage(Text.literal("scytheEffectsList postHit: " + scytheEffectsList.toString()));
        clearScytheEffects();
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PotionUtil.buildTooltip(scytheEffectsList.stream().toList(), tooltip, 1f);
        super.appendTooltip(stack, world, tooltip, context);
    }

    private void clearPlayerEffects(PlayerEntity user){
        user.clearStatusEffects();
    }

    private void getPlayerEffects(PlayerEntity user){
        playerEffectsList.addAll(user.getStatusEffects());
        //user.sendMessage(Text.literal("playerEffectsList: " + playerEffectsList.toString()));
    }

    private void putEffectsOnScythe(){
        scytheEffectsList.addAll(playerEffectsList);
        playerEffectsList.clear();
    }

    private void clearScytheEffects(){
        scytheEffectsList.clear();
    }
}
