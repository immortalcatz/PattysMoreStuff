package com.stc.pattysmorestuff.tools.items;

import com.stc.pattysmorestuff.tabs.ModTabs;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by StuffTheChicken on 14/11/2016.
 */
public class ItemStarShovel extends ItemSpade {

    public ItemStarShovel(String name, ToolMaterial material) {
        super(material);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(ModTabs.tabPattysTools);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(ItemStack stack) {
        return true;
    }
}
