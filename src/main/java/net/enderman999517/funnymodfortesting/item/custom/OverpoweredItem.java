package net.enderman999517.funnymodfortesting.item.custom;

import net.enderman999517.funnymodfortesting.entity.custom.ExplosiveProjectileEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OverpoweredItem extends Item {
    public OverpoweredItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.kill();
        //target.damage(new DamageSource(new RegistryEntry<DamageType>() {
        //    //no clue why the overides are needed
        //    @Override
        //    public DamageType value() {
        //        return null;
        //    }
//
        //    @Override
        //    public boolean hasKeyAndValue() {
        //        return false;
        //    }
//
        //    @Override
        //    public boolean matchesId(Identifier id) {
        //        return false;
        //    }
//
        //    @Override
        //    public boolean matchesKey(RegistryKey<DamageType> key) {
        //        return false;
        //    }
//
        //    @Override
        //    public boolean matches(Predicate<RegistryKey<DamageType>> predicate) {
        //        return false;
        //    }
//
        //    @Override
        //    public boolean isIn(TagKey<DamageType> tag) {
        //        return false;
        //    }
//
        //    @Override
        //    public Stream<TagKey<DamageType>> streamTags() {
        //        return Stream.empty();
        //    }
//
        //    @Override
        //    public Either<RegistryKey<DamageType>, DamageType> getKeyOrValue() {
        //        return null;
        //    }
//
        //    @Override
        //    public Optional<RegistryKey<DamageType>> getKey() {
        //        return Optional.empty();
        //    }
//
        //    @Override
        //    public Type getType() {
        //        return null;
        //    }
//
        //    @Override
        //    public boolean ownerEquals(RegistryEntryOwner<DamageType> owner) {
        //        return true;
        //    }
        //}), 1000);

        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ITEM_CROSSBOW_SHOOT,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );


        if (!world.isClient) {
            ExplosiveProjectileEntity explosiveProjectileEntity = new ExplosiveProjectileEntity(user, world);
            explosiveProjectileEntity.setItem(itemStack);
            explosiveProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 10F, 0.0F);
            explosiveProjectileEntity.setGlowing(true);
            world.spawnEntity(explosiveProjectileEntity);
        }

        //user.incrementStat(Stats.USED.getOrCreateStat(this));
        //if (!user.getAbilities().creativeMode) {
        //    itemStack.decrement(1);
        //}

        return TypedActionResult.success(itemStack, world.isClient);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.discard();
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltips.funnymodfortesting.overpowered.shift.l1").formatted(Formatting.AQUA));
            tooltip.add(Text.translatable("tooltips.funnymodfortesting.overpowered.shift.l2").formatted(Formatting.AQUA));
            tooltip.add(Text.translatable("tooltips.funnymodfortesting.overpowered.shift.l3").formatted(Formatting.AQUA));
        } else tooltip.add(Text.translatable("tooltips.funnymodfortesting.overpowered").formatted(Formatting.DARK_PURPLE));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
