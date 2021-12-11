package ie.wit.mob2_pb.models

import android.net.Uri


data class FlowerModel (
    var name: String = "",
    var family: String = "",
    var season: String = "",
    var description: String = "",
    var care: String = "",
    var image: Uri = Uri.EMPTY
)