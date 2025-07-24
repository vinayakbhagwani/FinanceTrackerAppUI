package com.vinayak.mydemoapp

import android.graphics.Color
import kotlin.reflect.KProperty

fun main() {

    val employeeList = mutableListOf<Employee>()

    val list = listOf(
        Employee(
            name = "ABC",
            doj = "23/06/2019",
            designation = EmployeeType.ASSOCIATE_ENGINEER
        ),
        Employee(
            name = "AB",
            doj = "23/06/2019",
            designation = EmployeeType.ENGINEER
        ),
        Employee(
            name = "ABC35678",
            doj = "23/06/2019",
            designation = EmployeeType.MANAGER
        ),
        Employee(
            name = "ABC4567890",
            doj = "23/06/2019",
            designation = EmployeeType.ENGINEER
        )
    )

    employeeList.addAll(list)

    val filteredList = employeeList.filter { it.designation == EmployeeType.ENGINEER }
    employeeList.map { emp ->
        val coordinate = emp.geoFence.flatMap{
            it
        }
        println(coordinate)
    }
    println(
        employeeList
            .filter { it.name.length > 4 }
            .map { it.name }
    )

    println(filteredList)

    println(consecutiveSeries(4))

    println(sumOfOddNumbers(5))


    log("Hello")
    log(123)
    log(3.14)

    println("-------------------------")

    customLogger("Hello")
    customLogger(123)

    checkName("Rohan") { isIdealName ->
        println(isIdealName)
    }

    println(SingletonDemo.instance)
    println(SingletonDemo.instance)

//    val book = Book()
//    book.content = "New content"
//
//    book.content = "ABC"
//    book.content = ""
//    println(book.content)

    val rawText = "MyBook"
    val book = Book(rawText)
    println("Book is created")
    book.analyse()

    println(Example().p)

//    println(FactoryDesignPattern())

//    println(employeeList)
}

data class Employee(
    val name: String,
    val doj: String,
    val designation: EmployeeType,
    val geoFence: List<List<String>> = listOf(listOf("200.0"))
)

enum class EmployeeType(val badgeColor: Int = Color.WHITE) {
    ASSOCIATE_ENGINEER(Color.BLUE),
    ENGINEER(Color.GREEN),
    MANAGER(Color.RED),
    SENIOR_MANAGER(Color.CYAN)
}

fun consecutiveSeries(n: Int): Int {
    if(n == 1) return 1
    return n + consecutiveSeries(n-1)
}

fun sumOfOddNumbers(n: Int): Int {
    if (n == 1) return 1  // Base case: first odd number is 1
    return n + sumOfOddNumbers(n - 1)
}

inline fun <reified T> log(message: T) {
    when(T::class) {
        String::class -> println("Message: "+message)
        Int::class -> println("Message: "+message.toString())
        else -> println("Message: "+message.toString())
    }
}

//interface Logger {
//    fun <T> log(message: T)
//}

typealias Logger = (Any) -> Unit

//class CustomLogger: Logger {
//    override fun <T> log(message: T) {
//        println(message.toString())
//    }
//}

val customLogger: Logger = {message -> println(message.toString()) }

inline fun checkName(name: String, result: (Boolean) -> Unit) {
    result(name == "Rohan")
}

class SingletonDemo private constructor() {
    companion object {
        val instance: SingletonDemo by lazy { SingletonDemo() }
    }
}

interface Notification

class PersonalChatNotification : Notification {

}

class GroupChatNotification : Notification {

}


object FactoryDesignPattern {

    fun <T: NotificationType> T.createNotification() : Notification {
        return when(this) {
            NotificationType.PERSONAL_CHAT -> {
                PersonalChatNotification()
            }
            NotificationType.GROUP_CHAT -> {
                GroupChatNotification()
            }
            else -> {
                PersonalChatNotification()
            }
        }
    }
}

enum class NotificationType {
    PERSONAL_CHAT,
    GROUP_CHAT
}

// Delegate to other member
// By Observable
//class Book {
////    var content : String by observable("") { property, oldValue, newValue ->
////        println("Book changed")
////    }
//
//    var content : String by vetoable("") { property, oldValue, newValue ->
//        !newValue.isNullOrEmpty()
//    }
//}

// By Lazy
class Book(private val rawText: String) {

    private val analyser: Analyser by lazy { Analyser() }

    fun analyse() {
        analyser.doSomething()
    }
}

class Analyser {
    init {
        println("Init Analyser class")
    }

    fun doSomething() {
        println("DoSomething")
    }
}

// Idiomatic logging access
//fun  R.logger(): Lazy {
//    return lazy { Logger.getLogger(unwrapCompanionClass(this.javaClass).name) }
//}
//
//class Something {
//    val LOG by logger()
//
//    fun foo() {
//        LOG.info("Hello from Something")
//    }
//}

class Example {
    var p: String by Delegate()
}

class Delegate {
    var cached = "Hello from delegate"

    operator fun getValue(example: Any, property: KProperty<*>): String {
        return cached
    }

    operator fun setValue(example: Any, property: KProperty<*>, s: String) {
        cached = s
    }
}