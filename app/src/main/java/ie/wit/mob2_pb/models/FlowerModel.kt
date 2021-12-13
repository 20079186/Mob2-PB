package ie.wit.mob2_pb.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize



@Parcelize
data class FlowerModel (
    var id: Long = 0,
    var name: String = "",
    var family: String = "",
    var season: String = "",
    var description: String = "",
    var care: String = "",
    var image: Uri = Uri.EMPTY
): Parcelable

