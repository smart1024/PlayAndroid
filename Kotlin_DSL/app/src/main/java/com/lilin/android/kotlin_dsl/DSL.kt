package com.lilin.android.kotlin_dsl

import java.lang.StringBuilder

/**
 * 创建日期：2021/9/28 16:24
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_dsl
 * 类说明：
 */


/**
 * DSL:特定领域语言
 * gradle就是基于groovy的构建工具，build.gradle就是借助groovy提供的DSL功能
 *
 * 借助kotlin也能实现DSL功能
 */
class Dependency {
    val libraries = ArrayList<String>()
    fun implementation(lib:String){
        libraries.add(lib)
    }
}

fun dependencies(block:Dependency.()->Unit):List<String>{
    val dependency = Dependency()
    dependency.block()
    return dependency.libraries
}

class Td{
    var content = ""
    fun html() = "\n\t\t<td>$content</td>"
}

class Tr{
    private val children = ArrayList<Td>()
    fun td(block: Td.() -> String){
        val td = Td()
        td.content =td.block()
        children.add(td)
    }

    fun html():String{
        val builder = StringBuilder()
        builder.append("\n\t<tr>")
        for (child in children){
            builder.append(child.html())
        }
        builder.append("\n\t</tr>")
        return builder.toString()
    }
}

class Table{
    private val children = ArrayList<Tr>()

    fun tr(block: Tr.() -> Unit){
        val tr = Tr()
        tr.block()
        children.add(tr)
    }

    fun html():String{
        val builder = StringBuilder()
        builder.append("<table>")
        for (child in children){
            builder.append(child.html())
        }
        builder.append("\n</table>")
        return builder.toString()
    }
}

/**
 * 进一步优化表格的创建
 * ClassName.表示block函数在哪个类中定义的，传入的lambda表达式就拥有该类的上下文
 * 此例中拥有Table类的上下文
 */
fun table(block: Table.() -> Unit):String{
    val table = Table()
    table.block()
    return table.html()
}