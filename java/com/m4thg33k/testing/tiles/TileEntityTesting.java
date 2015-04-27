package com.m4thg33k.testing.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by M4thG33k on 4/15/2015.
 */
public class TileEntityTesting extends TileEntity implements IInventory{

    private ItemStack[] inv;

    public TileEntityTesting()
    {
        inv = new ItemStack[1];
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return inv.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;
        if (stack!=null && stack.stackSize > getInventoryStackLimit())
        {
            stack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        ItemStack stack = getStackInSlot(slot);

        if (stack!=null)
        {
            if (stack.stackSize<=amt)
            {
                setInventorySlotContents(slot,null);
            }
            else
            {
                stack = stack.splitStack(amt);
                if (stack.stackSize==0)
                {
                    setInventorySlotContents(slot,null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack!=null)
        {
            setInventorySlotContents(slot,null);
        }
        return stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord,yCoord,zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        //System.out.print("I hit Z\n");
        super.readFromNBT(tagCompound);

        //System.out.print("I hit Y\n");
        NBTTagList tagList = tagCompound.getTagList("Items",10);

        //System.out.print("I hit X\n");
        for (int i=0;i<tagList.tagCount();i++)
        {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot>=0 && slot<inv.length)
            {
                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        //System.out.print("I read NBTData!\n");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound)
    {
        //System.out.print("I hit A\n");
        super.writeToNBT(tagCompound);

        //System.out.print("I hit B\n");
        NBTTagList itemList = new NBTTagList();

       // System.out.print("I hit C\n");
        for (int i = 0; i < inv.length; i++)
        {
            if (inv[i] != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                inv[i].writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        //System.out.print("I hit D\n");
        tagCompound.setTag("Items",itemList);
        //System.out.print("I just wrote NBT data\n");
    }

    @Override
    public String getInventoryName() {
        return "testing.tileentitytesting";
    }


}

