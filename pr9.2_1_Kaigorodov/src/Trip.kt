package entities

//Базовый класс, представляющий Рейс (Заявку на поездку).
open class Trip(
    val id: Int,
    val destination: String,
    val distance: Double
) {
    var isCompleted: Boolean = false
    var assignedDriver: Driver? = null
    var assignedCar: Car? = null

    open fun displayInfo() {
        println("Рейс #$id: $destination, ${distance}км, Статус: ${if (isCompleted) "Завершен" else "Назначен"}")
    }
}

//Класс-наследник Рейса (Междугородный рейс).
class LongDistanceTrip(
    id: Int,
    destination: String,
    distance: Double,
    val requiresOvernightStop: Boolean
) : Trip(id, destination, distance) {

    fun planOvernight() {
        if (requiresOvernightStop) {
            println("Планируется ночевка в гостинице для рейса #$id")
        }
    }

    override fun displayInfo() {
        println("Рейс #$id (Междугородний): $destination, ${distance}км, Ночевка: $requiresOvernightStop")
    }
}