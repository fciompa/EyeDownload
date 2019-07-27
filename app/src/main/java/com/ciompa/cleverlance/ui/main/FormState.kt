package com.ciompa.cleverlance.ui.main

/**
 * Data validation state of the login form.
 */
data class FormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null
)
