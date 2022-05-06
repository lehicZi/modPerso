package com.lehicZi.firstmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;

public class Biceps extends SwordItem implements ICurioItem {

    public Biceps(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
        super(tier, attackDamageIn, attackSpeedIn, builderIn);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {

        PlayerEntity player = (PlayerEntity) livingEntity;

        if (!player.world.isRemote){
            boolean playerNotHasStrenght = Objects.equals(player.getActivePotionEffect(Effects.STRENGTH), null);

            if (playerNotHasStrenght) {
                player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 200, 1));
            }

        }


        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        return true;
    }
}
