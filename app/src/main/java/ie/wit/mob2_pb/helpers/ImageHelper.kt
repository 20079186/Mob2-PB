package ie.wit.mob2_pb.helpers

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

fun showImagePicker(intentLauncher : ActivityResultLauncher<Intent>) {

    var chooseImage = Intent(Intent.ACTION_OPEN_DOCUMENT)
    chooseImage.type = "image/*"
    chooseImage = Intent.createChooser(chooseImage, "Select Image".toString())
    intentLauncher.launch(chooseImage)
}