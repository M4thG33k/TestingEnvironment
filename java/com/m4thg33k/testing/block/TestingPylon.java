package com.m4thg33k.testing.block;

import com.m4thg33k.testing.item.TestingItemLinker;
import com.m4thg33k.testing.reference.Reference;
import com.m4thg33k.testing.tiles.TestingTilePylon;
import com.m4thg33k.testing.utility.ChatHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

/**
 * Created by M4thG33k on 4/18/2015.
 */
public class TestingPylon extends TestingBlockContainer{

    public TestingPylon()
    {
        super();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.setBlockName("testingBasePylon");
        this.setBlockTextureName(Reference.MOD_ID + ":" + "testingBasePylon");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TestingTilePylon();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity == null || !(tileEntity instanceof TestingTilePylon))
        {
            return false;
        }

        ItemStack held = player.getHeldItem();
        if (!player.isSneaking() && held!=null)
        {
            TestingTilePylon tEnt = (TestingTilePylon)tileEntity;
            if (held.getItem() instanceof TestingItemLinker && held.stackTagCompound != null)
            {
                if (!held.stackTagCompound.getBoolean("IsLinked"))
                {
                    ((TestingItemLinker) held.getItem()).linkToBlockAt(held, x, y, z);
                }
                else
                {
                    tEnt.setParent(((TestingItemLinker)held.getItem()).getLocationArray(held));
                    ((TestingItemLinker) held.getItem()).setUnlinked(held);
                }
            }
            //check if held item can be placed in slot 0
            if (tEnt.isItemValidForSlot(0,held))
            {
                setInventorySlotTo(player,tEnt,0);
                return true;
            }

            //check if held item can be placed in slot 1
            else if (tEnt.isItemValidForSlot(1,held))
            {

                setInventorySlotTo(player,tEnt,1);
                return true;
            }
            return true;
        }

        if (!player.isSneaking() && held==null)
        {
            ChatHelper.sayMessage(world, player, "The location of my parent is " + ((TestingTilePylon) tileEntity).getParent());
        }

        if (player.isSneaking() && held == null)
        {
            dropItems(world,x,y,z);
            ((TestingTilePylon)tileEntity).setInventorySlotContents(0, null);
            ((TestingTilePylon)tileEntity).setInventorySlotContents(1,null);
            return true;
        }
        return false;
    }

    private void setInventorySlotTo(EntityPlayer player, TestingTilePylon tilePylon,int slot)
    {
        tilePylon.setInventorySlotContents(slot, player.getHeldItem().splitStack(1));
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        Block below = world.getBlock(x,y-1,z);



        if (below != Blocks.lapis_block)
        {
            return false;
        }

        Block[] blocks = new Block[4];
        blocks[0] = world.getBlock(x+1,y,z);
        blocks[1] = world.getBlock(x-1,y,z);
        blocks[2] = world.getBlock(x,y,z+1);
        blocks[3] = world.getBlock(x,y,z-1);
        for (int i=0;i<4;i++){
            if (blocks[i] == Blocks.chest)
            {
                return true;
            }
        }


        return false;
    }
}





































