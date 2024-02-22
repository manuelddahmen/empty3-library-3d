/*
 *
 *  * Copyright (c) 2024. Manuel Daniel Dahmen
 *  *
 *  *
 *  *    Copyright 2024 Manuel Daniel Dahmen
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

package one.empty3.library1.tree

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Test class for AlgebraicTree.
 */
@RunWith(JUnit4::class)
class TestStringAnalyzer1 {
    @Test
    fun testStringAnalyzerPackage() {
        val stringAnalyzer1 = StringAnalyzer1()
        val token = stringAnalyzer1.TokenPackage()
        val input = "package"
        var parse = -1
        try {
            parse = stringAnalyzer1.parse(token, input)
//            parse = stringAnalyzer1.mPosition
        } catch (ex: RuntimeException) {
            if (token.isSuccessful) {
                if (stringAnalyzer1.mPosition >= input.length) {
                    Assert.assertTrue(true)

                    println(stringAnalyzer1.construct)
                    return
                }
            }

            Assert.assertTrue(false)
        }

        println("parse = " + stringAnalyzer1.mPosition + "/" + input.length)
        println("isSuccessful = " + token.isSuccessful)


        if (token.isSuccessful) {
            if (parse >= input.length) {
                Assert.assertTrue(true)
                return

            }
        }
        Assert.assertTrue(false)

    }

    @Test
    fun testStringAnalyzerPackageDeclaration() {
        val stringAnalyzer1 = StringAnalyzer1()
        val tokenPackage = stringAnalyzer1.TokenPackage()
        val tokenPackageName = stringAnalyzer1.TokenQualifiedName()
        val tokenPackageSemicolon = stringAnalyzer1.TokenSemiColon()

        tokenPackage.addToken(tokenPackageName)
        tokenPackageName.addToken(tokenPackageSemicolon)

        val token = stringAnalyzer1.SingleTokenOptional(tokenPackage)


        val input = "package com.example;"
        var parse = -1
        try {
            parse = stringAnalyzer1.parse(token, input)
//            parse = stringAnalyzer1.mPosition
        } catch (ex: RuntimeException) {
            if (token.isSuccessful) {
                if (parse >= input.length) {
                    Assert.assertTrue(true)

                    println(stringAnalyzer1.construct)
                    return
                }
            }

            Assert.assertTrue(false)
        }

        stringAnalyzer1.construct.packageName = tokenPackageName.name


        println("TestStringAnalyzer1.testStringAnalyzerPackage")
        println("parse = " + parse + "/" + input.length)
        println("isSuccessful = " + token.isSuccessful + " tokenSemicolon : " + tokenPackageSemicolon.isSuccessful)
        println("Name : " + tokenPackageName.name)

        if (token.isSuccessful) {
            if (parse >= input.length) {
                Assert.assertTrue(true)

                println(stringAnalyzer1.construct)
                return
            }
        }

        Assert.assertTrue(false)


    }

    @Test
    fun testStringAnalyzerPackageAndClassDeclaration() {
        val stringAnalyzer1 = StringAnalyzer1()
        val tokenPackage = stringAnalyzer1.TokenPackage()
        val tokenPackageName = stringAnalyzer1.TokenQualifiedName()
        val tokenPackageSemicolon = stringAnalyzer1.TokenSemiColon()
        val tokenClass = stringAnalyzer1.TokenClassKeyword()
        val tokenClassName = stringAnalyzer1.TokenName()
        val tokenOpenBracket = stringAnalyzer1.TokenOpenBracket()
        val tokenCloseBracket = stringAnalyzer1.TokenCloseBracket()


        tokenPackage.addToken(tokenPackageName)
        tokenPackageName.addToken(tokenPackageSemicolon)
        tokenClass.addToken(tokenClassName)
        tokenClassName.addToken(tokenOpenBracket)
        tokenOpenBracket.addToken(tokenCloseBracket)

        val token = stringAnalyzer1.SingleTokenOptional(tokenPackage)

        token.addToken(tokenClass)

        val input = "package com.example;\nclass Graph {\n}"
        var parse = -1
        try {
            parse = stringAnalyzer1.parse(token, input)
//            parse = stringAnalyzer1.mPosition
        } catch (ex: RuntimeException) {
            if (token.isSuccessful) {
                if (parse >= input.length) {
                    Assert.assertTrue(true)

                    println(stringAnalyzer1.construct)
                    return
                }
            }

            Assert.assertTrue(false)
        }

        stringAnalyzer1.construct.packageName = tokenPackageName.name
        stringAnalyzer1.construct.currentClass.name = tokenClassName.name


        println("TestStringAnalyzer1.testStringAnalyzerPackage")
        println("parse = " + parse + "/" + input.length)
        println("isSuccessful = " + token.isSuccessful + " tokenSemicolon : " + tokenPackageSemicolon.isSuccessful)
        println("Name : " + tokenPackageName.name)

        if (token.isSuccessful) {
            if (parse >= input.length) {
                Assert.assertTrue(true)

                println(stringAnalyzer1.construct)
                return
            }
        }

        Assert.assertTrue(false)


    }

    /*
        private fun buildJavaClassTokenList(stringAnalyzer1: StringAnalyzer1): StringAnalyzer1.SingleTokenOptional {
            val tokenPackage = stringAnalyzer1.TokenPackage()
            val tokenPackageName = stringAnalyzer1.TokenQualifiedName()
            val tokenPackageSemicolon = stringAnalyzer1.TokenSemiColon()
            val tokenClass = stringAnalyzer1.TokenClassKeyword()
            val tokenClassName = stringAnalyzer1.TokenName()
            val tokenOpenBracket = stringAnalyzer1.TokenOpenBracket()
            val tokenCloseBracket = stringAnalyzer1.TokenCloseBracket()

            val tokenMemberVarType1 = stringAnalyzer1.TokenQualifiedName()
            val tokenMemberVarName1 = stringAnalyzer1.TokenName()
            val tokenMemberVarEquals1 = stringAnalyzer1.TokenEquals()
            val tokenMemberExpression1 = stringAnalyzer1.TokenExpression1()
            val tokenSemiColonVar1 = stringAnalyzer1.TokenSemiColon()

            val tokenMemberVarType2 = stringAnalyzer1.TokenQualifiedName()
            val tokenMemberVarName2 = stringAnalyzer1.TokenName()
            val tokenSemiColonVar2 = stringAnalyzer1.TokenSemiColon()

            tokenMemberVarType1.addToken(tokenMemberVarName1)
            tokenMemberVarEquals1.addToken(tokenMemberVarEquals1)
            tokenMemberVarEquals1.addToken(tokenMemberExpression1)
            tokenMemberExpression1.addToken(tokenSemiColonVar1)

            tokenMemberVarType2.addToken(tokenMemberVarName2)
            tokenMemberVarName2.addToken(tokenSemiColonVar2)


            val tokenMemberMethodVarType1 = stringAnalyzer1.TokenQualifiedName()
            val tokenMemberMethodVarName1 = stringAnalyzer1.TokenName()
            val tokenSemiMethodColonVar1 = stringAnalyzer1.TokenSemiColon()

            val tokenMemberMethodVarType2 = stringAnalyzer1.TokenQualifiedName()
            val tokenMemberMethodVarName2 = stringAnalyzer1.TokenName()
            val tokenMemberMethodVarEquals2 = stringAnalyzer1.TokenEquals()
            val tokenMemberMethodExpression2 = stringAnalyzer1.TokenExpression1()
            val tokenMethodSemiColonVar2 = stringAnalyzer1.TokenSemiColon()


            tokenMemberMethodVarType2.addToken(tokenMemberMethodVarName2)
            tokenMemberMethodVarName2.addToken(tokenMemberMethodVarEquals2)
            tokenMemberMethodVarEquals2.addToken(tokenMethodSemiColonVar2)
            tokenMethodSemiColonVar2.addToken(tokenMemberMethodExpression2)

            tokenMemberMethodVarType1.addToken(tokenMemberMethodVarName1)
            tokenMemberMethodVarName1.addToken(tokenSemiMethodColonVar1)

            val tokenMemberVar = stringAnalyzer1.SingleTokenExclusiveXor(
                tokenMemberVarType1, tokenMemberVarType2
            )

            val tokenMemberMethodType = stringAnalyzer1.TokenQualifiedName()
            val tokenMemberMethodName = stringAnalyzer1.TokenName()
            val tokenOpenBracketMethod = stringAnalyzer1.TokenOpenBracket()

            tokenMemberMethodType.addToken(tokenMemberMethodName)
            tokenMemberMethodName.addToken(tokenOpenBracketMethod)

            // Instructions
            val singleTokenExclusiveXorMethodInstruction =
                stringAnalyzer1.SingleTokenExclusiveXor(
                    tokenMemberMethodVarType1,
                    tokenMemberMethodVarType2
                )

            val tokenMemberMethodVarType = stringAnalyzer1.TokenQualifiedName()
            val tokenMemberMethodVarName = stringAnalyzer1.TokenName()
            val tokenMemberMethodEquals = stringAnalyzer1.TokenEquals()
            val tokenMemberMethodExpression = stringAnalyzer1.TokenExpression1()


            // End of Instructions

            val tokenMultiMembersInstructions = stringAnalyzer1.MultiTokenOptional(
                tokenMemberMethodVarType1, tokenMemberMethodVarType2
            )

            tokenMemberMethodType.addToken(tokenMultiMembersInstructions)

            tokenPackage.addToken(tokenPackageName)
            tokenPackageName.addToken(tokenPackageSemicolon)
            tokenClass.addToken(tokenClassName)
            tokenClassName.addToken(tokenOpenBracket)
            val multiTokenOptional = stringAnalyzer1.MultiTokenOptional(
                tokenMemberVar, tokenMemberMethodType
            )
            tokenOpenBracket.addToken(multiTokenOptional)
            multiTokenOptional.addToken(tokenCloseBracket)

            val token = stringAnalyzer1.SingleTokenOptional(tokenPackage)

            token.addToken(tokenClass)

            return token

        }
    */
    @Test
    fun testStringAnalyzerPackageAndClassDeclarationAndImpl() {
        val stringAnalyzer1: StringAnalyzer1 = StringAnalyzer1()


        val tokenPackage = stringAnalyzer1.TokenPackage()
        val tokenPackageName = stringAnalyzer1.TokenQualifiedName()
        val tokenPackageSemicolon = stringAnalyzer1.TokenSemiColon()
        val tokenClass: StringAnalyzer1.Token = stringAnalyzer1.TokenClassKeyword()


        class ActionPackageName : Action(tokenPackageName) {
            public override fun action(): Boolean {
                if (!tokenPackageName.name.isEmpty()) {
                    stringAnalyzer1.construct.currentClass.setPackageName(tokenPackageName.name)
                    stringAnalyzer1.construct.packageName = tokenPackageName.name
                }
                return true
            }
        }
        tokenPackageName.setAction(ActionPackageName())

        class ActionClassKeyword : Action(tokenClass) {
            public override fun action(): Boolean {
                stringAnalyzer1.construct.classes.add(stringAnalyzer1.construct.currentClass)
                stringAnalyzer1.construct.currentClass.setPackageName(stringAnalyzer1.construct.packageName)
                return true
            }
        }
        tokenClass.setAction(ActionClassKeyword())

        val tokenClassName = stringAnalyzer1.TokenName()

        class ActionClassname : Action(tokenClassName) {
            override fun action(): Boolean {
                if (tokenClassName.name != null) {
                    stringAnalyzer1.construct.currentClass.name = tokenClassName.name
                }
                return true
            }
        }
        tokenClass.setAction(ActionClassname())
        val tokenOpenBracket = stringAnalyzer1.TokenOpenBracket()
        val tokenCloseBracketClass = stringAnalyzer1.TokenCloseBracket()

        // Variables members declarations
        val tokenMemberVarType3 = stringAnalyzer1.TokenQualifiedName()
        val tokenMemberVarName3 = stringAnalyzer1.TokenName()
        val tokenSemiColonVarSemiColon3 = stringAnalyzer1.TokenSemiColon()

        val tokenMemberVarType1 = stringAnalyzer1.TokenQualifiedName()
        val tokenMemberVarName1 = stringAnalyzer1.TokenName()
        val tokenMemberVarEquals1 = stringAnalyzer1.TokenEquals()
        val tokenMemberExpression1 = stringAnalyzer1.TokenExpression1()
        val tokenSemiColonVarSemiColon1 = stringAnalyzer1.TokenSemiColon()

        val tokenMemberVarType2 = stringAnalyzer1.TokenQualifiedName()
        val tokenMemberVarName2 = stringAnalyzer1.TokenName()
        val tokenSemiColonVarSemiColon2 = stringAnalyzer1.TokenSemiColon()

        tokenMemberVarType1.addToken(tokenMemberVarName1)
        tokenMemberVarName1.addToken(tokenMemberVarEquals1)
        tokenMemberVarEquals1.addToken(tokenMemberExpression1)
        tokenMemberExpression1.addToken(tokenSemiColonVarSemiColon1)

        tokenMemberVarType2.addToken(tokenMemberVarName2)
        tokenMemberVarName2.addToken(tokenSemiColonVarSemiColon2)

        tokenMemberVarType3.addToken(tokenMemberVarName3)
        tokenMemberVarName3.addToken(tokenSemiColonVarSemiColon3)

        // Method's instructions
        val tokenMemberMethodVarType1 = stringAnalyzer1.TokenQualifiedName()
        val tokenMemberMethodVarName1 = stringAnalyzer1.TokenName()
        val tokenSemiMethodColonVar1 = stringAnalyzer1.TokenSemiColon()

        val tokenMemberMethodVarType2 = stringAnalyzer1.TokenQualifiedName()
        val tokenMemberMethodVarName2 = stringAnalyzer1.TokenName()
        val tokenMemberMethodVarEquals2 = stringAnalyzer1.TokenEquals()
        val tokenMemberMethodExpression2 = stringAnalyzer1.TokenExpression1()
        val tokenMethodSemiColonVar2 = stringAnalyzer1.TokenSemiColon()

        val tokenMemberMethodExpression3 = stringAnalyzer1.TokenExpression1()
        val tokenMethodSemiColonVar3 = stringAnalyzer1.TokenSemiColon()

        tokenMemberMethodVarType2.addToken(tokenMemberMethodVarName2)
        tokenMemberMethodVarName2.addToken(tokenMemberMethodVarEquals2)
        tokenMemberMethodVarEquals2.addToken(tokenMemberMethodExpression2)
        tokenMemberMethodExpression2.addToken(tokenMethodSemiColonVar2)

        tokenMemberMethodVarType1.addToken(tokenMemberMethodVarName1)
        tokenMemberMethodVarName1.addToken(tokenSemiMethodColonVar1)

        tokenMemberMethodExpression3.addToken(tokenMethodSemiColonVar3)

        val tokenMemberVar = stringAnalyzer1.SingleTokenExclusiveXor(
            tokenMemberVarType1, tokenMemberVarType2, tokenMemberVarType3
        )

        val tokenMemberMethodType = stringAnalyzer1.TokenQualifiedName()
        val tokenMemberMethodName = stringAnalyzer1.TokenName()

        // Arguments' list
        val tokenOpenParenthesizedMethodParameter = stringAnalyzer1.TokenOpenParenthesized()
        val tokenComaMethodParameter1 = stringAnalyzer1.TokenComa()
        val tokenQualifiedNameMethodParameter1 = stringAnalyzer1.TokenQualifiedName()
        val tokenNameMethodParameter1 = stringAnalyzer1.TokenName()

        val tokenComaMethodParameter2 = stringAnalyzer1.TokenComa()
        val tokenQualifiedNameMethodParameter2 = stringAnalyzer1.TokenQualifiedName()
        val tokenNameMethodParameter2 = stringAnalyzer1.TokenName()

        val tokenCloseParenthesizedMethodParameter = stringAnalyzer1.TokenCloseParenthesized()

        val multiTokenOptionalMethodParameter2 =
            stringAnalyzer1.MultiTokenMandatory(
                tokenComaMethodParameter1, tokenQualifiedNameMethodParameter1, tokenNameMethodParameter1
            )
        val multiTokenOptionalMethodParameter1 =
            stringAnalyzer1.MultiTokenMandatory(
                tokenQualifiedNameMethodParameter2, tokenNameMethodParameter2
            )

        val multiTokenOptionalMethodParameter = stringAnalyzer1.MultiTokenExclusiveXor(
            multiTokenOptionalMethodParameter1, multiTokenOptionalMethodParameter2
        )
        /*
        multiTokenOptionalMethodParameter1.addToken(
            stringAnalyzer1.MultiTokenOptional(
                multiTokenOptionalMethodParameter2
            )
        )*/
        tokenOpenParenthesizedMethodParameter.addToken(multiTokenOptionalMethodParameter)
        multiTokenOptionalMethodParameter.addToken(tokenCloseParenthesizedMethodParameter)
        //multiTokenOptionalMethodParameter2.addToken(tokenCloseParenthesizedMethodParameter)//???
        val tokenOpenBracketMethod = stringAnalyzer1.TokenOpenBracket()
        tokenCloseParenthesizedMethodParameter.addToken(tokenOpenBracketMethod)


        class ActionTokenOpenParenthesizedMethodParameter : Action(tokenOpenParenthesizedMethodParameter) {
            override fun action(): Boolean {
                if (tokenMemberMethodType.name != null) {
//                    stringAnalyzer1.construct.currentMethod.ofClass = Variable()
//                    stringAnalyzer1.construct.currentMethod.ofClass.classStr = tokenMemberMethodType.name
//                    stringAnalyzer1.construct.currentMethod.name = tokenMemberMethodName.name
                }
                return true
            }
        }

        class ActionParamName1 : Action(tokenNameMethodParameter1) {
            override fun action(): Boolean {
                if (tokenNameMethodParameter1.name != null) {
                    val parameterList = stringAnalyzer1.construct.currentMethod.parameterList
                    parameterList[parameterList.size - 1].name = tokenNameMethodParameter1.name
                }
                return true
            }
        }

        class ActionParamName2 : Action(tokenNameMethodParameter2) {
            override fun action(): Boolean {
                if (tokenNameMethodParameter2.name != null) {
                    val parameterList = stringAnalyzer1.construct.currentMethod.parameterList
                    parameterList[parameterList.size - 1].name = tokenNameMethodParameter2.name
                }
                return true
            }
        }


        class ActionParamType1 : Action(tokenQualifiedNameMethodParameter1) {
            override fun action(): Boolean {
                println("tokenQualifiedNameMethodParameter: " + tokenQualifiedNameMethodParameter1.name)
                if (tokenQualifiedNameMethodParameter1.name != null) {
                    val parameterList = stringAnalyzer1.construct.currentMethod.parameterList
                    parameterList.add(Variable())
                    parameterList[parameterList.size - 1].classStr = tokenQualifiedNameMethodParameter1.name
                }
                return true
            }
        }

        class ActionParamType2 : Action(tokenQualifiedNameMethodParameter2) {
            override fun action(): Boolean {
                println("tokenQualifiedNameMethodParameter: " + tokenQualifiedNameMethodParameter2.name)
                if (tokenQualifiedNameMethodParameter2.name != null) {
                    val parameterList = stringAnalyzer1.construct.currentMethod.parameterList
                    parameterList.add(Variable())
                    parameterList[parameterList.size - 1].classStr = tokenQualifiedNameMethodParameter2.name
                }
                return true
            }
        }


        tokenMemberMethodType.addToken(tokenMemberMethodName)
        tokenMemberMethodName.addToken(tokenOpenParenthesizedMethodParameter)
        // Instructions
        val tokenMultiMembersInstructions =
            stringAnalyzer1.MultiTokenExclusiveXor(
                tokenMemberMethodVarType1,
                tokenMemberMethodVarType2,
                tokenMemberMethodExpression3,
            )

        val tokenMemberMethodVarType = stringAnalyzer1.TokenQualifiedName()
        val tokenMemberMethodVarName = stringAnalyzer1.TokenName()
        val tokenMemberMethodEquals = stringAnalyzer1.TokenEquals()
        val tokenMemberMethodExpression = stringAnalyzer1.TokenExpression1()
        val tokenMemberMethodSemicolon = stringAnalyzer1.TokenSemiColon()

        // End of Instructions

        val tokenCloseBracketMethod = stringAnalyzer1.TokenCloseBracket()
        tokenOpenBracketMethod.addToken(tokenMultiMembersInstructions)
        tokenMultiMembersInstructions.addToken(tokenCloseBracketMethod)

        class ActionPushMethod : Action(tokenCloseBracketMethod) {
            override fun action(): Boolean {
                stringAnalyzer1.construct.currentClass.methodList[stringAnalyzer1.construct.methodMembers.size - 1] =
                    stringAnalyzer1.construct.currentMethod
                stringAnalyzer1.construct.currentMethod = Method()
                return true
            }
        }

        tokenPackage.addToken(tokenPackageName)
        tokenPackageName.addToken(tokenPackageSemicolon)
        tokenPackageSemicolon.addToken(tokenClass)
        tokenClass.addToken(tokenClassName)
        tokenClassName.addToken(tokenOpenBracket)
        val multiTokenOptional = stringAnalyzer1.MultiTokenExclusiveXor(
            tokenMemberMethodType, tokenMemberVar
        )
        tokenOpenBracket.addToken(multiTokenOptional)
        multiTokenOptional.addToken(tokenCloseBracketClass)
        class ActionCloseBracketClass : Action(tokenCloseBracketClass) {
            override fun action(): Boolean {
                if (tokenCloseBracketClass.isSuccessful) {
                    stringAnalyzer1.construct.classes[stringAnalyzer1.construct.classes.size - 1] =
                        stringAnalyzer1.construct.currentClass
                    stringAnalyzer1.construct.currentClass = Class()
                }
                return true
            }
        }

        val token = tokenPackage

        //token.addToken(tokenClass)

//        val input = "package com.example;\nclass Graph {\n}\n"
        val input =
            "package one.empty3;\n\n" +
                    "class Number {\n" +
                    "\tDouble ar;\n" +
                    "\tdouble func1(Double a, Double b, Double c) {\n" +
                    "\t\tDouble d = c+b/a;\n" +
                    "\t\treturn d;\n" +
                    "\t}\n" +
                    "\tDouble br;\n" +
                    "\tdouble func2(Double a, Double b, Double c) {\n" +
                    "\t\tDouble d = c+b*a;\n" +
                    "\t\treturn d;\n" +
                    "\t}\n" +
                    "}\n"

        var parse = -1
        try {
            parse = stringAnalyzer1.parse(token, input)
//            parse = stringAnalyzer1.mPosition
        } catch (ex: RuntimeException) {
            if (parse >= input.length) {
                Assert.assertTrue(true)
            } else {
                Assert.assertTrue(false)
            }
        }
        println("TestStringAnalyzer1")
        println("parse = " + parse + "/" + input.length)
        println("isSuccessful = " + token.isSuccessful + " tokenClass : " + tokenClassName.isSuccessful)
        println("Name : " + tokenPackageName.name)
        println("Method name : " + tokenMemberMethodName.name)
        println(stringAnalyzer1.construct)
        if (parse >= input.length)
            println("")
        else
            println(input.substring(stringAnalyzer1.mPosition))

        if (parse >= input.length) {
            Assert.assertTrue(true)
            return
        } else {
            Assert.assertTrue(false)
        }

    }

}