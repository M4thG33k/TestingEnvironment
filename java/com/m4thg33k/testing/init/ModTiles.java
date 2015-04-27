package com.m4thg33k.testing.init;

import com.m4thg33k.testing.tiles.TestingTilePylon;
import com.m4thg33k.testing.tiles.TileEntityTesting;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by M4thG33k on 4/15/2015.
 */
public class ModTiles {

    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityTesting.class,"tileEntityTesting");
        GameRegistry.registerTileEntity(TestingTilePylon.class,"testingTilePylon");
    }
}
