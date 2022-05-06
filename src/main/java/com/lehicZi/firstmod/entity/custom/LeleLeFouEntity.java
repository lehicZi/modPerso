package com.lehicZi.firstmod.entity.custom;

import com.lehicZi.firstmod.util.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.*;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Predicate;


public class LeleLeFouEntity extends ZombieEntity {

    public LeleLeFouEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 2D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 20.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 64.0D)
                .createMutableAttribute(Attributes.ARMOR, 30)
                .createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 15)
                .createMutableAttribute(Attributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal( 1, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true ) );
        this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player)
    {
        return 50 + this.world.rand.nextInt(50);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return ModSoundEvents.LELE_LE_FOU_WESH.get();
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_HOGLIN_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_HOGLIN_STEP, 0.20F, 0.5F);
    }


    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!super.attackEntityAsMob(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200,1));
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200));
                ((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.NAUSEA, 200));
            }
            return true;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        Random random = new Random();
        double x = this.getPosX() + (32 * random.nextDouble() - 16);
        double z = this.getPosZ() + (32 * random.nextDouble() - 16);
        double y = world.getHeight(Heightmap.Type.WORLD_SURFACE, (int) Math.round(x), (int) Math.round(z));

        if (!this.dead) {
            this.attemptTeleport(x, y, z, true);
        }

        if (this.getHealth() < 5f){
            this.addPotionEffect(new EffectInstance(Effects.REGENERATION, 200, 1));
            this.addPotionEffect(new EffectInstance(Effects.STRENGTH, 200, 1));
            this.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 200));
        }

        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void setChild(boolean childZombie) {
    }

    @Override
    protected boolean shouldBurnInDay() {
        return false;
    }

    @Override
    protected boolean shouldDrown() {
        return false;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.NETHERITE_SWORD));
        this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(Items.NETHERITE_HELMET));
        this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(Items.NETHERITE_CHESTPLATE));
        this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(Items.NETHERITE_LEGGINGS));
        this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(Items.NETHERITE_BOOTS));
    }

    @Override
    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance difficulty) {

        this.getHeldItem(Hand.MAIN_HAND).addEnchantment(Enchantments.SHARPNESS, 10);
        this.getHeldItem(Hand.MAIN_HAND).addEnchantment(Enchantments.FIRE_ASPECT, 5);
        this.getHeldItem(Hand.MAIN_HAND).addEnchantment(Enchantments.UNBREAKING, 30);

        for(EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
            if (equipmentslottype.getSlotType() == EquipmentSlotType.Group.ARMOR) {
                ItemStack armor = this.getItemStackFromSlot(equipmentslottype);
                armor.addEnchantment(Enchantments.PROTECTION, 10);
                armor.addEnchantment(Enchantments.THORNS, 5);
                armor.addEnchantment(Enchantments.UNBREAKING, 30);
            }
        }

    }

    @Override
    public boolean getAlwaysRenderNameTagForRender() {
        return true;
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    protected float getDropChance(EquipmentSlotType slotIn) {
        float f;
        switch(slotIn.getSlotType()) {
            case HAND:
            case ARMOR:
                f = 0.01f;
                break;
            default:
                f = 0.01F;
        }

        return f;
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        setEquipmentBasedOnDifficulty(difficultyIn);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}
