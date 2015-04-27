package com.m4thg33k.testing.proxy;

import com.m4thg33k.testing.entities.TestEntity;
import com.m4thg33k.testing.renders.RenderTestEntity;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Created by M4thG33k on 4/15/2015.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers()
    {
        super.registerRenderers();
        EntityRegistry.registerGlobalEntityID(TestEntity.class, "testEntity", 0);
        RenderingRegistry.registerEntityRenderingHandler(TestEntity.class,new RenderTestEntity());
    }
}
