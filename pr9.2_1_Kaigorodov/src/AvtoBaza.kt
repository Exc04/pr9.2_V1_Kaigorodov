import entities.*
import management.Dispatcher


typealias BaseDriver = Driver
typealias ExtendedDriver = ExpeditionDriver

typealias BaseCar = Car
typealias ExtendedCar = Truck

typealias BaseRequest = Request
typealias ExtendedRequest = UrgentRequest

typealias BaseTrip = Trip
typealias ExtendedTrip = LongDistanceTrip

fun main() {
    val dispatcher = Dispatcher("Иван петрович")

    val driver1: BaseDriver = Driver("Сидоров",15)
    val car1: BaseCar = Car("A121BM","AUDI Q7")

    // Объекты-наследники через псевдонимы
    val driver2: ExtendedDriver = ExpeditionDriver("Петров", 8, true)
    val car2: ExtendedCar = Truck("В456ЕН", "Камаз", 20.0)

    // Добавляем их диспетчеру
    dispatcher.addDriver(driver1)
    dispatcher.addDriver(driver2)
    dispatcher.addCar(car1)
    dispatcher.addCar(car2)

    dispatcher.showFleetStatus()

    // 3. Создаем рейсы
    val trip1: BaseTrip = Trip(101, "Москва", 750.0)
    val trip2: ExtendedTrip = LongDistanceTrip(102, "Владивосток", 9200.0, true)

    // 4. Диспетчер назначает рейсы
    dispatcher.assignTrip(trip1)  // Сидоров поедет на Ладе
    dispatcher.assignTrip(trip2)  // Петров поедет на Камазе

    // 5. Водитель делает отметку о выполнении рейса
    driver1.completeTrip(trip1, "Требуется замена масла")
    trip1.isCompleted = true

    // 6. Водитель делает заявку на ремонт (после рейса)
    val repairRequest: BaseRequest = driver1.requestRepair()
    dispatcher.handleRepairRequest(repairRequest)

    // 7. Водитель-экспедитор проверяет документы
    (driver2 as ExpeditionDriver).checkDocuments()
    driver2.completeTrip(trip2, "Царапина на бампере")
    trip2.isCompleted = true

    // 8. Диспетчер отстраняет водителя
    dispatcher.suspendDriver(driver1)

    // Итоговый статус
    dispatcher.showFleetStatus()
    repairRequest.displayInfo()
    trip2.displayInfo()

















}