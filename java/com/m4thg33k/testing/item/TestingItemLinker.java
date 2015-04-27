package com.m4thg33k.testing.item;

import com.m4thg33k.testing.creativetab.CreativeTabTesting;
import com.m4thg33k.testing.reference.Reference;
import com.m4thg33k.testing.utility.ChatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by M4thG33k on 4/26/2015.
 */
public class TestingItemLinker extends Item {

    public TestingItemLinker()
    {
        setUnlocalizedName(Reference.MOD_ID+"_"+"testingItemLinker");
        setTextureName(Reference.MOD_ID + ":" + "testingItemLinker");
        setCreativeTab(CreativeTabTesting.TESTING_TAB);
        setMaxStackSize(1);
    }



    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if(itemStack.stackTagCompound == null)
        {
            itemStack.stackTagCompound = new NBTTagCompound();
            resetNBTTag(itemStack);
        }
        if (player.isSneaking())
        {
            if(itemStack.stackTagCompound.getBoolean("IsLinked"))
            {
                ChatHelper.sayMessage(world, player, "I am currently linked to the block at " + getLocation(itemStack));
                ChatHelper.sayMessage(world, player, ""+world.getBlock(getX(itemStack),getY(itemStack),getZ(itemStack)).getUnlocalizedName());
            }
            else
            {
                ChatHelper.sayMessage(world, player, "I am NOT currently linked");
            }
        }
        return itemStack;
}

    @Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        resetNBTTag(itemStack);
    }

    private void resetNBTTag(ItemStack itemStack)
    {
        initLink(itemStack);
        itemStack.stackTagCompound.setInteger("LinkedX", 0);
        itemStack.stackTagCompound.setInteger("LinkedY", 0);
        itemStack.stackTagCompound.setInteger("LinkedZ", 0);
        itemStack.stackTagCompound.setBoolean("IsLinked", false);
    }

    public void linkToBlockAt(ItemStack itemStack,int x,int y,int z)
    {
        initLink(itemStack);
        itemStack.stackTagCompound.setInteger("LinkedX", x);
        itemStack.stackTagCompound.setInteger("LinkedY", y);
        itemStack.stackTagCompound.setInteger("LinkedZ", z);
        itemStack.stackTagCompound.setBoolean("IsLinked", true);
    }

    public void initLink(ItemStack itemStack)
    {
        if (itemStack.stackTagCompound == null)
        {
            itemStack.stackTagCompound = new NBTTagCompound();
        }
    }

    public String getLocation(ItemStack itemStack)
    {
        String text = "(" + itemStack.stackTagCompound.getByte("LinkedX") + ", " + itemStack.stackTagCompound.getByte("LinkedY") + ", " + itemStack.stackTagCompound.getByte("LinkedZ") + ")";
        return text;
    }

    public int[] getLocationArray(ItemStack itemStack)
    {
        int locations[] = new int[3];
        if (itemStack.stackTagCompound.hasKey("LinkedX"))
        {
            locations[0] = itemStack.stackTagCompound.getInteger("LinkedX");
            locations[1] = itemStack.stackTagCompound.getInteger("LinkedY");
            locations[2] = itemStack.stackTagCompound.getInteger("LinkedZ");
        }
        else
        {
            for (int i=0;i<3;i++){
                locations[i] = 0;
            }
        }
        return locations;
    }

    public int getX(ItemStack itemStack)
    {
        return itemStack.stackTagCompound.getInteger("LinkedX");
    }

    public int getY(ItemStack itemStack)
    {
        return itemStack.stackTagCompound.getInteger("LinkedY");
    }

    public int getZ(ItemStack itemStack)
    {
        return itemStack.stackTagCompound.getInteger("LinkedZ");
    }

    public void setUnlinked(ItemStack itemStack)
    {
        itemStack.stackTagCompound.setBoolean("IsLinked",false);
    }
}
