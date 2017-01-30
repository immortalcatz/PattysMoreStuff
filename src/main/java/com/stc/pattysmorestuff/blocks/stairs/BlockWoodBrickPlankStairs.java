package com.stc.pattysmorestuff.blocks.stairs;

import com.stc.pattysmorestuff.tabs.ModTabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

/**
 * Created by patrick on 29/01/2017.
 */
public class BlockWoodBrickPlankStairs extends BlockStairs {

    public BlockWoodBrickPlankStairs(String name, IBlockState modelState) {
        super(modelState);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setCreativeTab(ModTabs.tabPattysBlocks);
    }
}
