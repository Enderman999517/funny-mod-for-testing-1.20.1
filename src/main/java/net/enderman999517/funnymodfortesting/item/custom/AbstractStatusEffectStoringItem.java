package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.enchantment.ModEnchantments;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractStatusEffectStoringItem extends SwordItem {
    public AbstractStatusEffectStoringItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf, boolean hitOnOthers, int effectSwapping, boolean stacksAmp, boolean useOnOthers, int attackDamage, float attackSpeed, ToolMaterial toolMaterial) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.damagesUser = damagesUser;
        this.damageTypeRegistryKey = damageTypeRegistryKey;
        this.damageAmount = damageAmount;
        this.clearable = clearable;
        this.useOnSelf = useOnSelf;
        this.hitOnOthers = hitOnOthers;
        this.effectSwapping = effectSwapping;
        this.stacksAmp = stacksAmp;
        this.useOnOthers = useOnOthers;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
    }

    private final boolean damagesUser;
    private final RegistryKey<DamageType> damageTypeRegistryKey;
    private final float damageAmount;
    private final boolean clearable;
    private final boolean useOnSelf;
    private final boolean hitOnOthers;
    private final int effectSwapping;
    private boolean stacksAmp;
    private final boolean useOnOthers;
    private final int attackDamage;
    private final float attackSpeed;

    public void writeEffectsToNbt(ItemStack stack, Collection<StatusEffectInstance> effects) {
        List<StatusEffectInstance> existingEffects = readEffectsFromNbt(stack);

        if (EnchantmentHelper.getLevel(ModEnchantments.CONCENTRATION, stack) != 0) {
            stacksAmp = true;
        }
        testBehavior(effects, existingEffects, effectSwapping, stacksAmp, stack);

        NbtList effectList = new NbtList();
        for (StatusEffectInstance effect : existingEffects) {
            effectList.add(effect.writeNbt(new NbtCompound()));
        }

        stack.getOrCreateNbt().put("Effects", effectList);
    }

    public static List<StatusEffectInstance> readEffectsFromNbt(ItemStack stack) {
        List<StatusEffectInstance> effects = new ArrayList<>();
        if (stack.hasNbt() && stack.getNbt().contains("Effects")) {
            NbtList list = stack.getNbt().getList("Effects", NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < list.size(); i++) {
                effects.add(StatusEffectInstance.fromNbt(list.getCompound(i)));
            }
        }
        return effects;
    }

    public static void clearEffectsFromNbt(ItemStack stack) {
        if (stack.hasNbt()) {
            stack.getNbt().remove("Effects");
        }
    }

    public static boolean isItemListEmpty(ItemStack stack) {
        var nbt = stack.getNbt();
        if (stack == null || !stack.hasNbt()) return true;
        if (nbt == null || !nbt.contains("Effects")) return true;
        return nbt.getList("Effects", 10).isEmpty();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        boolean returnValue = true;
        ItemStack stack = user.getStackInHand(hand);
        if (clearable) {
            if (Screen.hasShiftDown()) {
                if (!stack.getNbt().getList("Effects", 10).isEmpty()) {
                    if (!world.isClient) {
                        clearEffectsFromNbt(stack);
                    }
                } else returnValue = false;
            } else {
                if (!user.getStatusEffects().isEmpty()) {
                    useItem(world, user);
                } else returnValue = false;
            }
        } else {
            if (Screen.hasShiftDown() && useOnSelf) {
                if (!stack.getNbt().getList("Effects", 10).isEmpty()) {
                    for (StatusEffectInstance effect : readEffectsFromNbt(stack)) {
                        if (!world.isClient) {
                            user.addStatusEffect(new StatusEffectInstance(effect));
                        }
                    }
                    if (!world.isClient) {
                        clearEffectsFromNbt(stack);
                    }
                } else returnValue = false;
            } else if (!user.getStatusEffects().isEmpty() && useOnSelf) {
                useItem(world, user);
            } else returnValue = false;
        }
        if (returnValue) {
            if (!world.isClient) {
                user.incrementStat(Stats.USED.getOrCreateStat(this));
            }
            return TypedActionResult.success(stack, world.isClient);
        } else return TypedActionResult.fail(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (hitOnOthers) {
            for (StatusEffectInstance effect : readEffectsFromNbt(stack)) {
                target.addStatusEffect(new StatusEffectInstance(effect));
            }
            clearEffectsFromNbt(stack);
            //attacker.getWorld().playSound(null, attacker.getBlockPos(), SoundEvents.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.BLOCKS);
        }
        return super.postHit(stack, target, attacker);
    }

    //boolean needsUpdate = false;
    //Collection<StatusEffectInstance> effectInstances;
    //@Override
    //public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
    //    if (useOnOthers) {
    //        effectInstances = entity.getStatusEffects();
    //        if (!user.getWorld().isClient && entity != user) {
    //            writeEffectsToNbt(stack, effectInstances);
    //            //FunnyModForTesting.LOGGER.error("effects: {}", effectInstances);
    //            FunnyModForTesting.LOGGER.error("nbt: {}", stack.getNbt().getList("Effects", 10));
    //            entity.clearStatusEffects();
    //            needsUpdate = true;
    //        }
    //        return ActionResult.SUCCESS;
    //    } else return ActionResult.FAIL;
    //}

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        List<StatusEffectInstance> effects = readEffectsFromNbt(stack);
        PotionUtil.buildTooltip(effects, tooltip, 1f);
        super.appendTooltip(stack, world, tooltip, context);
    }

    //@Override
    //public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
    //    if (!world.isClient) {
    //        if (needsUpdate) {
    //            writeEffectsToNbt(stack, effectInstances);
    //            needsUpdate = false;
    //        }
    //    }
    //    super.inventoryTick(stack, world, entity, slot, selected);
    //}

    private void useItem(World world, PlayerEntity user) {
        ItemStack stack = user.getMainHandStack();

        if (!world.isClient) {
            Collection<StatusEffectInstance> playerEffects = user.getStatusEffects();
            writeEffectsToNbt(stack, playerEffects);
            user.clearStatusEffects();

            if (damagesUser) {
                DamageSource damageSource = new DamageSource(
                        world.getRegistryManager()
                                .get(RegistryKeys.DAMAGE_TYPE)
                                .entryOf(damageTypeRegistryKey));
                user.damage(damageSource, damageAmount);
            }
            stack.damage(1, user,
                    player -> player.sendToolBreakStatus(player.getActiveHand()));
            user.getWorld().playSound(null, user.getBlockPos(), SoundEvents.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.BLOCKS);
        }
    }

    private static boolean noSwapping(List<StatusEffectInstance> existingEffects, Collection<StatusEffectInstance> effects, boolean found, StatusEffectInstance newEffect) {
        for (StatusEffectInstance existing : existingEffects) {
            if (existing.getEffectType() == newEffect.getEffectType()) {
                found = true;
                break;
            }
        }
        return found;
    }

    private static boolean swapping(List<StatusEffectInstance> existingEffects, Collection<StatusEffectInstance> effects, boolean found, StatusEffectInstance newEffect) {
        for (int i = 0; i < existingEffects.size(); i++) {
            StatusEffectInstance existing = existingEffects.get(i);
            if (existing.getEffectType() == newEffect.getEffectType()) {
                if ((newEffect.getDuration() > existing.getDuration() && newEffect.getAmplifier() >= existing.getAmplifier()) ||
                        (newEffect.getAmplifier() > existing.getAmplifier() && newEffect.getDuration() >= existing.getDuration())) {
                    existingEffects.set(i, newEffect);
                }
                found = true;
                break;
            }
        }
        return found;
    }

    private static boolean addingAmp(List<StatusEffectInstance> existingEffects, Collection<StatusEffectInstance> effects, boolean found, StatusEffectInstance newEffect, ItemStack stack) {
        for (int i = 0; i < existingEffects.size(); i++) {
            StatusEffectInstance existing = existingEffects.get(i);
            if (existing.getEffectType() == newEffect.getEffectType()) {
                // check to add
                if (((existing.getAmplifier() + 1) + (newEffect.getAmplifier() + 1) <= 3) && (EnchantmentHelper.getLevel(ModEnchantments.CONCENTRATION, stack) >= 1)) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(existing.getEffectType(), Math.max(existing.getDuration(), newEffect.getDuration()),
                            existing.getAmplifier() + newEffect.getAmplifier() + 1);
                    existingEffects.set(i, effectInstance);
                    found = true;
                    break;
                // revert to max level
                } else if (((existing.getAmplifier() + 1) + (newEffect.getAmplifier() + 1) > 3) && (EnchantmentHelper.getLevel(ModEnchantments.CONCENTRATION, stack) == 1)) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(existing.getEffectType(), Math.max(existing.getDuration(), newEffect.getDuration()),
                            2);
                    existingEffects.set(i, effectInstance);
                    found = true;
                    break;
                // check to add
                } else if (((existing.getAmplifier() + 1) + (newEffect.getAmplifier() + 1) <= 4) && (EnchantmentHelper.getLevel(ModEnchantments.CONCENTRATION, stack) >= 2)) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(existing.getEffectType(), Math.max(existing.getDuration(), newEffect.getDuration()),
                            existing.getAmplifier() + newEffect.getAmplifier() + 1);
                    existingEffects.set(i, effectInstance);
                    found = true;
                    break;
                // revert to max level
                } else if (((existing.getAmplifier() + 1) + (newEffect.getAmplifier() + 1) > 4) && (EnchantmentHelper.getLevel(ModEnchantments.CONCENTRATION, stack) == 2)) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(existing.getEffectType(), Math.max(existing.getDuration(), newEffect.getDuration()),
                            3);
                    existingEffects.set(i, effectInstance);
                    found = true;
                    break;
                // check to add
                } else if (((existing.getAmplifier() + 1) + (newEffect.getAmplifier() + 1) <= 5) && (EnchantmentHelper.getLevel(ModEnchantments.CONCENTRATION, stack) >= 3)) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(existing.getEffectType(), Math.max(existing.getDuration(), newEffect.getDuration()),
                            existing.getAmplifier() + newEffect.getAmplifier() + 1);
                    existingEffects.set(i, effectInstance);
                    found = true;
                    break;
                // revert to max level
                } else if (((existing.getAmplifier() + 1) + (newEffect.getAmplifier() + 1) > 5) && (EnchantmentHelper.getLevel(ModEnchantments.CONCENTRATION, stack) == 3)) {
                    StatusEffectInstance effectInstance = new StatusEffectInstance(existing.getEffectType(), Math.max(existing.getDuration(), newEffect.getDuration()),
                            4);
                    existingEffects.set(i, effectInstance);
                    found = true;
                    break;
                } else found = true;
            } else found = false;
        }
        return found;
    }

    private static void testBehavior(Collection<StatusEffectInstance> effects, List<StatusEffectInstance> existingEffects, int effectSwapping, boolean stacks, ItemStack stack) {
        for (StatusEffectInstance newEffect : effects) {
            boolean found = false;
            if (stacks) {
                found = addingAmp(existingEffects, effects, false, newEffect, stack);
            } else  {
                switch (effectSwapping) {
                    case 0 -> found = noSwapping(existingEffects, effects, found, newEffect);
                    case 1 -> found = swapping(existingEffects, effects, found, newEffect);
                    default -> FunnyModForTesting.LOGGER.error("Invalid effect swapping state");
                }
            }
            if (!found) {
                existingEffects.add(newEffect);
            }
        }
    }
}
