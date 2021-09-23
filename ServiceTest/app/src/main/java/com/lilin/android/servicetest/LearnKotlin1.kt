package com.lilin.android.servicetest

/**
 * 创建日期：2021/9/23 16:04
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */

/**
 * java中不可能允许
 * 泛型协变：假如定义了一个MyClass<T>的泛型类，其中A是B的子类型，
 * 同时MyClass<A>又是MyClass<B>的子类型，
 * 那么我们就可以称MyClass在T这个泛型上是协变的
 *
 *
 * 设置值属于in位置
 * 返回值属于out位置
 */
fun main(){
    xieBian()
    xieBian1()
}


fun xieBian1() {
    val stu = Student("ZhangSan",20)
    val data = SimpleData1<Student>(stu)
    handleMyData(data)
}

/**
 * 泛型协变后
 * SimpleData1<Student> 变成了SimpleData1<Person>的子类
 *
 */
fun handleMyData(data: SimpleData1<Person>){
    var personData = data.get()
}

/**
 * Student被替换为Teacher 会存在类型转换错误
 */
fun xieBian() {
    val stu = Student("ZhangSan",20)
    val data = SimpleData<Student>()
    data.setValue(stu)
//    handleSimpleData(data) //这里编译不通过 类型不匹配
    val stuData = data.get()
}





/**
 * 我们调用SimpleData<Student>的get()方法来获取它内部封装的Student数据，
 * 可现在SimpleData<Student>中实际包含的却是一个Teacher的实例，那么此时必然会产生类型转换异常
 * java为了杜绝安全隐患，java不允许这样传递参数，kotlin也不允许，List<Student>和List<Person>是不同类型
 *
 * 你会发现问题发生的主要原因是我们在handleSimpleData()方法中向SimpleData<Person>里设置了一个Teacher的实例。
 * 如果SimpleData在泛型T上是只读的话，肯定就没有类型转换的安全隐患了
 */
fun handleSimpleData(data: SimpleData<Person>){
    val tea = Teacher("LiSi",30)
    data.setValue(tea)
}