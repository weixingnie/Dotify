package edu.uw.wn5.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.wn5.dotify.databinding.FragmentStatisticsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment() {


    private lateinit var binding: FragmentStatisticsBinding
    private val safeArgs:  StatisticsFragmentArgs by navArgs()

    /*
     * we change images and playcount according to the previous pressed song/selected song.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatisticsBinding.inflate(inflater)
        val songName = safeArgs.songName
        val Count = safeArgs.playCount

        with(binding) {
            rvPlayCount.text = Count.toString()
            rvStatCover.setImageResource(songName.largeImageID)
        }
        return binding.root
    }
}