package com.lehicZi.firstmod.block.custom;

import com.lehicZi.firstmod.container.LightningCrafterContainer;
import com.lehicZi.firstmod.container.ReparatorContainer;
import com.lehicZi.firstmod.tileentity.LightningCrafterTile;
import com.lehicZi.firstmod.tileentity.ModTileEntities;
import com.lehicZi.firstmod.tileentity.RipairitorTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.stream.Stream;

public class LightningCrafterBlock extends HorizontalBlock {
    public LightningCrafterBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.LIGHTNING_CRAFTER_TILE.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if (!world.isRemote){

            TileEntity tileEntity = world.getTileEntity(pos);

                if (tileEntity instanceof LightningCrafterTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(world, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                }

                else {
                    throw new IllegalStateException("Our container is missing");
                }

        }

        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.firstmod.lightning_crafter");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity player) {
                return new LightningCrafterContainer(i, world, pos, playerInventory, player);
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

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    private static VoxelShape SHAPE_N =
            Stream.of(
                    Block.makeCuboidShape(10, 10, 7, 11, 11, 9),
                    Block.makeCuboidShape(9, 9, 7, 11, 10, 9),
                    Block.makeCuboidShape(7, 7, 7, 9, 8, 9),
                    Block.makeCuboidShape(8, 8, 7, 10, 9, 9),
                    Block.makeCuboidShape(6, 6, 7, 8, 7, 9),
                    Block.makeCuboidShape(10, 5, 7, 11, 6, 9),
                    Block.makeCuboidShape(5, 5, 7, 9, 6, 9),
                    Block.makeCuboidShape(9, 4, 7, 10, 6, 9),
                    Block.makeCuboidShape(8, 3, 7, 9, 5, 9),
                    Block.makeCuboidShape(7, 2, 7, 8, 4, 9),
                    Block.makeCuboidShape(6, 1, 7, 7, 3, 9),
                    Block.makeCuboidShape(5, 0, 7, 6, 2, 9)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static VoxelShape SHAPE_W =
            Stream.of(
                    Block.makeCuboidShape(7, 10, 5, 9, 11, 6),
                    Block.makeCuboidShape(7, 9, 5, 9, 10, 7),
                    Block.makeCuboidShape(7, 7, 7, 9, 8, 9),
                    Block.makeCuboidShape(7, 8, 6, 9, 9, 8),
                    Block.makeCuboidShape(7, 6, 8, 9, 7, 10),
                    Block.makeCuboidShape(7, 5, 5, 9, 6, 6),
                    Block.makeCuboidShape(7, 5, 7, 9, 6, 11),
                    Block.makeCuboidShape(7, 4, 6, 9, 6, 7),
                    Block.makeCuboidShape(7, 3, 7, 9, 5, 8),
                    Block.makeCuboidShape(7, 2, 8, 9, 4, 9),
                    Block.makeCuboidShape(7, 1, 9, 9, 3, 10),
                    Block.makeCuboidShape(7, 0, 10, 9, 2, 11)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static VoxelShape SHAPE_S =
            Stream.of(
                    Block.makeCuboidShape(5, 10, 7, 6, 11, 9),
                    Block.makeCuboidShape(5, 9, 7, 7, 10, 9),
                    Block.makeCuboidShape(7, 7, 7, 9, 8, 9),
                    Block.makeCuboidShape(6, 8, 7, 8, 9, 9),
                    Block.makeCuboidShape(8, 6, 7, 10, 7, 9),
                    Block.makeCuboidShape(5, 5, 7, 6, 6, 9),
                    Block.makeCuboidShape(7, 5, 7, 11, 6, 9),
                    Block.makeCuboidShape(6, 4, 7, 7, 6, 9),
                    Block.makeCuboidShape(7, 3, 7, 8, 5, 9),
                    Block.makeCuboidShape(8, 2, 7, 9, 4, 9),
                    Block.makeCuboidShape(9, 1, 7, 10, 3, 9),
                    Block.makeCuboidShape(10, 0, 7, 11, 2, 9)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
    private static VoxelShape SHAPE_E =
            Stream.of(
                    Block.makeCuboidShape(7, 10, 10, 9, 11, 11),
                    Block.makeCuboidShape(7, 9, 9, 9, 10, 11),
                    Block.makeCuboidShape(7, 7, 7, 9, 8, 9),
                    Block.makeCuboidShape(7, 8, 8, 9, 9, 10),
                    Block.makeCuboidShape(7, 6, 6, 9, 7, 8),
                    Block.makeCuboidShape(7, 5, 10, 9, 6, 11),
                    Block.makeCuboidShape(7, 5, 5, 9, 6, 9),
                    Block.makeCuboidShape(7, 4, 9, 9, 6, 10),
                    Block.makeCuboidShape(7, 3, 8, 9, 5, 9),
                    Block.makeCuboidShape(7, 2, 7, 9, 4, 8),
                    Block.makeCuboidShape(7, 1, 6, 9, 3, 7),
                    Block.makeCuboidShape(7, 0, 5, 9, 2, 6)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(HORIZONTAL_FACING)){
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }
}
