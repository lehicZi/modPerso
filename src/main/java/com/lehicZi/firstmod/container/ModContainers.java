package com.lehicZi.firstmod.container;

import com.lehicZi.firstmod.FirstMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, FirstMod.MOD_ID);

    public static final RegistryObject<ContainerType<ReparatorContainer>> REPAIRATOR_CONTAINER =
            CONTAINERS.register("repairator_container",
                    () -> IForgeContainerType.create((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        World world = inv.player.getEntityWorld();
                        return new ReparatorContainer(windowId ,world, pos, inv, inv.player);
                    }));

    public static final RegistryObject<ContainerType<LightningCrafterContainer>> LIGHTNING_CRAFTER_CONTAINER =
            CONTAINERS.register("lightning_crafter_container",
                    () -> IForgeContainerType.create((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        World world = inv.player.getEntityWorld();
                        return new LightningCrafterContainer(windowId ,world, pos, inv, inv.player);
                    }));


    public static void register(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }


}
