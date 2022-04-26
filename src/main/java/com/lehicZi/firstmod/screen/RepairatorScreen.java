package com.lehicZi.firstmod.screen;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.container.ReparatorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RepairatorScreen extends ContainerScreen<ReparatorContainer> {

    private final ResourceLocation GUI = new ResourceLocation(FirstMod.MOD_ID,
            "textures/gui/repairator_gui.png");

    public RepairatorScreen(ReparatorContainer reparatorContainer, PlayerInventory p_i51105_2_, ITextComponent p_i51105_3_) {
        super(reparatorContainer, p_i51105_2_, p_i51105_3_);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int topLeft = this.guiLeft;
        int top = this.guiTop;
        this.blit(matrixStack,topLeft, top, 0, 0, this.xSize,  this.ySize);

        if (container.isRepairating()) {
            this.blit(matrixStack, topLeft + 82, top + 9, 176, 0, 13, 17);
        }

    }
}
