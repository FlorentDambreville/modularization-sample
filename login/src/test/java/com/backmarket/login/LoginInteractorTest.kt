package com.backmarket.login

import com.backmarket.core.util.Result
import com.backmarket.login.usecase.LoginInteractor
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class LoginInteractorTest {

    private val interactor = LoginInteractor()

    companion object {
        @DataProvider
        @JvmStatic
        @Suppress("unused")
        fun parameters(): Array<Array<Any?>> {
            return arrayOf(
                arrayOf<Any?>("", false),
                arrayOf<Any?>("iamabackmaker", true),
                arrayOf<Any?>("-1", false),
                arrayOf<Any?>("null", false),
                arrayOf<Any?>("&", false),
                arrayOf<Any?>("anythingelse", false),
                arrayOf<Any?>("iamnotabackmaker", false)
            )
        }
    }

    @Test
    @UseDataProvider("parameters")
    fun validatePassword(password: String, expectedResult: Boolean) {
        // When
        val validatePasswordResult = interactor.validatePassword(password)

        // Then
        assertThat(validatePasswordResult).isEqualTo(expectedResult)
    }
}