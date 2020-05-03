// 
// Decompiled by Procyon v0.5.36
// 

package ru.NanoNik.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.IOException;
import cpw.mods.fml.common.network.FMLNetworkEvent;

public class ScreenHandler
{
    @SubscribeEvent
    public void onClientPacket(final FMLNetworkEvent.ClientCustomPacketEvent event) throws IOException {
        if (!event.packet.channel().equals("nGuard")) {
            return;
        }
        try {
            final byte[] read = event.packet.payload().array();
            final String[] data = new String(read).split(";");
            if (data[1].equals("screen")) {
                ScreenMaster.saveScreenshot(bao.B().w, data[0], bao.B().d, bao.B().e, bao.B().a());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
