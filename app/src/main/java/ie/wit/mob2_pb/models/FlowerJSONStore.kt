package ie.wit.mob2_pb.models
import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.wit.mob2_pb.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

val JSON_FILE = "flowers.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting().registerTypeAdapter(Uri::class.java, FlowerJSONStore.UriParser()).create()
val listType = object : TypeToken<ArrayList<FlowerModel>>(){}.type

class FlowerJSONStore(private val context: Context) : FlowerStore {

    var flowers = mutableListOf<FlowerModel>()
    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }


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
        serialize()
    }

    override fun findF(id: Long): FlowerModel? {
        var foundFlower: FlowerModel? = flowers.find { p -> p.id == id }
        return foundFlower
    }

    override fun update(flower: FlowerModel) {
        var foundFlower = findF(flower.id)
        if (foundFlower != null) {
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
        flowers.forEach { Timber.i("$it") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(flowers, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        flowers = gsonBuilder.fromJson(jsonString, listType)
    }

    class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): Uri {
            return Uri.parse(json?.asString)
        }

        override fun serialize(
            src: Uri?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            return JsonPrimitive(src.toString())
        }

    }
}