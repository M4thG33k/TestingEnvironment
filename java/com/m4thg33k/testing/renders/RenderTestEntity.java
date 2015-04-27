package com.m4thg33k.testing.renders;

import com.m4thg33k.testing.entities.TestEntity;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by M4thG33k on 4/26/2015.
 */
public class RenderTestEntity extends Render {

    public RenderTestEntity()
    {

    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation("gui/items.png");
    }

    public void doRenderTestEntity(TestEntity testEntity,double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslated((float) par2, (float) par4, (float) par6);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f2 = 1.0F;
        GL11.glScalef(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
        IIcon icon = Items.fire_charge.getIconFromDamage(0);
        this.bindTexture(new ResourceLocation("/gui/items.png"));
        Tessellator tessellator = Tessellator.instance;
        float f3 = icon.getMinU();
        float f4 = icon.getMaxU();
        float f5 = icon.getMinV();
        float f6 = icon.getMaxV();
        float f7 = 1.0F;
        float f8 = 0.5F;
        float f9 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double) (0.0F - f8), (double) (0.0F - f9), 0.0D, (double) f3, (double) f6);
        tessellator.addVertexWithUV((double) (f7 - f8), (double) (0.0F - f9), 0.0D, (double) f4, (double) f6);
        tessellator.addVertexWithUV((double) (f7 - f8), (double) (1.0F - f9), 0.0D, (double) f4, (double) f5);
        tessellator.addVertexWithUV((double) (0.0F - f8), (double) (1.0F - f9), 0.0D, (double) f3, (double) f5);
        tessellator.draw();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    public void doRender(Entity entity,double par2,double par4, double par6, float par8, float par9)
    {
        this.doRenderTestEntity((TestEntity)entity,par2,par4,par6,par8,par9);
    }
}
