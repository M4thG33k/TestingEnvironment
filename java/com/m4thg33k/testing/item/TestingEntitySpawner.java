package com.m4thg33k.testing.item;

import com.m4thg33k.testing.entities.TestEntity;
import com.m4thg33k.testing.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by M4thG33k on 4/26/2015.
 */
public class TestingEntitySpawner extends Item {

    public TestingEntitySpawner()
    {
        setUnlocalizedName(Reference.MOD_ID + "_" + "testingEntitySpawner");
        setTextureName(Reference.MOD_ID + ":" + "testingEntitySpawner");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
//        if (!player.capabilities.isCreativeMode)
//        {
//            --itemStack.stackSize;
//        }

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(new TestEntity(world,player));
        }

        return itemStack;
    }
}
