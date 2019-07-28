package com.ciompa.cleverlance.common

import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

/**
 * inject lazily given dependency for Android koin component
 * @param qualifier - bean qualifier / optional
 * @param scope
 * @param parameters - injection parameters
 */
inline fun <reified T : Any> inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy { get<T>(qualifier, parameters) }

/**
 * get given dependency for Android koin component
 * @param name - bean name
 * @param scope
 * @param parameters - injection parameters
 */
inline fun <reified T : Any> get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = getKoin().get(qualifier, parameters)


/**
 * Get Koin context
 */
fun getKoin() = GlobalContext.get().koin

