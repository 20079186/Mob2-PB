package ie.wit.mob2_pb.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import ie.wit.mob2_pb.R

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {

    var chooseImage = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseImage.type = "image/*"
    chooseImage = Intent.createChooser(chooseImage, R.string.pick_flower_image.toString())
    intentLauncher.launch(chooseImage)
}