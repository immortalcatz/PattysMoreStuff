package com.stc.pattysmorestuff.blocks.slabs;

/**
 * Created by patrick on 28/01/2017.
 */
public class BlockGrayStonebrickSlabDouble extends BlockGrayStonebrickSlab {
    public BlockGrayStonebrickSlabDouble(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}