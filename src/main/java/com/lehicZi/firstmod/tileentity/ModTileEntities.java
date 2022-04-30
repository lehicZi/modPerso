package com.lehicZi.firstmod.tileentity;

import com.lehicZi.firstmod.FirstMod;
import com.lehicZi.firstmod.block.ModBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, FirstMod.MOD_ID);

    public static RegistryObject<TileEntityType<RipairitorTile>> REPAIRATOR_TILE =
            TILE_ENTITIES.register("ripairator_tile", () -> TileEntityType.Builder.create(
                    RipairitorTile::new, ModBlocks.REPAIRATOR.get()).build(null));

    public static RegistryObject<TileEntityType<ModSignTileEntity>> SIGN_TILE_ENTITIES =
            TILE_ENTITIES.register("sign", () -> TileEntityType.Builder.create(
                   ModSignTileEntity::new,
                            ModBlocks.GEMWOOD_SIGN.get(),
                            ModBlocks.GEMWOOD_WALL_SIGN.get())
                    .build(null));

    public static RegistryObject<TileEntityType<LightningCrafterTile>> LIGHTNING_CRAFTER_TILE =
            TILE_ENTITIES.register("lightning_crafter_tile", () -> TileEntityType.Builder.create(
                    LightningCrafterTile::new, ModBlocks.LIGHTNING_CRAFTER.get()).build(null));

    public static void register(IEventBus eventBus){
        TILE_ENTITIES.register(eventBus);
    }

}
