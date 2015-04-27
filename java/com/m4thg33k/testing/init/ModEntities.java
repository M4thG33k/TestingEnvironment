package com.m4thg33k.testing.init;

import com.m4thg33k.testing.Testing;
import com.m4thg33k.testing.entities.TestEntity;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Created by M4thG33k on 4/26/2015.
 */
public class ModEntities {


    public static void init()
    {
        EntityRegistry.registerModEntity(TestEntity.class,"testEntity",0,Testing.testing,80,3,true);
    }
}
