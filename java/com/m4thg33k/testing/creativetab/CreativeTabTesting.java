package com.m4thg33k.testing.creativetab;

import com.m4thg33k.testing.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by M4thG33k on 4/15/2015.
 */
public class CreativeTabTesting {

    public static final CreativeTabs TESTING_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem(){
            return Items.boat;
        }
    };
}
