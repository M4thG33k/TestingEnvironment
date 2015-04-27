package com.m4thg33k.testing.init;

import com.m4thg33k.testing.item.TestingEntitySpawner;
import com.m4thg33k.testing.item.TestingItemLinker;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by M4thG33k on 4/26/2015.
 */
public class ModItems {

    public static final TestingEntitySpawner testingEntitySpawner = new TestingEntitySpawner();
    public static final TestingItemLinker testingItemLinker = new TestingItemLinker();

    public static void init()
    {
        GameRegistry.registerItem(testingEntitySpawner,testingEntitySpawner.getUnlocalizedName());
        GameRegistry.registerItem(testingItemLinker,testingItemLinker.getUnlocalizedName());
    }
}
