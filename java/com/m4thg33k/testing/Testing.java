package com.m4thg33k.testing;

import com.m4thg33k.testing.init.ModBlocks;
import com.m4thg33k.testing.init.ModEntities;
import com.m4thg33k.testing.init.ModItems;
import com.m4thg33k.testing.init.ModTiles;
import com.m4thg33k.testing.proxy.CommonProxy;
import com.m4thg33k.testing.proxy.IProxy;
import com.m4thg33k.testing.reference.Reference;
import com.m4thg33k.testing.reference.WhiteListPylon;
import com.m4thg33k.testing.utility.ChatHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by M4thG33k on 4/15/2015.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Testing {
    //public static WhiteListPylon whiteListPylon;

    @Mod.Instance(Reference.MOD_ID)
    public static Testing testing;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //whiteListPylon = new WhiteListPylon();
        ModBlocks.init();
        ModTiles.init();
        ModItems.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenderers();
        ModEntities.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
