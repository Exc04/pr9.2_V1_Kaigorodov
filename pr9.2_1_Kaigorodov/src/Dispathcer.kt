package management

import entities.*

//Класс Диспетчер. Управляет водителями, автомобилями и заявками.
class Dispatcher(val name: String) {

    private val drivers = mutableListOf<Driver>()
    private val cars = mutableListOf<Car>()
    private val trips = mutableListOf<Trip>()

    //Добавление водителя в базу.
    fun addDriver(driver: Driver) {
        drivers.add(driver)
        println("Диспетчер $name добавил водителя ${driver.name}")
    }

    //Добавление автомобиля в автопарк.
    fun addCar(car: Car) {
        cars.add(car)
        println("Диспетчер $name добавил авто ${car.licensePlate}")
    }

    //Распределение заявки на рейс.
    //Ищет свободного водителя и свободный автомобиль.
    fun assignTrip(trip: Trip) {
        val availableDriver = drivers.find { !it.isSuspended && it.currentCar == null }
        val availableCar = cars.find { it.isAvailable }

        if (availableDriver != null && availableCar != null) {
            availableDriver.currentCar = availableCar
            availableCar.isAvailable = false
            trip.assignedDriver = availableDriver
            trip.assignedCar = availableCar
            trips.add(trip)

            println("Диспетчер $name назначил рейс #${trip.id} водителю ${availableDriver.name} на авто ${availableCar.licensePlate}")
        } else {
            println("Диспетчер $name: Нет свободного транспорта или водителей для рейса #${trip.id}")
        }
    }

    //Обработка заявки на ремонт.
    fun handleRepairRequest(request: Request) {
        println("Диспетчер $name обрабатывает заявку от ${request.driver.name}")
        // Логика отправки в сервис
        request.isResolved = true
        request.car?.condition = "В ремонте"
        request.car?.isAvailable = false
    }

    //Отстранение водителя от работы.
    fun suspendDriver(driver: Driver) {
        driver.isSuspended = true
        // Освобождаем автомобиль, если он был за водителем
        driver.currentCar?.isAvailable = true
        driver.currentCar = null
        println("Диспетчер $name отстранил водителя ${driver.name} от работы.")
    }

    fun showFleetStatus() {
        println("\n--- Состояние автобазы ---")
        drivers.forEach { it.displayInfo() }
        cars.forEach { it.displayInfo() }
        println("---------------------------\n")
    }
}