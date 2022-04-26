package com.lehicZi.firstmod.item.custom;

import com.lehicZi.firstmod.util.TestTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.apache.http.util.EntityUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

// New item extends Item class
public class MagicRuby extends Item {
    public MagicRuby(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    //Method used when item is clicked on a block
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {

        ItemStack stack = context.getPlayer().getHeldItem(context.getHand());

        if (!context.getWorld().isRemote) {

                rightClickOnBlock(context);
                stack.damageItem(5, context.getPlayer(), player -> player.sendBreakAnimation(context.getHand()));
                return ActionResultType.SUCCESS;
        }


        return super.onItemUse(context);
    }


    //Method used on every right clicks
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerEntity, Hand hand) {

        //The item's instance
        ItemStack stack = playerEntity.getHeldItem(hand);
        //A raytraceresult : the extracted BlockPos is the block situated 7 block away from where the player is looking
        RayTraceResult result = rayTrace(playerEntity, 7F, RayTraceContext.FluidMode.NONE);
        BlockPos startPos = ((BlockRayTraceResult)result).getPos();

        if(!world.isRemote) {

            if (world.isAirBlock(startPos)) {
                rightClickOnAir(playerEntity);
                stack.damageItem(10, playerEntity, player -> player.sendBreakAnimation(hand));
            }
        }

        return super.onItemRightClick(world, playerEntity, hand);
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {

        if (Screen.hasShiftDown()){
            tooltip.add(new TranslationTextComponent("tooltip.firstmod.magic_ruby_shift"));
        }
        else {

            tooltip.add(new TranslationTextComponent("tooltip.firstmod.magic_ruby"));
        }

        super.addInformation(itemStack, world, tooltip, flag);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();

        if(container.attemptDamageItem(50, random, null)){
            return ItemStack.EMPTY;
        }
        else {
            return container;
        }

    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    //Applies positive or negative potion effect randomly
    private void rightClickOnAir(PlayerEntity player) {
            //Positive or negative potion effect
            givePotionEffect(random.nextFloat() > 0.5f, player, 1200);
    }

    //Destroys the targeted block
    private void rightClickOnBlock(ItemUseContext context) {
        BlockState clickedBlock = context.getWorld().getBlockState(context.getPos());
        if (!isDestructible(clickedBlock)) {
            context.getWorld().destroyBlock(context.getPos(), true);
        }
    }

    private boolean isDestructible (BlockState clickedBlock) {
        return clickedBlock.isIn(TestTags.Blocks.MAGIC_RUBY_UNDESTRUCTIBLE_BLOCKS);
    }

    //Lists applicable potion effects and returns a random one depending on if it is asked positive or negative
    public static void givePotionEffect(Boolean isPositive, LivingEntity livingEntity, int duration){
        List<Effect> potionEffects = new ArrayList<>();
        potionEffects.add(Effects.HASTE);
        potionEffects.add(Effects.INSTANT_DAMAGE);
        potionEffects.add(Effects.INSTANT_HEALTH);
        potionEffects.add(Effects.HUNGER);
        potionEffects.add(Effects.REGENERATION);
        potionEffects.add(Effects.RESISTANCE);
        potionEffects.add(Effects.DOLPHINS_GRACE);
        potionEffects.add(Effects.FIRE_RESISTANCE);
        potionEffects.add(Effects.MINING_FATIGUE);
        potionEffects.add(Effects.ABSORPTION);
        potionEffects.add(Effects.BLINDNESS);
        potionEffects.add(Effects.LUCK);
        potionEffects.add(Effects.NAUSEA);
        potionEffects.add(Effects.WEAKNESS);
        potionEffects.add(Effects.WITHER);
        potionEffects.add(Effects.WATER_BREATHING);
        potionEffects.add(Effects.JUMP_BOOST);
        potionEffects.add(Effects.SPEED);
        potionEffects.add(Effects.SLOWNESS);
        potionEffects.add(Effects.POISON);
        potionEffects.add(Effects.SATURATION);
        potionEffects.add(Effects.STRENGTH);

        int randomIndex = (int) (Math.random() * potionEffects.size());

        Effect selectedEffect = potionEffects.get(randomIndex);

        if (isPositive) {

            while (!selectedEffect.isBeneficial()){

                randomIndex = (int) (Math.random() * potionEffects.size());
                selectedEffect = potionEffects.get(randomIndex);
            }

        }

        else {

            while (selectedEffect.isBeneficial()){

                randomIndex = (int) (Math.random() * potionEffects.size());
                selectedEffect = potionEffects.get(randomIndex);
            }

        }

        if (selectedEffect == Effects.INSTANT_DAMAGE ||
        selectedEffect == Effects.INSTANT_HEALTH){

            livingEntity.addPotionEffect(new EffectInstance(selectedEffect, 1));

        }

        else {

            livingEntity.addPotionEffect(new EffectInstance(selectedEffect, duration));
        }

    }

    //Allows to get the rayTraceResult
    public static RayTraceResult rayTrace(Entity entity, double length, RayTraceContext.FluidMode fluidMode) {
        Vector3d startPos = entity.getEyePosition(1.0F);
        Vector3d endPos = startPos.add(entity.getLookVec().scale(length));
        return entity.world.rayTraceBlocks(new RayTraceContext(startPos, endPos, RayTraceContext.BlockMode.COLLIDER, fluidMode, entity));
    }


}
