// 
// Decompiled by Procyon v0.5.36
// 

package ru.NanoNik;

//import cpw.mods.fml.common.network.FMLEventChannel; FROM 1710
import net.minecraftforge.fml.common.network.FMLEventChannel;
//import cpw.mods.fml.relauncher.ReflectionHelper; FROM 1710
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import ru.NanoNik.handlers.ProfilerHook;
import ru.NanoNik.handlers.ScreenHandler;
//import cpw.mods.fml.common.network.NetworkRegistry; FROM 1710
import net.minecraftforge.fml.common.network.NetworkRegistry;
//import cpw.mods.fml.common.event.FMLInitializationEvent; FROM 1710
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//import cpw.mods.fml.common.Mod;
import net.minecraftforge.fml.common.

@Mod(modid = "nGuard", version = "1.0.0.1")Ini
public class Main
{
    public static final String MODID = "nGuard";
    public static final String VERSION = "1.0.0.1";
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        final FMLEventChannel nevent = NetworkRegistry.INSTANCE.newEventDrivenChannel("nGuard");
        nevent.register((Object)new ScreenHandler());
        ReflectionHelper.setPrivateValue((Class)bao.class, (Object)bao.B(), (Object)new ProfilerHook(), 58);
    }
}
