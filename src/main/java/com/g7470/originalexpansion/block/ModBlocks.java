package com.g7470.originalexpansion.block;

import com.g7470.originalexpansion.OriginalExpansion;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static Block CRYSTAL_BRICKS=register("crystal_bricks",BlockBehaviour.Properties.of().strength(1.4f,8f),true);

    public static Block register(final String name,final Function<BlockBehaviour.Properties,Block> factory, final BlockBehaviour.Properties properties,boolean shouldRegisterItem) {
        ResourceKey<Block> id=ResourceKey.create(BuiltInRegistries.BLOCK.key(), Identifier.fromNamespaceAndPath(OriginalExpansion.MOD_ID,name));
        Block block=factory.apply(properties.setId(id));
        if (shouldRegisterItem){
            registerBlockItem(name,block);
        }
        return Registry.register(BuiltInRegistries.BLOCK,id,block);
    }
    public static Block register(final String name, final BlockBehaviour.Properties properties,boolean shouldRegisterItem) {
        return register(name,Block::new,properties,shouldRegisterItem);
    }
    public static void registerBlockItem(String name,Block block){
        ResourceKey<Item> id=ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(OriginalExpansion.MOD_ID,name));
        BlockItem blockItem=new BlockItem(block,new Item.Properties().setId(id).useBlockDescriptionPrefix());
        Registry.register(BuiltInRegistries.ITEM,id,blockItem);
    }

    public static void register(){
        OriginalExpansion.LOGGER.info("Registering Mod Items");
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS)
                .register(fabricCreativeModeTabOutput -> {
                    fabricCreativeModeTabOutput.accept(CRYSTAL_BRICKS);
                });
    }
}
