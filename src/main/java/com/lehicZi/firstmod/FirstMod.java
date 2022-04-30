package com.lehicZi.firstmod;

import com.google.common.collect.ImmutableMap;
import com.ibm.icu.impl.locale.XCldrStub;
import com.lehicZi.firstmod.block.ModBlocks;
import com.lehicZi.firstmod.block.custom.ModWoodTypes;
import com.lehicZi.firstmod.container.ModContainers;
import com.lehicZi.firstmod.data.recipes.ModRecipeTypes;
import com.lehicZi.firstmod.fluid.ModFluids;
import com.lehicZi.firstmod.item.ModItems;
import com.lehicZi.firstmod.screen.LightningCrafterScreen;
import com.lehicZi.firstmod.screen.RepairatorScreen;
import com.lehicZi.firstmod.tileentity.ModTileEntities;
import com.lehicZi.firstmod.world.structure.ModStructures;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WoodType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.item.AxeItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("firstmod")
public class FirstMod
{

    public static final String MOD_ID = "firstmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public FirstMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register items in ModItems
        ModItems.register(eventBus);
        //Register blocks in ModBlocks
        ModBlocks.register(eventBus);
        //Register mod TileEntities
        ModTileEntities.register(eventBus);
        //Register mod Containers
        ModContainers.register(eventBus);
        //Register structures
        ModStructures.register(eventBus);
        //Refister mod fluids
        ModFluids.register(eventBus);
        //Register mod custom recipes
        ModRecipeTypes.register(eventBus);


        // Register the setup method for modloading
        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            AxeItem.BLOCK_STRIPPING_MAP = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.BLOCK_STRIPPING_MAP)
                    .put(ModBlocks.GEMWOOD_LOG.get(), ModBlocks.STRIPPED_GEMWOOD_LOG.get())
                    .put(ModBlocks.GEMWOOD_WOOD.get(), ModBlocks.STRIPPED_GEMWOOD_WOOD.get()).build();

            ModStructures.setupStructures();
            WoodType.register(ModWoodTypes.GEMWOOD);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.RUBY_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.RUBY_TRPDOOR.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.HOP.get(), RenderType.getCutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.GEMWOOD_LEAVES.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.GEMWOOD_SAPLING.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.PLOP.get(), RenderType.getCutout());

            ScreenManager.registerFactory((ModContainers.REPAIRATOR_CONTAINER.get()),
                    RepairatorScreen::new);
            ScreenManager.registerFactory((ModContainers.LIGHTNING_CRAFTER_CONTAINER.get()),
                    LightningCrafterScreen::new);

            ClientRegistry.bindTileEntityRenderer(ModTileEntities.SIGN_TILE_ENTITIES.get(),
                    SignTileEntityRenderer::new);
            Atlases.addWoodType(ModWoodTypes.GEMWOOD);

            RenderTypeLookup.setRenderLayer(ModFluids.REDWATER_FLUID.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.REDWATER_FLOWING.get(), RenderType.getTranslucent());
            RenderTypeLookup.setRenderLayer(ModFluids.REDWATER_BLOCK.get(), RenderType.getTranslucent());

        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
