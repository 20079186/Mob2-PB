package ie.wit.mob2_pb.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.mob2_pb.models.FlowerModel


interface FlowerListener {
    fun onFlowerClick(flower: FlowerModel)
}

class FlowerAdapter constructor(
    private var flowers: List<FlowerModel>,
    private val listener: FlowerListener
):

        RecyclerView.Adapter<FlowerAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = Card
    }

        }



{
}