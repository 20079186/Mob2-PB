package ie.wit.mob2_pb.models

interface FlowerStore {

    fun findF(id: Long) : FlowerModel?
    fun findAll(): List<FlowerModel>
    fun create(flower: FlowerModel)
    fun update(flower: FlowerModel)
    fun delete(flower: FlowerModel)
}