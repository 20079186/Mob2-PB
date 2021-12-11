package ie.wit.mob2_pb.models

import ie.wit.mob2_pb.activites.AddFlower

interface FlowerStore {
    fun findall(): List<FlowerModel>
    fun create(flower: FlowerModel)
    fun update(flower: FlowerModel)
    fun delete(flower: FlowerModel)
}