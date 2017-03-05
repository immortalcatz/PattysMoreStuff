package com.stc.pattysmorestuff.blocks.ores;

import com.stc.pattysmorestuff.blocks.item.IMetaBlockName;
import com.stc.pattysmorestuff.handlers.EnumHandler;
import com.stc.pattysmorestuff.lib.Strings;
import com.stc.pattysmorestuff.tabs.ModTabs;
import com.stc.pattysmorestuff.blocks.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Created by StuffTheChicken on 20/12/2016.
 */
public class BlockDyeOre extends Block implements IMetaBlockName {

    /**
     * The type property
     */
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumHandler.OreType.class);


    public BlockDyeOre(String unlocalizedName) {
        super(Material.ROCK);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(unlocalizedName);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setCreativeTab(ModTabs.tabPattysBlocks);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumHandler.OreType.OVERWORLD)); //Default state
    }



    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        tooltip.add("When mined the block will drop random amounts of dye and most likely different each time!");
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list) {
        for(int i = 0; i < EnumHandler.OreType.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);
        for(int i = 0; i < count; i++)
        {
            Item item = this.getItemDropped(state, rand, fortune);
            if (item != Items.AIR)
            {
                ret.add(new ItemStack(Items.DYE, 1, this.damageDropped(state)));
            }
        }
        return ret;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return this == ModBlocks.dye_ore ? 4 + random.nextInt(5) : 1;
    }

    /**
     * Get the quantity dropped based on the given fortune level
     */
    public int quantityDroppedWithBonus(int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this);
    }
    /**
     * Spawns this Block's drops into the World as EntityItems.
     */
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }
    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;

            if (this == ModBlocks.dye_ore)
            {
                i = MathHelper.getInt(rand, 2, 5);
            }
            return i;
        }
        return 0;
    }

    @Override
    public int damageDropped(IBlockState state) {

        Random random = new Random();

        return random.nextInt(16);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {TYPE});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumHandler.OreType type = (EnumHandler.OreType) state.getValue(TYPE);
        return type.getID();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumHandler.OreType.values()[meta]);
    }

    public String getSpecialName(ItemStack stack) {
        return EnumHandler.OreType.values()[stack.getItemDamage()].getName();
    }

}