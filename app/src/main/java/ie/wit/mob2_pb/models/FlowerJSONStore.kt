package ie.wit.mob2_pb.models
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ie.wit.mob2_pb.helpers.*
import timber.log.Timber
import java.util.*

val JSON_FILE = "flowers.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<FlowerModel>>(){}.type

class FlowerJSONStore(private val context: Context) : FlowerStore {

    var flowers = mutableListOf<FlowerModel>()

    fun generateRandomId(): Long {
        return Random().nextLong()
    }

    override fun findAll(): List<FlowerModel> {
        logAll()
        return flowers
    }

    override fun create(flower: FlowerModel) {
        flower.id = generateRandomId()
        flowers.add(flower)
        logAll()
    }

    override fun findF(id: Long) : FlowerModel? {
    var foundFlower: FlowerModel? = flowers.find { p -> p.id == id }
        return foundFlower
    }

    override fun update(flower: FlowerModel) {
        var foundFlower = findF(flower.id)
        if(foundFlower !=null){
            foundFlower.name = flower.name
            foundFlower.family = flower.family
            foundFlower.season = flower.season
            foundFlower.description = flower.description
            foundFlower.care = flower.care
        }
        serialize()

    }

    override fun delete(flower: FlowerModel) {
        flowers.remove(flower)
        serialize()
    }

    private fun logAll() {
        flowers.forEach{Timber.i("$it")}
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(flowers, listType)
        write(JSON_FILE, jsonString)
    }
    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        flowers = Gson().fromJson(jsonString, listType)
    }
}