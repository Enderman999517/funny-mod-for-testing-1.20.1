package net.enderman999517.funnymodfortesting.block.entity;

import net.enderman999517.funnymodfortesting.FunnyModForTesting;
import net.enderman999517.funnymodfortesting.recipe.CompactingRecipe;
import net.enderman999517.funnymodfortesting.screen.CompactingScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CompactorBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 128;
    private ItemStack currentOutput = null;

    public CompactorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COMPACTOR_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CompactorBlockEntity.this.progress;
                    case 1 -> CompactorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CompactorBlockEntity.this.progress = value;
                    case 1 -> CompactorBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public ItemStack getCurrentOutput() {
        return currentOutput;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.funnymodfortesting.compactor");
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("compactor.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("compactor.progress");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CompactingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }


    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        if (this.getCurrentOutput() == null) {
                    if (getStack(INPUT_SLOT).getCount() >= 1) {
                        //FunnyModForTesting.LOGGER.info("test");
                        //gets called
                        Optional<CompactingRecipe> recipe = getCurrentRecipe();
                        ItemStack result = recipe.get().getOutput(null);
                        currentOutput = result;
                    }
        }

        //i think currentOutput isnt being updated
        //if not null, if hasnt finished, only craft if same
        if (currentOutput != null) {
            Optional<CompactingRecipe> recipe = getCurrentRecipe();
            if (recipe.isPresent()) {
                if (!hasCraftingFinished()) {
                    ItemStack result = recipe.get().getOutput(null);
                    //FunnyModForTesting.LOGGER.info("currentOutput is " + currentOutput);
                    //FunnyModForTesting.LOGGER.info("result is " + result);
                    //currentOutput is the same as result and im not smart enough to work out why the if statement doesnt work
                    if (currentOutput == result) {
                        FunnyModForTesting.LOGGER.info("test");
                        if(isOutputSlotEmptyOrReceivable()) {
                            // if has item in input, decrement input, increment normalised progress bar
                            // if progress bar full, craft


                            if(this.hasRecipe()) {
                                this.increaseCraftProgress();
                                this.removeStack(INPUT_SLOT, 1);
                                markDirty(world, pos, state);


                                if(hasCraftingFinished()) {
                                    this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().getOutput(null).getItem(),
                                            getStack(OUTPUT_SLOT).getCount() + recipe.get().getOutput(null).getCount()));
                                    this.resetProgress();
                                    currentOutput = null;
                                }

                            }// else {
                            //    this.resetProgress();
                            //}
                        } else {
                            this.resetProgress();
                            markDirty(world, pos, state);
                        }
                    }
                }
            }
        }
    }

    //private void craftItem() {
//
    //    //change
    //    this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().getOutput(null).getItem(),
    //            getStack(OUTPUT_SLOT).getCount() + recipe.get().getOutput(null).getCount()));
    //}

    private Optional<CompactingRecipe> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for(int i=0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(CompactingRecipe.Type.INSTANCE, inv, getWorld());
    }

    private boolean hasRecipe() {
        Optional<CompactingRecipe> recipe = getCurrentRecipe();

        if(recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getOutput(getWorld().getRegistryManager());

        return canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
