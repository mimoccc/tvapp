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
import org.gradle.api.artifacts.ComponentSelection
import org.gradle.api.artifacts.ComponentSelectionRules
import org.gradle.internal.rules.RuleSourceBackedRuleAction
import org.gradle.model.internal.type.ModelType

@Suppress("unused")
class ComponentSelectionRulesWithCurrent(
    private val delegate: ComponentSelectionRules,
    private val currentCoordinates: Map<Coordinate.Key, Coordinate>,
) {

    fun all(
        selectionAction: Action<in ComponentSelectionWithCurrent>
    ): ComponentSelectionRulesWithCurrent {
        delegate.all {
            wrapComponentSelection(this)?.let { wrapped ->
                selectionAction.execute(wrapped)
            }
        }
        return this
    }

    fun all(closure: Closure<*>): ComponentSelectionRulesWithCurrent {
        delegate.all {
            wrapComponentSelection(this)?.let { wrapped ->
                closure.delegate = wrapped
                closure.call(wrapped)
            }
        }
        return this
    }

    fun all(ruleSource: Any): ComponentSelectionRulesWithCurrent {
        val ruleAction = RuleSourceBackedRuleAction
            .create(ModelType.of(ComponentSelectionWithCurrent::class.java), ruleSource)
        delegate.all {
            wrapComponentSelection(this)?.let { wrapped ->
                ruleAction.execute(wrapped, mutableListOf<Any>())
            }
        }
        return this
    }

    fun withModule(
        id: Any,
        selectionAction: Action<in ComponentSelectionWithCurrent>
    ): ComponentSelectionRulesWithCurrent {
        delegate.withModule(id) {
            wrapComponentSelection(this)?.let { wrapped ->
                selectionAction.execute(wrapped)
            }
        }
        return this
    }

    fun withModule(id: Any, closure: Closure<*>): ComponentSelectionRulesWithCurrent {
        delegate.withModule(id) {
            wrapComponentSelection(this)?.let { wrapped ->
                closure.delegate = wrapped
                closure.call(wrapped)
            }
        }
        return this
    }

    fun withModule(id: Any, ruleSource: Any): ComponentSelectionRulesWithCurrent {
        val ruleAction = RuleSourceBackedRuleAction
            .create(ModelType.of(ComponentSelectionWithCurrent::class.java), ruleSource)
        delegate.withModule(id) {
            wrapComponentSelection(this)?.let { wrapped ->
                ruleAction.execute(wrapped, mutableListOf<Any>())
            }
        }
        return this
    }

    private fun wrapComponentSelection(inner: ComponentSelection): ComponentSelectionWithCurrent? {
        val candidateCoordinate = Coordinate.from(inner.candidate)
        val current = currentCoordinates[candidateCoordinate.key] ?: return null
        return ComponentSelectionWithCurrent(inner, current.version)
    }
}
