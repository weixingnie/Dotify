package edu.uw.wn5.dotify

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.wn5.dotify.databinding.MusicListBinding


/*
 *  This method help us to recreate and generate multiple items in recycle view.
 *  In this method, we are going to establish the connection with SongListActivity ktlin class.
 *  Here, we know its size. Then, we go through the whole list from the ericchee's api to
 *  provide cover, title and name.
 *  It also have reshuffle and clickable feature.
 */
class SongListAdapter(private var listOfMusic: List<Song>):RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClickListener:(song: Song) -> Unit = {_ -> }

    /*
     * Make the connection with the correct class.
     * Generate through this adapter to fill the all the view page.
     */
    class SongViewHolder(val binding: MusicListBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = MusicListBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    /*
     *  Cover, Name, Title
     *  Allow each song to be clickable
     */
    override fun getItemCount(): Int = listOfMusic.size

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfMusic[position]
        with(holder.binding) {
            ivSongCover.setImageResource(song.smallImageID)
            ivSongTitle.text = song.title
            ivArtistName.text = song.artist
            holder.binding.root.setOnClickListener{
                onSongClickListener(song)
            }
        }
    }

    /*
     *  Reorganize all the songs
     *  Inform the change
     */
    fun updateSong(updatedSongList: List<Song>) {
        listOfMusic = updatedSongList
        notifyDataSetChanged()
    }
}