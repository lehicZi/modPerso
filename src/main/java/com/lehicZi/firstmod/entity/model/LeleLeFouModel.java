package com.lehicZi.firstmod.entity.model;

import com.lehicZi.firstmod.entity.custom.LeleLeFouEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class LeleLeFouModel <T extends LeleLeFouEntity> extends PlayerModel<T> {

	public LeleLeFouModel(float modelSize) {
        super(modelSize, false);
        textureWidth = 64;
        textureHeight = 32;

        bipedHead = new ModelRenderer(this);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.05F, true);

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        bipedRightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        bipedLeftArm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        bipedRightLeg = new ModelRenderer(this);
        bipedRightLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        bipedRightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        bipedLeftLeg = new ModelRenderer(this);
        bipedLeftLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        bipedLeftLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
    }

        @Override
        public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
                bipedHead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                bipedBody.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                bipedLeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                bipedRightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                bipedLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                bipedRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        }

}
