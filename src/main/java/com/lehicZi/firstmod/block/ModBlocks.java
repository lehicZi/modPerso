package com.lehicZi.firstmod.block;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.block.custom.HopBlock;
import com.lehicZi.firstmod.block.custom.MagicRubyBlock;
import com.lehicZi.firstmod.block.custom.RepairatorBlock;
import com.lehicZi.firstmod.block.custom.trees.GemwoodTree;
import com.lehicZi.firstmod.item.ModItemGroup;
import com.lehicZi.firstmod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.OakTree;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    // List of all blocks in this class
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    // New blocks
    public static final RegistryObject<Block> BICEPS_BLOCK = registerBlock("biceps_block",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(10f)));

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new Block(AbstractBlock.Properties.create(Material.ROCK)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)));

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)));

    public static final RegistryObject<Block> MAGIC_RUBY_BLOCK = registerBlock("magic_ruby_block",
            () -> new MagicRubyBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)));

    public static final RegistryObject<Block> RUBY_STAIRS = registerBlock("ruby_stairs",
            () -> new StairsBlock(() -> RUBY_BLOCK.get().getDefaultState(),
                    AbstractBlock.Properties.create(Material.IRON)
                            .harvestLevel(3)
                            .harvestTool(ToolType.PICKAXE).setRequiresTool()
                            .hardnessAndResistance(7f)));

    public static final RegistryObject<Block> RUBY_FENCE = registerBlock("ruby_fence",
            () -> new FenceBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)));

    public static final RegistryObject<Block> RUBY_FENCE_GATE = registerBlock("ruby_fence_gate",
            () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)));


    public static final RegistryObject<Block> RUBY_SLAB = registerBlock("ruby_slab",
            () -> new SlabBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)));

    public static final RegistryObject<Block> RUBY_PRESURE_PLATE = registerBlock("ruby_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS,
                    AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)));

    public static final RegistryObject<Block> RUBY_BUTTON = registerBlock("ruby_button",
            () -> new StoneButtonBlock(AbstractBlock.Properties.create(Material.IRON)
                    .harvestLevel(3)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool().
                    hardnessAndResistance(8f)
                    .doesNotBlockMovement()));

    public static final RegistryObject<Block> RUBY_DOOR = registerBlock("ruby_door",
            () -> new DoorBlock(AbstractBlock.Properties.create(Material.IRON)
                            .harvestLevel(3)
                            .harvestTool(ToolType.PICKAXE).setRequiresTool()
                            .hardnessAndResistance(8f)
                            .notSolid()));

    public static final RegistryObject<Block> RUBY_TRPDOOR = registerBlock("ruby_trapdoor",
            () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.IRON)
                            .harvestLevel(3)
                            .harvestTool(ToolType.PICKAXE).setRequiresTool().
                            hardnessAndResistance(8f)
                    .notSolid()));

    public static final RegistryObject<Block> HOP = BLOCKS.register("hop_crop",
            () -> new HopBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));

    public static final RegistryObject<Block> GEMWOOD_WOOD = registerBlock("gemwood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool()));

    public static final RegistryObject<Block> GEMWOOD_LOG = registerBlock("gemwood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool()));

    public static final RegistryObject<Block> STRIPPED_GEMWOOD_WOOD = registerBlock("stripped_gemwood_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool()));

    public static final RegistryObject<Block> STRIPPED_GEMWOOD_LOG = registerBlock("stripped_gemwood_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE).setRequiresTool()));

    public static final RegistryObject<Block> GEMWOOD_PLANKS = registerBlock("gemwood_planks",
            () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> GEMWOOD_LEAVES = registerBlock("gemwood_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES)
                    .hardnessAndResistance(0.25f)
                    .tickRandomly().sound(SoundType.PLANT).notSolid()));

    public static final RegistryObject<Block> GEMWOOD_SAPLING = registerBlock("gemwood_sapling",
            () -> new SaplingBlock(new GemwoodTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> PLOP = registerBlock("plop",
            () -> new FlowerBlock(Effects.NAUSEA,1600 , AbstractBlock.Properties.from(Blocks.DANDELION)));

    public static final RegistryObject<Block> REPAIRATOR = registerBlock("repairator",
            () -> new RepairatorBlock(AbstractBlock.Properties.create(Material.IRON)));


    // Regiqters and creates a block and the blockitem.
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return  toReturn;
    }

    // Adds the block as an item and adds it to creative tab
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () ->
                new BlockItem(block.get(), new Item.Properties().group(ModItemGroup.FIRSTMOD_GROUP)));
    }

    // registers method call in mod's main class
    public static void register (IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
