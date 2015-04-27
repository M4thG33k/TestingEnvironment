package com.m4thg33k.testing.entities;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by M4thG33k on 4/26/2015.
 */
public class TestEntity extends EntityThrowable{

    public TestEntity(World world)
    {
        super(world);
    }
    public TestEntity(World world, EntityPlayer player)
    {
        super(world,player);
    }

    @Override
    protected void entityInit()
    {

    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompound)
    {

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound)
    {

    }

    @Override
    protected void onImpact(MovingObjectPosition position)
    {

        this.worldObj.setBlock(position.blockX,position.blockY,position.blockZ, Blocks.lava);

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
