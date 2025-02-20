package net.entropyentertainment.nathan.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A helper class for getting Item initialisation and registrykeys <br />
 *
 * @author &lt;null/&gt;
 */
public class RegistryHelper {
    public static Logger LOGGER = LogManager.getLogger(RegistryHelper.class);

    /**
     * Creates a new {@link net.minecraft.registry.RegistryKey} for the given {@link net.minecraft.item.Item}'s itemName
     *
     * @param itemName The itemName of the Item you need the registrykey for
     * @param <T>      Your main class which extends {@link ModEssentials} (it should grab this automatically!) add it like &lt;ClassName&gt;getItemRegistryKey(itemName)
     * @return A {@link net.minecraft.registry.RegistryKey} of your MOD_ID and itemName of the item
     */
    public static <T extends ModEssentials> RegistryKey<Item> getItemRegistryKey(String itemName) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(T.MOD_ID, itemName.toLowerCase()));
    }

    /**
     * Registers the given item to the Minecraft {@link net.minecraft.registry.Registry} <br />
     * I do recomend using {@link RegistryHelper#registerAndCreateItem(Function, T.Settings, RegistryKey)} instead!
     *
     * @param item        The item to register to the {@link net.minecraft.registry.Registry}
     * @param registryKey The registry key of the Item you're trying to register
     * @param <T>         The Item class to return and use for item in the method parameters, this should extend {@link net.minecraft.item.Item}
     * @return The newly registered {@link net.minecraft.item.Item}
     */
    public static <T extends Item> T registerItem(T item, @NotNull RegistryKey<Item> registryKey) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), item);
    }

    /**
     * Creates and registers an {@link net.minecraft.item.Item} to the Minecraft {@link net.minecraft.registry.Registry}. <br />
     *
     * @param itemFactory A {@code Function<T.Settings, T>} where {@link net.minecraft.item.Item} is the class you wish to register. Example: {@code Item::new}.
     * @param settings    The {@link T.Settings} used to configure the item.
     * @param registryKey The {@link net.minecraft.registry.RegistryKey} of the {@link net.minecraft.item.Item} you are trying to register.
     * @param <T>         The {@link net.minecraft.item.Item} class to return and use in the method parameters, this should extend {@link net.minecraft.item.Item}.
     * @return The newly registered {@link net.minecraft.item.Item}.
     */
    public static <T extends Item> T registerAndCreateItem(@NotNull Function<T.Settings, T> itemFactory, @NotNull T.Settings settings, @NotNull RegistryKey<Item> registryKey) {
        return Registry.register(Registries.ITEM, registryKey.getValue(), itemFactory.apply(settings));
    }

    /**
     * Creates and registers an {@link net.minecraft.item.Item} to the Minecraft {@link net.minecraft.registry.Registry}, using default settings. <br />
     * <p>
     * This method is an overload of {@link RegistryHelper#registerAndCreateItem(Function, Item.Settings, RegistryKey)}
     * and provides a default {@link net.minecraft.item.Item.Settings} instance. <br />
     * </p>
     *
     * @param itemFactory A {@code Function<T.Settings, T>} where {@link net.minecraft.item.Item} is the class you wish to register. Example: {@code Item::new}.
     * @param registryKey The {@link net.minecraft.registry.RegistryKey} of the {@link net.minecraft.item.Item} you are trying to register.
     * @param <T>         The {@link net.minecraft.item.Item} class to return and use in the method parameters, this should extend {@link net.minecraft.item.Item}.
     * @return The newly registered {@link net.minecraft.item.Item}.
     */
    public static <T extends Item> T registerAndCreateItem(@NotNull Function<T.Settings, T> itemFactory, @NotNull RegistryKey<Item> registryKey) {
        return registerAndCreateItem(itemFactory, new T.Settings().registryKey(registryKey), registryKey);
    }

    /**
     * Creates a registry key for an {@link net.minecraft.item.ItemGroup} using the given itemGroupName
     *
     * @param itemGroupName the itemName of the group you're trying to make an {@link net.minecraft.item.ItemGroup} of
     * @param <T>           Your main class which extends {@link ModEssentials} (it should grab this automatically!) add it like &lt;ClassName&gt;getItemRegistryKey(itemName)
     * @return The {@link net.minecraft.registry.RegistryKey} of your MOD_ID and itemName of the ItemGroup
     */
    public static <T extends ModEssentials> RegistryKey<ItemGroup> getGroupRegistryKey(String itemGroupName) {
        return RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(T.MOD_ID, itemGroupName));
    }

    /**
     * Registers the given {@link net.minecraft.item.ItemGroup} to Minecraft's {@link net.minecraft.registry.Registry}
     *
     * @param group       The {@link net.minecraft.item.ItemGroup} you wish to register to the {@link net.minecraft.registry.Registry}
     * @param registryKey The {@link net.minecraft.registry.RegistryKey} of the ItemGroup you are trying to register
     * @return A newly registered {@link net.minecraft.item.ItemGroup} of your RegistryKey
     */
    public static ItemGroup registerItemGroup(ItemGroup group, RegistryKey<ItemGroup> registryKey) {
        return Registry.register(Registries.ITEM_GROUP, registryKey, group);
    }

    /**
     * A method to add multiple Items to an already existing ItemGroup
     *
     * @param itemGroupKey a {@link net.minecraft.registry.RegistryKey} of the {@link net.minecraft.item.ItemGroup} you want to add the Items to
     * @param items        The {@link net.minecraft.item.Item}'s you want to register to an ItemGroup
     */
    public static void registerToItemGroup(RegistryKey<ItemGroup> itemGroupKey, Item... items) {
        for (Item item : items) {
            ItemGroupEvents.modifyEntriesEvent(itemGroupKey)
                    .register((IG) -> IG.add(item));
        }
    }
}

