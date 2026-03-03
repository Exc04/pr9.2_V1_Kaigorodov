package entities

import com.sun.jdi.DoubleType


//Базовый класс, автомобили
open class Car (val licensePlate: String,
val model: String) {
    var condition: String = "Исправен" //Состояние авто
    var isAvailable: Boolean = true //Доступен ли для рейсов


    open fun displayInfo() {
        println("Авто: $model[$licensePlate], Состояние: $condition, Доступен: $isAvailable")
    }
}

class Truck(
    licensePlate: String,
model: String,
val loadCapacity: Double
) : Car(licensePlate,model) {

    fun loadCargo(weight: Double) {
        if (weight  <= loadCapacity) {
            println("Груз весом $weight т загружен в грузовик $licensePlate")
        } else {
            println("превышение грузоподъемности! Максимум $loadCapacity т")
        }
    }

    override fun displayInfo() {
        println("Грузовик: $model[$licensePlate], Грузоподъемность: $loadCapacity т, Состояние: $condition")
    }
}