package com.lehicZi.firstmod.block.custom;

import com.lehicZi.firstmod.container.ReparatorContainer;
import com.lehicZi.firstmod.tileentity.ModTileEntities;
import com.lehicZi.firstmod.tileentity.RipairitorTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class RepairatorBlock extends Block {
    public RepairatorBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.REPAIRATOR_TILE.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (!world.isRemote){

            TileEntity tileEntity = world.getTileEntity(pos);

            if(!player.isCrouching()) {
                if (tileEntity instanceof RipairitorTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(world, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                }

                else {
                    throw new IllegalStateException("Our container is missing");
                }
            }

        }

        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.firstmod.repairator");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity player) {
                return new ReparatorContainer(i, world, pos, playerInventory, player);
            }
        };
    }

    @Override
    public void animateTick(BlockState blockState, World world, BlockPos blockPos, Random random) {

        float chance = 0.35f;
        if (chance < random.nextFloat()){

            world.addParticle(ParticleTypes.ENCHANT,
                    blockPos.getX() + random.nextDouble(),
                    blockPos.getY() + 1,
                    blockPos.getZ() + random.nextDouble(),
                    0.05d, 0.05d, 0.05d);


        }

        super.animateTick(blockState, world, blockPos, random);
    }
}
