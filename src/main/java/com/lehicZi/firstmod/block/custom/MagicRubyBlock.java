package com.lehicZi.firstmod.block.custom;

import com.lehicZi.firstmod.item.ModItems;
import com.lehicZi.firstmod.item.custom.MagicRuby;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class MagicRubyBlock extends Block {
    public MagicRubyBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    protected static final Random random = new Random();


    //Called when block is right clicked
    @Override
    public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos blockPos,
                                             PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {

        if (!world.isRemote){
            if (hand == Hand.MAIN_HAND) {
                System.out.println("Wesh d'où tu me clique droit dessus ?");
            }
            if (hand == Hand.OFF_HAND) {
                System.out.println("Wesh d'où tu me clique gauche dessus ?");
            }
        }

        return super.onBlockActivated(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
    }

    //Called when block is left clicked
    @Override
    public void onBlockClicked(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity) {

        if (!world.isRemote){

            if (playerEntity.getHeldItem(Hand.MAIN_HAND) == ModItems.MAGIC_RUBY.get().getDefaultInstance()){
                System.out.println("Wallah le ruby magique");
            }

        }

        super.onBlockClicked(blockState, world, blockPos, playerEntity);
    }

    //Call when an entity walks on the block
    @Override
    public void onEntityWalk(World world, BlockPos blockPos, Entity entity) {

        if (!world.isRemote) {
                //Checks if the entity can receive a potion effect
                if (entity instanceof LivingEntity) {
                    LivingEntity walker = (LivingEntity) entity;
                    //Checks if the entity already has an effect
                    if (walker.getActivePotionMap().isEmpty()) {
                        MagicRuby.givePotionEffect(random.nextFloat() > 0.5f, walker, 1200);
                        return;
                    }
                }

        }

        super.onEntityWalk(world, blockPos, entity);
    }


}
