package me.trqhxrd.veinminer.config

import org.bukkit.Material

open class ToolGroup(val name: String, val tools: Set<Material>) {
    /**
     * Indicates whether some other object is "equal to" this one. Implementations must fulfil the following
     * requirements:
     *
     * * Reflexive: for any non-null value `x`, `x.equals(x)` should return true.
     * * Symmetric: for any non-null values `x` and `y`, `x.equals(y)` should return true if and only if `y.equals(x)` returns true.
     * * Transitive:  for any non-null values `x`, `y`, and `z`, if `x.equals(y)` returns true and `y.equals(z)` returns true, then `x.equals(z)` should return true.
     * * Consistent:  for any non-null values `x` and `y`, multiple invocations of `x.equals(y)` consistently return true or consistently return false, provided no information used in `equals` comparisons on the objects is modified.
     * * Never equal to null: for any non-null value `x`, `x.equals(null)` should return false.
     *
     * Read more about [equality](https://kotlinlang.org/docs/reference/equality.html) in Kotlin.
     */
    override fun equals(other: Any?): Boolean {
        return other != null &&
                other is ToolGroup &&
                this.name == other.name &&
                this.tools.containsAll(other.tools) &&
                other.tools.containsAll(this.tools)
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + tools.hashCode()
        return result
    }
}
