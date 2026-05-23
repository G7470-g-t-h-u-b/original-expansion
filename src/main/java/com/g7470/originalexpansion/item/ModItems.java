package com.g7470.originalexpansion.item;

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

import java.util.function.Function;

public class ModItems {
    public static final Item CRYSTAL=registerItem("crystal",Item::new,new Item.Properties());

    private static Item registerItem(final String name, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        ResourceKey<Item> key=ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(OriginalExpansion.MOD_ID,name));
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
    private static Item registerItem(final String name) {
        return registerItem(name,Item::new,new Item.Properties());
    }
    public static void register(){
        OriginalExpansion.LOGGER.info("Registering Mod Items");
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register(fabricCreativeModeTabOutput -> {
                    fabricCreativeModeTabOutput.accept(CRYSTAL);
                });
    }
}
