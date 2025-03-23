package net.enderman999517.funnymodfortesting.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LtfBlock extends Block {
    public LtfBlock(Settings settings) {
        super(settings);
    }

    //private static float randomPitch;

    //@Override
    //public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
    //    randomPitch = ((float) net.minecraft.util.math.random.Random.createLocal().nextBetweenExclusive(5, 20) / 10);
    //    super.onPlaced(world, pos, state, placer, itemStack);
    //}
//
    //public static float getRandomPitch() {
    //    return randomPitch;
    //}

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        //String randomPitchString = Float.toString(randomPitch);
        //player.sendMessage(Text.literal("randomPitch: " + randomPitchString));
        //world.playSound(player, pos, ModSounds.LTF_INTERACT, SoundCategory.BLOCKS, 1f, 1f);
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.literal("Loooooooooooooooooooooowwww Taperrrrrrrrrrrr Faadeeeeeeeeee").formatted(Formatting.BOLD));
        super.appendTooltip(stack, world, tooltip, options);
    }
}
