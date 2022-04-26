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

    public static void register(IEventBus eventBus){
        TILE_ENTITIES.register(eventBus);
    }

}
