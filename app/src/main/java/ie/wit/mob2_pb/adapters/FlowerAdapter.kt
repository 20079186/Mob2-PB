package ie.wit.mob2_pb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.wit.mob2_pb.databinding.CardFlowerBinding
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
        val binding = CardFlowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val flower = flowers[holder.adapterPosition]
        holder.bind(flower, listener)
    }

    override fun getItemCount(): Int = flowers.size

    class MainHolder(private val binding: CardFlowerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flower: FlowerModel, listener: FlowerListener) {
            binding.flowerTitle.text = flower.name
            binding.season.text = flower.season
            Picasso.get().load(flower.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onFlowerClick(flower) }
        }
    }
}