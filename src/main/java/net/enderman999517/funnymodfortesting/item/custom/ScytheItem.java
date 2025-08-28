package net.enderman999517.funnymodfortesting.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;

public class ScytheItem extends Item {
    public ScytheItem(Settings settings) {
        super(settings);
    }
    private Collection<StatusEffectInstance> playerEffectsList = new ArrayList<>();
    private Collection<StatusEffectInstance> scytheEffectsList = new ArrayList<>();

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        getPlayerEffects(user);
        clearPlayerEffects(user);
        putEffectsOnScythe();
        //user.sendMessage(Text.literal("scythe1: " + scytheEffectsList.toString()));

        return TypedActionResult.success(itemStack, world.isClient);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ArrayList<StatusEffectInstance> testList = new ArrayList<>();
        for (int i = scytheEffectsList.size(); i >=0; i--) {
            if (i>0) {
                target.addStatusEffect(scytheEffectsList.stream().toList().get(i - 1));
                testList.add(scytheEffectsList.stream().toList().get(i));
            }
        }
        clearScytheEffects();

        attacker.sendMessage(Text.literal("scythe: " + testList.toString()));
        testList.clear();
        return super.postHit(stack, target, attacker);
    }

    private void clearPlayerEffects(PlayerEntity user){
        user.clearStatusEffects();
    }

    private void getPlayerEffects(PlayerEntity user){
        playerEffectsList.addAll(user.getStatusEffects());
        user.sendMessage(Text.literal("player: " + playerEffectsList.toString()));
    }

    private void putEffectsOnScythe(){
        scytheEffectsList.addAll(playerEffectsList);
        playerEffectsList.clear();
    }

    private void clearScytheEffects(){
        scytheEffectsList.clear();
    }
}
