package com.m4thg33k.testing.block;

import com.m4thg33k.testing.reference.Reference;
import com.m4thg33k.testing.tiles.TileEntityTesting;
import net.minecraft.block.Block;
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
public class TestingGuilessChest extends TestingBlockContainer {

    public TestingGuilessChest()
    {
        super();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.setBlockName("testingGuilessChest");
        this.setBlockTextureName(Reference.MOD_ID + ":" + "testingGuilessChest");


    }

//    @Override
//    public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
//    {
//        dropItems(world,x,y,z);
//        super.breakBlock(world,x,y,z,par5,par6);
//    }
//
//    private void dropItems(World world, int x, int y, int z)
//    {
//        Random rand = new Random();
//
//        TileEntity tileEntity = world.getTileEntity(x,y,z);
//        if (!(tileEntity instanceof IInventory) || world.isRemote)
//        {
//            return;
//        }
//        IInventory inventory = (IInventory) tileEntity;
//
//        for (int i=0;i<inventory.getSizeInventory();i++)
//        {
//            ItemStack item = inventory.getStackInSlot(i);
//
//            if (item != null && item.stackSize>0)
//            {
//                float rx = rand.nextFloat() * 0.8F + 0.1F;
//                float ry = rand.nextFloat() * 0.8F + 0.1F;
//                float rz = rand.nextFloat() * 0.8F + 0.1F;
//
//
//                EntityItem entityItem = new EntityItem(world, x+rx, y+ry, z+rz, new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
//
//                if (item.hasTagCompound())
//                {
//                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
//                }
//
//                float factor = 0.05F;
//                entityItem.motionX = rand.nextGaussian() * factor;
//                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
//                entityItem.motionZ = rand.nextGaussian() * factor;
//                world.spawnEntityInWorld(entityItem);
//                item.stackSize = 0;
//            }
//        }
//    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityTesting();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x,y,z);

        if (tileEntity == null)
        {
            return false;
        }
        ItemStack held = player.getHeldItem();
        if (!player.isSneaking() && held != null && tileEntity instanceof TileEntityTesting) {
            TileEntityTesting tEnt = (TileEntityTesting) tileEntity;
            ItemStack slot = tEnt.getStackInSlot(0);
            if (slot!=null)
            {
                return false;
            }
            else
            {
                ItemStack toPlace = new ItemStack(held.getItem(),1,held.getItemDamage());
                if (held.hasTagCompound())
                {
                    toPlace.setTagCompound((NBTTagCompound) held.getTagCompound().copy());
                }
                tEnt.setInventorySlotContents(0, toPlace);
                player.getHeldItem().splitStack(1);
                return true;
            }
        }
        else if (player.isSneaking() && held == null && tileEntity instanceof TileEntityTesting && !world.isRemote)
        {
//            TileEntityTesting tEnt = (TileEntityTesting) tileEntity;
//            ItemStack slot = tEnt.getStackInSlot(0);
//            if (slot != null && slot.stackSize>0)
//            {
//                EntityItem entityItem = new EntityItem(world,x,y,z,new ItemStack(slot.getItem(),slot.stackSize,slot.getItemDamage()));
//                if (slot.hasTagCompound())
//                {
//                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) slot.getTagCompound().copy());
//                }
//                world.spawnEntityInWorld(entityItem);
//                tEnt.decrStackSize(0,1);
//            }
            dropItems(world,x,y,z);
            ((TileEntityTesting) tileEntity).decrStackSize(0,1);
            return true;
        }
        return true;
    }
}
