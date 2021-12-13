package ie.wit.mob2_pb.main

import android.app.Application
import ie.wit.mob2_pb.models.FlowerJSONStore
import ie.wit.mob2_pb.models.FlowerStore
import timber.log.Timber

class MainApp(): Application() {

    lateinit var flowers: FlowerStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        flowers = FlowerJSONStore(applicationContext)
    }
}