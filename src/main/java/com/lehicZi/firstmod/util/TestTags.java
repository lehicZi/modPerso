package com.lehicZi.firstmod.util;

import com.lehicZi.firstmod.FirstMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TestTags {

    public static class Blocks{

        public static final Tags.IOptionalNamedTag<Block> MAGIC_RUBY_UNDESTRUCTIBLE_BLOCKS =
                createTag("magic_ruby_undestructible_blocks");

        public static final Tags.IOptionalNamedTag<Block> GEMWOOD_LOGS =
                createTag("gemwood_logs");

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(FirstMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items{

        public static final Tags.IOptionalNamedTag<Item> RUBY = createForgeTag("gems/ruby");


        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(FirstMod.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }

}
