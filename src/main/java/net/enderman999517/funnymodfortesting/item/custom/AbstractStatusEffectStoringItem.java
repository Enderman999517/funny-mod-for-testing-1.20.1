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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
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

public abstract class AbstractStatusEffectStoringItem extends Item {
    public AbstractStatusEffectStoringItem(Settings settings, boolean damagesUser, RegistryKey<DamageType> damageTypeRegistryKey, float damageAmount, boolean clearable, boolean useOnSelf, boolean useOnOthers) {
        super(settings);
        this.damagesUser = damagesUser;
        this.damageTypeRegistryKey = damageTypeRegistryKey;
        this.damageAmount = damageAmount;
        this.clearable = clearable;
        this.useOnSelf = useOnSelf;
        this.useOnOthers = useOnOthers;
    }

    private final boolean damagesUser;
    private final RegistryKey<DamageType> damageTypeRegistryKey;
    private final float damageAmount;
    private final boolean clearable;
    private final boolean useOnSelf;
    private final boolean useOnOthers;

    public static void writeEffectsToNbt(ItemStack stack, Collection<StatusEffectInstance> effects) {
        NbtList effectList = new NbtList();
        for (StatusEffectInstance effect : effects) {
            effectList.add(effect.writeNbt(new NbtCompound()));
            if (stack.hasNbt()) {
                effectList.add(stack.getNbt());
            }
        }
        //if (stack.hasNbt()) {
        //    if (stack.getNbt().contains("CustomEffects")) {
        //        FunnyModForTesting.LOGGER.info("elb: " + effectList);
        //        //FunnyModForTesting.LOGGER.info("gnbtt: " + effectList.getNbtType());
        //        effectList.add(stack.getNbt().get("CustomEffects"));
        //        FunnyModForTesting.LOGGER.info("s.gnbt: " + stack.getNbt().toString());
        //        FunnyModForTesting.LOGGER.info("ela: " + effectList);
        //        //clearEffectsFromNbt(stack);
        //        stack.getOrCreateNbt().put("CustomEffects", effectList);
        //    }
        //} else stack.getOrCreateNbt().put("CustomEffects", effectList);

        //if (stack.hasNbt()) {
        //    if (stack.getNbt().contains("CustomEffects")) {
        //        //for (StatusEffectInstance effect1 : effects) {
        //        //    effects.add(readEffectsFromNbt(stack));
        //        //}
//
//
        //        List<StatusEffectInstance> effects1 = readEffectsFromNbt(stack);
        //        effects.addAll(effects1);
        //    }
        //}

        stack.getOrCreateNbt().put("CustomEffects", effectList);
    }

    public static List<StatusEffectInstance> readEffectsFromNbt(ItemStack stack) {
        List<StatusEffectInstance> effects = new ArrayList<>();
        if (stack.hasNbt() && stack.getNbt().contains("CustomEffects")) {
            NbtList list = stack.getNbt().getList("CustomEffects", NbtElement.COMPOUND_TYPE);
            for (int i = 0; i < list.size(); i++) {
                effects.add(StatusEffectInstance.fromNbt(list.getCompound(i)));
            }
        }
        return effects;
    }

    public static void clearEffectsFromNbt(ItemStack stack) {
        if (stack.hasNbt()) {
            stack.getNbt().remove("CustomEffects");
        }
    }

    public static boolean isItemListEmpty(ItemStack stack) {
        var nbt = stack.getNbt();
        if (stack == null || !stack.hasNbt()) return true;
        if (nbt == null || !nbt.contains("CustomEffects")) return true;
        return nbt.getList("CustomEffects", 10).isEmpty();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            if (clearable) {
                if (Screen.hasShiftDown()) {
                    clearEffectsFromNbt(stack);
                } else {
                    if (!user.getStatusEffects().isEmpty()) {
                        useItem(world, user);
                    }
                }
            } else {
                if (Screen.hasShiftDown() && useOnSelf) {
                    for (StatusEffectInstance effect : readEffectsFromNbt(stack)) {
                        user.addStatusEffect(new StatusEffectInstance(effect));
                    }
                    clearEffectsFromNbt(stack);
                } else {
                    useItem(world, user);
                }
            }
        }
        return TypedActionResult.success(stack, world.isClient);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (useOnOthers) {
            for (StatusEffectInstance effect : readEffectsFromNbt(stack)) {
                target.addStatusEffect(new StatusEffectInstance(effect));
            }
            clearEffectsFromNbt(stack);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        List<StatusEffectInstance> effects = readEffectsFromNbt(stack);
        PotionUtil.buildTooltip(effects, tooltip, 1f);
        super.appendTooltip(stack, world, tooltip, context);
    }

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
        }
    }

    protected boolean hasItems() {
        return this.getDefaultStack().hasNbt();
    }
}
