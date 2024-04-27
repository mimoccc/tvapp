/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("SpellCheckingInspection")

package org.mjdev.gradle.unittest.makers

import org.mjdev.gradle.unittest.Constants.Companion.TEST_OBJECT_NAME
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.MemberName
import net.bytebuddy.utility.RandomString
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import kotlin.coroutines.Continuation

internal class KotlinFunctionMakerImpl : KotlinFunctionMaker {
    companion object {
        private const val BEFORE_FUNCTION_NAME = "setUp"
        private val INDENT_LIST = arrayOf("", "\t", "\t\t", "\t\t\t", "\t\t\t\t")
        private const val FIELD_PREFIX = "test"
        private const val FIELD_SUFFIX = "Object"
        private const val EXPECTED_RESULT = "testExpectedResult"
        private const val KOTLIN_COROUTINES_PACKAGE = "kotlinx.coroutines"
        private const val ANDROID_CONTEXT_PACKAGE = "android.content.Context"
        private const val RUN_BLOCKING_METHOD = "runBlocking"
        private const val VAL = "val"
        private const val LATEINIT_VAR = "lateinit var"
    }

    override fun createBeforeFunction(clazz: Class<*>, testObjectMap: Map<String, Map<String, Class<*>>>): FunSpec {
        val beforeFunctionSpecBuilder = FunSpec.builder(BEFORE_FUNCTION_NAME)
                .addAnnotation(Before::class)
                .addStatement("%T.initMocks(this)", MockitoAnnotations::class)

        for (testObject in testObjectMap) {
            beforeFunctionSpecBuilder.addStatement("${testObject.key} = %T(", clazz)
            var j = 0
            val parameterLength = testObject.value.size
            for (parameter in testObject.value) {
                j += 1
                if (j < parameterLength) {
                    beforeFunctionSpecBuilder.addStatement("\t%N,", parameter.key)
                } else {
                    beforeFunctionSpecBuilder.addStatement("\t%N", parameter.key)
                }
            }
            beforeFunctionSpecBuilder.addStatement(")")
        }

        return beforeFunctionSpecBuilder.build()
    }

    override fun createTestFunction(
            functionName: String,
            method: Method
    ): FunSpec {
        val function = FunSpec.builder(functionName)
                .addAnnotation(Test::class)
        val parameterMap = HashMap<Class<*>, Int>()
        var isSuspend = false
        val listOfParams: MutableList<String> = ArrayList()
        for (parameter in method.parameterTypes) {
            if (parameter.isSynthetic) continue
            if (parameter.name == Continuation::class.java.name) {
                isSuspend = true
                continue
            }
            if (!parameterMap.containsKey(parameter)) {
                parameterMap[parameter] = 1
            } else {
                parameterMap[parameter] = parameterMap[parameter]!! + 1
            }
            val count = parameterMap[parameter]

            var simpleName = parameter.simpleName[0].uppercase() + parameter.simpleName.substring(1)
            simpleName = simpleName.trimEnd('[', ']')
            val name = if (count == 1) {
                FIELD_PREFIX + simpleName + FIELD_SUFFIX
            } else {
                FIELD_PREFIX + simpleName + FIELD_SUFFIX + count
            }
            listOfParams.add(name)
            val returnSet: MutableSet<Class<*>> = HashSet()
            returnSet.add(parameter)
            doDfs(name, parameter, parameterMap, function, returnSet)
            returnSet.remove(parameter)
        }

        val returnType = method.returnType
        val returnSet: MutableSet<Class<*>> = HashSet()
        returnSet.add(returnType)
        doDfs(EXPECTED_RESULT, returnType, parameterMap, function, HashSet())
        returnSet.remove(returnType)

        var indent = 0
        if (isSuspend) {
            val runBlocking = MemberName(KOTLIN_COROUTINES_PACKAGE, RUN_BLOCKING_METHOD)
            function.addStatement("%M {", runBlocking)
            indent += 1
        }
        function.addStatement("${INDENT_LIST[indent]}%T.assertEquals (", Assert::class)
        indent += 1
        function.addStatement("${INDENT_LIST[indent]}$EXPECTED_RESULT,")
        function.addStatement("${INDENT_LIST[indent]}$TEST_OBJECT_NAME.${method.name}(")
        var i = 0
        val n = listOfParams.size
        for (param in listOfParams) {
            i += 1
            if (i < n) {
                function.addStatement("${INDENT_LIST[indent + 1]}$param,")
            } else {
                function.addStatement("${INDENT_LIST[indent + 1]}$param")
            }
        }
        function.addStatement("${INDENT_LIST[indent]})")
        indent -= 1
        function.addStatement("${INDENT_LIST[indent]})")
        if (isSuspend) {
            indent -= 1
            function.addStatement("}")
        }

        return function.build()
    }

    private fun doDfs(
            nodeName: String,
            currentNode: Class<*>,
            parameterMap: MutableMap<Class<*>, Int>,
            functionBuilder: FunSpec.Builder,
            someSet: MutableSet<Class<*>>
    ) {
        if (currentNode.name == ANDROID_CONTEXT_PACKAGE) {
            functionBuilder.addStatement("$VAL $nodeName: %T = %T.mock(%T::class.java)", currentNode.kotlin, Mockito::class, currentNode.kotlin)
            return
        }
        if (currentNode.kotlin == String::class) {
            val randomString = RandomString.make()
            functionBuilder.addStatement("$VAL $nodeName: %T = \"$randomString\"", currentNode.kotlin)
            return
        }
        val constructors = currentNode.constructors
        if (constructors.isNotEmpty() && !currentNode.isPrimitive) {
            val listOfParams: MutableList<String> = ArrayList()
            for (constructor in constructors) {
                if (constructor.isSynthetic || Modifier.isPrivate(constructor.modifiers)) continue
                val list = constructor.parameterTypes
                for(parameter in list) {
                    if(someSet.contains(parameter)) {
                        functionBuilder.addStatement("$LATEINIT_VAR $nodeName: %T", currentNode.kotlin)
                        return
                    }
                }
                for (parameter in list) {
                    if (parameter.isSynthetic) continue
                    if (!parameterMap.containsKey(parameter)) {
                        parameterMap[parameter] = 1
                    } else {
                        parameterMap[parameter] = parameterMap[parameter]!! + 1
                    }
                    val count = parameterMap[parameter]
                    var simpleName = parameter.simpleName[0].uppercase() + parameter.simpleName.substring(1)
                    simpleName = simpleName.trimEnd('[', ']')
                    val name = if (count == 1) {
                        FIELD_PREFIX + simpleName + FIELD_SUFFIX
                    } else {
                        FIELD_PREFIX + simpleName + FIELD_SUFFIX + count
                    }
                    listOfParams.add(name)
                    someSet.add(currentNode)
                    doDfs(name, parameter, parameterMap, functionBuilder, someSet)
                    someSet.remove(currentNode)
                }
                break
            }
            functionBuilder.addStatement("$VAL $nodeName: %T = %T(", currentNode.kotlin, currentNode.kotlin)
            var i = 0
            val n = listOfParams.size
            for (param in listOfParams) {
                i += 1
                if (i < n) {
                    functionBuilder.addStatement("\t$param,")
                } else {
                    functionBuilder.addStatement("\t$param")
                }
            }
            functionBuilder.addStatement(")")
        } else {
            if (currentNode.isPrimitive) {
                when (currentNode.kotlin) {
                    Boolean::class -> {
                        functionBuilder.addStatement("$VAL $nodeName: %T = false", currentNode.kotlin)
                    }
                    Char::class -> {
                        functionBuilder.addStatement("$VAL $nodeName: %T = 'a'", currentNode.kotlin)
                    }
                    Byte::class, Short::class, Int::class, Long::class -> {
                        functionBuilder.addStatement("$VAL $nodeName: %T = 1", currentNode.kotlin)
                    }
                    Float::class, Double::class -> {
                        functionBuilder.addStatement("$VAL $nodeName: %T = 1.0", currentNode.kotlin)
                    }
                    Void::class -> {
                        functionBuilder.addStatement("$VAL $nodeName: %T = %T()", Any::class, Any::class)
                    }
                    else -> {
                        functionBuilder.addStatement("$LATEINIT_VAR $nodeName: %T", currentNode.kotlin)
                    }
                }
            } else {
                functionBuilder.addStatement("$LATEINIT_VAR $nodeName: %T", currentNode.kotlin)
            }
        }
    }

}