package entities

//Базовый класс, представляющий Заявку на ремонт.
open class Request(
    val driver: Driver,
    val car: Car?,
    val description: String
) {
    var isResolved: Boolean = false

    open fun displayInfo() {
        println("Заявка от ${driver.name}: ${car?.model} [$description]. Решена: $isResolved")
    }
}

//Класс-наследник Заявки (Срочная заявка).
class UrgentRequest(
    driver: Driver,
    car: Car?,
    description: String,
    val priority: String
) : Request(driver, car, description) {

    fun escalate() {
        println("СРОЧНО! Заявка от ${driver.name} с приоритетом $priority требует немедленного внимания!")
    }

    override fun displayInfo() {
        println("СРОЧНАЯ заявка! Приоритет: $priority, ${driver.name} -> ${car?.model} [$description]")
    }
}