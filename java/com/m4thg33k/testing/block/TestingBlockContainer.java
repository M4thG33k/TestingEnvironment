package com.m4thg33k.testing.block;

import com.m4thg33k.testing.creativetab.CreativeTabTesting;
import com.m4thg33k.testing.tiles.TileEntityTesting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by M4thG33k on 4/15/2015.
 */
public class TestingBlockContainer extends BlockContainer {

    public TestingBlockContainer(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTabTesting.TESTING_TAB);
    }

    public TestingBlockContainer()
    {
        super(Material.rock);
        this.setCreativeTab(CreativeTabTesting.TESTING_TAB);
    }

    public TileEntity createNewTileEntity(World world,int meta)
    {
        return null;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
        dropItems(world, x, y, z);
        super.breakBlock(world, x, y, z, par5, par6);
    }

    protected void dropItems(World world, int x, int y, int z)
    {
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x,y,z);
        if (!(tileEntity instanceof IInventory) || world.isRemote)
        {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i=0;i<inventory.getSizeInventory();i++)
        {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize>0)
            {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;


                EntityItem entityItem = new EntityItem(world, x+rx, y+ry, z+rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }


}
