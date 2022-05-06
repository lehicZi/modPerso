package com.lehicZi.firstmod.entity.render;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.entity.custom.LeleLeFouEntity;
import com.lehicZi.firstmod.entity.model.LeleLeFouModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class LeleLeFouRenderer<T extends LeleLeFouEntity> extends BipedRenderer<T, LeleLeFouModel<T>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(FirstMod.MOD_ID, "textures/entity/lele_le_fou.png");

    public LeleLeFouRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new LeleLeFouModel<>(0f), 0.7f);

        this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(0.5F)));
    }


    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return TEXTURE;
    }

    @Override
    protected void renderName(T entityIn, ITextComponent displayNameIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.renderName(entityIn, displayNameIn, matrixStackIn, bufferIn, packedLightIn);
    }
}
