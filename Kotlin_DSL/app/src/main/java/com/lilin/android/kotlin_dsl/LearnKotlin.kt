package com.lilin.android.kotlin_dsl

/**
 * 创建日期：2021/9/28 16:17
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_dsl
 * 类说明：
 */
/**
 * 学习目标是通过高阶函数的方式来实现DSL
 *
 * DSL的全称是领域特定语言（Domain Specific Language），
 * 它是编程语言赋予开发者的一种特殊能力，
 * 通过它我们可以编写出一些看似脱离其原始语法结构的代码，
 * 从而构建出一种专有的语法结构
 *
 * 相当于自定义了一种语法结构(语言本身并不支持的语法)
 */
fun main(){
    val libraries = dependencies{//dependencis高阶函数调用一次block，运行了两次implementation方法
        implementation("com.squareup.retrofit2:retrofit:2.6.1")
        implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    }
    for (lib in libraries){
        println(lib)
    }


    val table = Table()

    table.tr { //高阶函数tr调用block()，block中三次调用高阶函数td
        td {
            "Apple"
        }
        td {
            "Grape"
        }
        td {
            "Orange"
        }
    }

    table.tr {
        td { "Pear" }
        td { "Banana" }
        td { "Watermelon" }
    }
    println(table.html())

    val tablePlus = table {
        tr { //高阶函数tr调用block()，block中三次调用高阶函数td
            td {
                "Apple"
            }
            td {
                "Grape"
            }
            td {
                "Orange"
            }
        }

        tr {
            td { "Pear" }
            td { "Banana" }
            td { "Watermelon" }
        }
    }
    println(tablePlus)


    val html = table {
        repeat(2){
            tr {
                val fruits = listOf("Apple","Grape","Orange")
                for (fruit in fruits){
                    td {
                        fruit
                    }
                }
            }
        }
    }
    println(html)
}