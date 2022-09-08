package com.escm.recyclerview

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.escm.recyclerview.entities.RSSObject
import com.escm.recyclerview.utilities.Utilidades

class FeedAdapter : RecyclerView.Adapter<FeedViewHolder> {

    lateinit var rssObject: RSSObject
    lateinit var mContext: Context
    lateinit var inflater: LayoutInflater

    lateinit var mediaPlayer: MediaPlayer
    var enPausa: Boolean = false
    var posicionClic: Int = 0
    lateinit var utilidades: Utilidades


    constructor(rssObject: RSSObject, mContext: Context) : super() {
        this.rssObject = rssObject
        this.mContext = mContext
        this.inflater = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        var itemView: View = inflater.inflate(R.layout.row,parent,false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val imageButton = arrayOfNulls<ImageButton>(1000)

        holder.txtTitle.setText(rssObject.items?.get(position)?.title)
        holder.txtContent.setText(
            rssObject.items?.get(position)?.content?.replace("</p>", "")?.replace("<p>", "")?.
            replace("<br>", "")?.replace("</br>", "")?.replace("<a>", "")?.replace("</a>", "")
        )
        holder.txtUrlMp3.setText(rssObject.items?.get(position)?.enclosure?.link)

        var seconds: Int? = rssObject.items?.get(position)?.enclosure?.duration
        val hours = seconds?.div(3600)
        val minutes = seconds?.mod(3600)?.div(60)
        seconds = seconds?.mod(60)

        val duracion: String = hours?.let { Utilidades.twoDigitString(it) } + " : " + minutes?.let { Utilidades.twoDigitString(it) } + " : " + seconds?.let { Utilidades.twoDigitString(it) }
        holder.txtPubDate.text = "Duración: $duracion"

        holder.setItemClickListener(object : ItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                val toast = Toast.makeText(mContext,"Implementar llamado a otro fragmento y paso de parámetros",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getItemCount(): Int {
        return rssObject.items!!.size
    }


}

class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener, View.OnLongClickListener {
    var txtTitle: TextView
    var txtPubDate: TextView
    var txtContent: TextView
    var txtUrlMp3: TextView
    private var itemClickListener: ItemClickListener? = null

    init {
        txtTitle = itemView.findViewById<View>(R.id.txtTitle) as TextView
        txtPubDate = itemView.findViewById<View>(R.id.txtPubDate) as TextView
        txtContent = itemView.findViewById<View>(R.id.txtContent) as TextView
        txtUrlMp3 = itemView.findViewById<View>(R.id.txtUrlMP3) as TextView

        //Set Event
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View) {
        itemClickListener?.onClick(v, adapterPosition, false)
    }

    override fun onLongClick(v: View): Boolean {
        itemClickListener?.onClick(v, adapterPosition, true)
        return true
    }
}