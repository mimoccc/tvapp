/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.artifacts.DependencyResolveDetails
import org.gradle.api.artifacts.DependencySubstitutions
import org.gradle.api.artifacts.ResolutionStrategy

@Suppress("unused")
class ResolutionStrategyWithCurrent(
    private val delegate: ResolutionStrategy,
    private val currentCoordinates: Map<Coordinate.Key, Coordinate>,
) {

    fun failOnVersionConflict(): ResolutionStrategyWithCurrent {
        delegate.failOnVersionConflict()
        return this
    }

    fun activateDependencyLocking(): ResolutionStrategyWithCurrent {
        delegate.activateDependencyLocking()
        return this
    }

    fun force(vararg moduleVersionSelectorNotations: Any?): ResolutionStrategyWithCurrent {
        delegate.force(moduleVersionSelectorNotations)
        return this
    }

    fun setForcedModules(vararg moduleVersionSelectorNotations: Any?): ResolutionStrategyWithCurrent {
        delegate.setForcedModules(moduleVersionSelectorNotations)
        return this
    }

    fun eachDependency(rule: Action<in DependencyResolveDetails>): ResolutionStrategyWithCurrent {
        delegate.eachDependency(rule)
        return this
    }

    fun dependencySubstitution(
        action: Action<in DependencySubstitutions>
    ): ResolutionStrategyWithCurrent {
        delegate.dependencySubstitution(action)
        return this
    }

    fun componentSelection(
        action: Action<in ComponentSelectionRulesWithCurrent>
    ): ResolutionStrategyWithCurrent {
        action.execute(getComponentSelectionNonDelegate())
        return this
    }

    fun componentSelection(closure: Closure<*>): ResolutionStrategyWithCurrent {
        return componentSelection {
            closure.delegate = this
            closure.call(this)
        }
    }

    private fun getComponentSelectionNonDelegate(): ComponentSelectionRulesWithCurrent {
        return ComponentSelectionRulesWithCurrent(
            delegate.componentSelection,
            currentCoordinates
        )
    }
}
