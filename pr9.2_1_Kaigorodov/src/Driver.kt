package entities

//Базовый класс, представляющий Водителя.
open class Driver(
    val name: String,
    val experience: Int
) {
    var isSuspended: Boolean = false // Отстранен ли водитель от работы
    var currentCar: Car? = null      // Автомобиль, закрепленный за водителем

    //Водитель делает заявку на ремонт текущего автомобиля.
    fun requestRepair(): Request {
        println("Водитель $name подает заявку на ремонт.")
        return Request(this, currentCar, "Неисправность")
    }

    //Водитель выполняет рейс и отмечает его выполнение.
    fun completeTrip(trip: Trip, carCondition: String) {
        println("Водитель $name завершил рейс ${trip.id}. Состояние авто: $carCondition")
        currentCar?.condition = carCondition
    }

    open fun displayInfo() {
        println("Водитель: $name, Стаж: $experience, Отстранен: $isSuspended")
    }
}

// Класс-наследник Водителя (Водитель-экспедитор).
class ExpeditionDriver(
    name: String,
    experience: Int,
    val documents: Boolean
) : Driver(name, experience) {

    fun checkDocuments() {
        println("Водитель-экспедитор $name проверяет документы. Наличие: $documents")
    }

    override fun displayInfo() {
        println("Водитель-экспедитор: $name, Стаж: $experience, Документы: $documents")
    }
}