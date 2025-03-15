package nl.nullptrexc.modcore;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A helper class for creating registry keys and registering blocks, items, and item groups.
 * <p>
 * This utility leverages the mod identifier defined in your main mod class (extending {@link ModCore})
 * via its {@code MOD_ID} field.
 * </p>
 * <p>
 * Usage examples:
 * <ul>
 *   <li>Creating a registry key for an item: <code>RegistryHelper.getItemRegistryKey("my_item")</code></li>
 *   <li>Registering a block: <code>RegistryHelper.registerBlock(myBlock, getBlockRegistryKey("my_block"))</code></li>
 * </ul>
 * </p>
 *
 * @author &lt;null/&gt;
 */
public class RegistryHelper {

    // -------------------------------------------------------------------------
    // Registry Key Creation Methods
    // -------------------------------------------------------------------------

    /**
     * Creates a registry key for an {@link Item} using the provided item name.
     *
     * @param itemName the name of the item
     * @param <T>      the mod core type that provides {@code MOD_ID}
     * @return a {@link RegistryKey} for the item under the mod's identifier
     */
    public static <T extends ModCore> RegistryKey<Item> getItemRegistryKey(@NotNull String itemName) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(T.MOD_ID, itemName.toLowerCase()));
    }

    /**
     * Creates a tag key for a {@link Block} using the provided tag identifier.
     *
     * @param blockTagID the identifier for the block tag
     * @param <T>        the mod core type that provides {@code MOD_ID}
     * @return a {@link TagKey} for the block tag under the mod's identifier
     */
    public static <T extends ModCore> TagKey<Block> getBlockTagKey(@NotNull String blockTagID) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(T.MOD_ID, blockTagID));
    }

    /**
     * Creates a registry key for a {@link Block} using the provided block name.
     *
     * @param blockName the name of the block
     * @param <T>       the mod core type that provides {@code MOD_ID}
     * @return a {@link RegistryKey} for the block under the mod's identifier
     */
    public static <T extends ModCore> RegistryKey<Block> getBlockRegistryKey(@NotNull String blockName) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(T.MOD_ID, blockName));
    }

    /**
     * Creates a registry key for an {@link ItemGroup} using the provided group name.
     *
     * @param itemGroupName the name of the item group
     * @param <T>           the mod core type that provides {@code MOD_ID}
     * @return a {@link RegistryKey} for the item group under the mod's identifier
     */
    public static <T extends ModCore> RegistryKey<ItemGroup> getGroupRegistryKey(@NotNull String itemGroupName) {
        return RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(T.MOD_ID, itemGroupName));
    }

    // -------------------------------------------------------------------------
    // Item Registration Methods
    // -------------------------------------------------------------------------

    /**
     * Registers the given {@link Item} with the specified registry key.
     *
     * @param item        the item to register
     * @param registryKey the registry key for the item
     * @param <T>         the type of the item
     * @return the registered item
     */
    public static <T extends Item> T registerItem(@NotNull T item, @NotNull RegistryKey<Item> registryKey) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    /**
     * Creates and registers an {@link Item} using the provided factory function and settings.
     *
     * @param itemFactory a function that creates an item from the given settings
     * @param settings    the settings for the item
     * @param registryKey the registry key for the item
     * @param <T>         the type of the item
     * @return the newly created and registered item
     */
    public static <T extends Item> T registerAndCreateItem(@NotNull Function<T.Settings, T> itemFactory, @NotNull Item.Settings settings, @NotNull RegistryKey<Item> registryKey) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), itemFactory.apply(settings));
    }

    /**
     * Creates and registers an {@link Item} using the provided factory function and default settings.
     * <p>
     * This overload creates a new {@link Item.Settings} instance, applies the registry key to it,
     * and registers the item.
     * </p>
     *
     * @param itemFactory a function that creates an item from settings
     * @param registryKey the registry key for the item
     * @param <T>         the type of the item
     * @return the newly created and registered item
     */
    public static <T extends Item> T registerAndCreateItem(@NotNull Function<T.Settings, T> itemFactory, @NotNull RegistryKey<Item> registryKey) {
        return registerAndCreateItem(itemFactory, new Item.Settings().registryKey(registryKey), registryKey);
    }

    /**
     * Creates and registers an {@link Item} using the provided factory function, settings, and item name.
     * <p>
     * The item name is used to generate the registry key.
     * </p>
     *
     * @param itemFactory the factory function to create the item
     * @param settings    the settings for the item
     * @param itemName    the name of the item
     * @param <T>         the type of the item
     * @return the newly created and registered item
     */
    public static <T extends Item> T registerAndCreateItem(@NotNull Function<T.Settings, T> itemFactory, @NotNull Item.Settings settings, @NotNull String itemName) {
        return registerAndCreateItem(itemFactory, settings, getItemRegistryKey(itemName));
    }

    /**
     * Creates and registers an {@link Item} using the provided factory function and item name, with default settings.
     * <p>
     * The item name is used to generate the registry key.
     * </p>
     *
     * @param itemFactory the factory function to create the item
     * @param itemName    the name of the item
     * @param <T>         the type of the item
     * @return the newly created and registered item
     */
    public static <T extends Item> T registerAndCreateItem(@NotNull Function<T.Settings, T> itemFactory, @NotNull String itemName) {
        return registerAndCreateItem(itemFactory, getItemRegistryKey(itemName));
    }

    // -------------------------------------------------------------------------
    // Block Registration Methods
    // -------------------------------------------------------------------------

    /**
     * Creates, registers, and optionally registers an associated {@link BlockItem} for a new {@link Block}.
     * <p>
     * This method uses the provided factory to create a block with the given settings and a generated registry key derived from the block name.
     * If {@code registerAsItem} is true, it also creates and registers a corresponding {@link BlockItem} for the block using default item settings.
     * </p>
     *
     * @param blockFactory a function that creates a block instance using block settings
     * @param settings     the settings for the block; the generated registry key is applied to these settings
     * @param blockName    the name of the block, used to generate registry keys for both the block and its item
     * @param <T>          the type of the block
     * @return the registered block instance
     */
    public static <T extends Block> T registerAndCreateBlock(String blockName, Function<T.Settings, T> blockFactory, AbstractBlock.Settings settings) {
        RegistryKey<Block> blockRegistryKey = getBlockRegistryKey(blockName);
        T registeredBlock = Registry.register(Registries.BLOCK, blockRegistryKey, blockFactory.apply(settings.registryKey(blockRegistryKey)));

        RegistryKey<Item> itemRegistryKey = getItemRegistryKey(blockName);
        Registry.register(Registries.ITEM, itemRegistryKey, new BlockItem(registeredBlock, new Item.Settings().registryKey(itemRegistryKey)));

        return registeredBlock;
    }

    // -------------------------------------------------------------------------
    // Item Group Registration Methods
    // -------------------------------------------------------------------------

    /**
     * Registers the given {@link ItemGroup} with the specified registry key.
     *
     * @param group       the item group to register
     * @param registryKey the registry key for the item group
     * @return the registered item group
     */
    public static ItemGroup registerItemGroup(@NotNull ItemGroup group, @NotNull RegistryKey<ItemGroup> registryKey) {
        return Registry.register(Registries.ITEM_GROUP, registryKey, group);
    }

    /**
     * Adds multiple {@link Item} instances to an existing {@link ItemGroup}.
     *
     * @param itemGroupKey the registry key of the item group to modify
     * @param items        the items to add to the group
     */
    public static void registerToItemGroup(@NotNull RegistryKey<ItemGroup> itemGroupKey, Item... items) {
        for (Item item : items) {
            ItemGroupEvents.modifyEntriesEvent(itemGroupKey)
                    .register(entries -> entries.add(item));
        }
    }
}
