// 
// Decompiled by Procyon v0.5.36
// 

package ru.NanoNik.handlers;

import org.apache.logging.log4j.LogManager;
import java.awt.FontMetrics;
import java.awt.Graphics;
import ru.NanoNik.imgur.Uploader;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import java.io.File;
import java.nio.IntBuffer;
import org.apache.logging.log4j.Logger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScreenMaster
{
    private static final Logger logger;
    private static IntBuffer pixelBuffer;
    private static int[] pixelValues;
    
    public static void saveScreenshot(final File p_148260_0_, final int p_148260_1_, final int p_148260_2_, final bmg p_148260_3_) {
        saveScreenshot(p_148260_0_, null, p_148260_1_, p_148260_2_, p_148260_3_);
    }
    
    public static void saveScreenshot(final File p_148259_0_, final String p_148259_1_, int p_148259_2_, int p_148259_3_, final bmg p_148259_4_) {
        try {
            if (buu.g()) {
                p_148259_2_ = p_148259_4_.a;
                p_148259_3_ = p_148259_4_.b;
            }
            final int k = p_148259_2_ * p_148259_3_;
            if (ScreenMaster.pixelBuffer == null || ScreenMaster.pixelBuffer.capacity() < k) {
                ScreenMaster.pixelBuffer = BufferUtils.createIntBuffer(k);
                ScreenMaster.pixelValues = new int[k];
            }
            GL11.glPixelStorei(3333, 1);
            GL11.glPixelStorei(3317, 1);
            ScreenMaster.pixelBuffer.clear();
            if (buu.g()) {
                GL11.glBindTexture(3553, p_148259_4_.g);
                GL11.glGetTexImage(3553, 0, 32993, 33639, ScreenMaster.pixelBuffer);
            }
            else {
                GL11.glReadPixels(0, 0, p_148259_2_, p_148259_3_, 32993, 33639, ScreenMaster.pixelBuffer);
            }
            ScreenMaster.pixelBuffer.get(ScreenMaster.pixelValues);
            bqi.a(ScreenMaster.pixelValues, p_148259_2_, p_148259_3_);
            BufferedImage bufferedimage = null;
            if (buu.g()) {
                bufferedimage = new BufferedImage(p_148259_4_.c, p_148259_4_.d, 1);
                int i1;
                for (int l = i1 = p_148259_4_.b - p_148259_4_.d; i1 < p_148259_4_.b; ++i1) {
                    for (int j1 = 0; j1 < p_148259_4_.c; ++j1) {
                        bufferedimage.setRGB(j1, i1 - l, ScreenMaster.pixelValues[i1 * p_148259_4_.a + j1]);
                    }
                }
            }
            else {
                bufferedimage = new BufferedImage(p_148259_2_, p_148259_3_, 1);
                bufferedimage.setRGB(0, 0, p_148259_2_, p_148259_3_, ScreenMaster.pixelValues, 0, p_148259_2_);
            }
            final String name = bao.B().h.getDisplayName();
            final Graphics graphics = bufferedimage.getGraphics();
            graphics.setFont(new Font("Arial Black", 1, 20));
            graphics.setColor(Color.red);
            final FontMetrics fm = graphics.getFontMetrics();
            final int x = bufferedimage.getWidth() - fm.stringWidth(name) - 5;
            final int y = bufferedimage.getHeight() - 5;
            graphics.drawString(name, x, 20);
            graphics.dispose();
            if (p_148259_1_ != null) {
                new Thread(new Uploader(bufferedimage, p_148259_1_)).start();
            }
            else {
                new Thread(new Uploader(bufferedimage, null)).start();
            }
        }
        catch (Exception exception) {
            ScreenMaster.logger.warn("Couldn't save screenshot", (Throwable)exception);
        }
    }
    
    static {
        logger = LogManager.getLogger();
    }
}
