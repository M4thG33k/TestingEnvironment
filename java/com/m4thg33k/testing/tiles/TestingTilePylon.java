package com.m4thg33k.testing.tiles;

import com.m4thg33k.testing.utility.ChatHelper;
import com.m4thg33k.testing.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by M4thG33k on 4/18/2015.
 */
public class TestingTilePylon extends TileEntity implements IInventory {

    private ItemStack inv[];
    private int parentLocation[];

    public TestingTilePylon()
    {
        inv = new ItemStack[2];
        LogHelper.info("During init, we have parentLocation as " + this.getParent());
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (this.getStackInSlot(slot) != null)
        {
            return false;
        }
        if (slot == 0)
        {
            if (itemStack.getItem() == Items.redstone)
            {
                return true;
            }
        }
        else if (slot==1)
        {
            if (itemStack.getItem() == Items.coal)
            {
                return true;
            }
        }
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
        if (stack!= null && stack.stackSize > getInventoryStackLimit())
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
                if (stack.stackSize == 0)
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
        return worldObj.getTileEntity(xCoord,yCoord,zCoord) == this && player.getDistanceSq(xCoord + 0.5,yCoord + 0.5,zCoord + 0.5)<64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);

        NBTTagList tagList = tagCompound.getTagList("Items", 10);

        for (int i=0;i<tagList.tagCount();i++)
        {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot>=0 && slot<inv.length)
            {
                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        LogHelper.info("I am about to read the parent's data at location (" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")");
        if (tagCompound.hasKey("ParentLocation"))
        {
            this.setParent(tagCompound.getIntArray("ParentLocation"));
            LogHelper.info("Got the data! W00t! " + this.getParent());
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);

        NBTTagList itemList = new NBTTagList();

        for (int i=0;i<inv.length;i++){
            if (inv[i]!=null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot",(byte)i);
                inv[i].writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Items", itemList);
        if (parentLocation!=null)
        {
            tagCompound.setIntArray("ParentLocation", parentLocation);
            LogHelper.info("I wrote the parent's location as (" + tagCompound.getIntArray("ParentLocation")[0] + ", " + tagCompound.getIntArray("ParentLocation")[1] + ", " + tagCompound.getIntArray("ParentLocation")[2]+")");
        }
    }

    @Override
    public String getInventoryName() {
        return "testing.testingTilePylon";
    }

    public void setParent(int x, int y, int z)
    {
        LogHelper.info("Starting to set the parent");
        if(parentLocation == null)
        {
            LogHelper.info("Initializing the array");
            parentLocation = new int[3];
        }
        parentLocation[0] = x;
        parentLocation[1] = y;
        parentLocation[2] = z;
        LogHelper.info("I have set my parent as (" + x + ", " + y + ", " + z +").");

    }

    public void setParent(int locations[])
    {
        if(parentLocation == null)
        {
            parentLocation = new int[3];
        }
        for (int i=0;i<3;i++)
        {
            parentLocation[i] = locations[i];
        }
    }

    public String getParent()
    {
        if (parentLocation == null)
        {
            return "nowhere. Are you my mummy?";
        }
        return "(" + parentLocation[0] + ", " + parentLocation[1] + ", " + parentLocation[2] + ")";
    }
}
