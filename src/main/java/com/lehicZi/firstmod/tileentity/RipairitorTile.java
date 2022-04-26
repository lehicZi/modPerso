package com.lehicZi.firstmod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class RipairitorTile extends TileEntity implements ITickableTileEntity{

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public RipairitorTile(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.write(compound);
    }

    public RipairitorTile(){
        this(ModTileEntities.REPAIRATOR_TILE.get());
    }

    private ItemStackHandler createHandler(){
        return new ItemStackHandler(2){
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                        return stack.getItem().isDamageable();
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }


            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if ((!isItemValid(slot, stack))){
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {

        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }
        return super.getCapability(cap);
    }


    public boolean isRepairing;
    public void repairing(){

        ItemStack stackInSlot = this.itemHandler.getStackInSlot(0);
        Item itemInSlot = stackInSlot.getItem();

        boolean hasReparableItemIn = stackInSlot.getCount() > 0
                && itemInSlot.isDamaged(stackInSlot);
                // && stackInSlot.getDamage()  < stackInSlot.getDamage();

        if (hasReparableItemIn){
            isRepairing = true;
            stackInSlot.setDamage(stackInSlot.getDamage() - 1);
        }

        else {
            isRepairing = false;
        }

    }

    //Called on each tick
    @Override
    public void tick() {
        if (world.getWorldInfo().getGameTime() % 20 == 0) { //Every s
            repairing();
        }
    }
}
