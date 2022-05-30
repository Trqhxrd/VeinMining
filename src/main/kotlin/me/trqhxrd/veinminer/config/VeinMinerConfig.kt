package me.trqhxrd.veinminer.config

import me.trqhxrd.veinminer.VeinMiner
import org.bukkit.Material
import org.bukkit.configuration.serialization.ConfigurationSerializable

/**
 * This is the config
 */
class VeinMinerConfig(
    val plugin: VeinMiner,
    val toolGroups: MutableMap<String, Group> = mutableMapOf()
) : ConfigurationSerializable {

    @Suppress("UNCHECKED_CAST")
    constructor(map: Map<String, Any>) : this(
        VeinMiner.instance,
        map["groups"] as MutableMap<String, Group>
    )

    /**
     * Creates a Map representation of this class.
     *
     *
     * This class must provide a method to restore this class, as defined in
     * the [ConfigurationSerializable] interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    override fun serialize() = buildMap {
        this["groups"] = this@VeinMinerConfig.toolGroups
    }

    data class Group(
        val name: String,
        val permission: String?,
        val maxSize: Int,
        val tools: Set<Material>,
        val materials: Set<Material>
    ) : ConfigurationSerializable {
        @Suppress("UNCHECKED_CAST")
        constructor(map: Map<String, Any>) : this(
            map["name"] as String,
            map["permission"] as String?,
            map["maxSize"] as Int,
            (map["tools"] as Array<String>).map { Material.valueOf(it) }.toSet(),
            (map["materials"] as Array<String>).map { Material.valueOf(it) }.toSet()
        )

        /**
         * Creates a Map representation of this class.
         *
         *
         * This class must provide a method to restore this class, as defined in
         * the [ConfigurationSerializable] interface javadocs.
         *
         * @return Map containing the current state of this class
         */
        override fun serialize() = buildMap {
            this["name"] = this@Group.name
            this["permission"] = this@Group.permission
            this["maxSize"] = this@Group.maxSize
            this["tools"] = this@Group.tools.map { it.name }.toTypedArray()
            this["materials"] = this@Group.materials.map { it.name }.toTypedArray()
        }
    }
}
