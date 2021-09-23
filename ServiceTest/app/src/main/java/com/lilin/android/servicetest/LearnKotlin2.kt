package com.lilin.android.servicetest

/**
 * 创建日期：2021/9/23 18:08
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */
/**
 * 泛型的协变:子类还是子类
 * 如果A是B的子类，同时MyClass<A>又是MyClass<B>的子类
 *
 * 泛型的逆变：父类变子类
 *  如果A是B的子类，同时MyClass<B>又是MyClass<A>的子类
 *  逆变的规则好像挺奇怪的，原本A是B的子类型，怎么MyClass<B>能反过来成为MyClass<A>的子类型了呢
 */
fun main(){
    val trans = object :Transformer<Person>{
        override fun transform(t: Person): String {
            return "${t.name} ${t.age}"
        }
    }
    handleTransform(trans) //这行代码会报错
}

fun handleTransform(trans: Transformer<Student>) {
    val student = Student("Tom",19)
    val result = trans.transform(student)
}
