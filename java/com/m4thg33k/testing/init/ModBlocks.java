package com.m4thg33k.testing.init;

import com.m4thg33k.testing.block.TestingBlockContainer;
import com.m4thg33k.testing.block.TestingGuilessChest;
import com.m4thg33k.testing.block.TestingPylon;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by M4thG33k on 4/15/2015.
 */
public class ModBlocks {
    public static final TestingBlockContainer testingGuilessChest = new TestingGuilessChest();
    public static final TestingBlockContainer testingPylon = new TestingPylon();


    public static void init()
    {
        GameRegistry.registerBlock(testingGuilessChest, "testingGuilessChest");
        GameRegistry.registerBlock(testingPylon,"testingPylon");
    }
}
