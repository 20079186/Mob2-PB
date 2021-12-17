package ie.wit.mob2_pb.models
import timber.log.Timber.i
import mu.KotlinLogging

val logger = KotlinLogging.logger {}
var fid = 0L

internal fun getId(): Long {
    return fid++
}

class FlowerMStore: FlowerStore {

    val flowers = ArrayList<FlowerModel>()

    override fun findAll(): List<FlowerModel> {
        return findAll()
    }

    override fun findF(id: Long): FlowerModel? {
        var foundFlower: FlowerModel? = flowers.find { p -> p.id == id }
        return foundFlower
    }


    override fun create(flower: FlowerModel) {
        flower.id = getId()
        flowers.add(flower)
        logAll()
    }

    override fun update(flower: FlowerModel) {
        var foundFlower = findF(flower.id)
        if(foundFlower !=null){
            foundFlower.name = flower.name
            foundFlower.family = flower.family
            foundFlower.season = flower.season
            foundFlower.description = flower.description
            foundFlower.care = flower.care
            foundFlower.image =flower.image
            logAll()
        }
    }

    override fun delete(flower: FlowerModel) {
        flowers.remove(flower)
    }


    private fun logAll() {
        flowers.forEach { i("$it") }
    }


}