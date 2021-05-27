package edu.uw.wn5.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uw.wn5.dotify.databinding.FragmentSettingsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    private val navController by lazy { findNavController() }
    private val safeArgs: SettingsFragmentArgs by navArgs()
    private val application by lazy { context?.applicationContext as DotifyApplication}
    private val musicSyn by lazy { application.musicSyncManager }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)
        with(binding) {
            btnProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }

            btnProfileStatistics.setOnClickListener {
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(safeArgs.playCount, safeArgs.songName))
            }

            btnAbout.setOnClickListener {
                navController.navigate(R.id.aboutFragment)
            }

            notificationButton.setOnCheckedChangeListener{ _, isChecked ->
                if (isChecked) {
                    musicSyn.syncMusicPeriodically()
                } else {
                    musicSyn.stopSync()
                }
            }

        }
        return binding.root
    }

}