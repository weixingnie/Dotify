package edu.uw.wn5.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uw.wn5.dotify.databinding.FragmentAboutBinding
import edu.uw.wn5.dotify.databinding.FragmentProfileBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    /**
     * Change version name base on the config
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAboutBinding.inflate(inflater)
        with(binding) {
            rvVersion.text = "Version: " + BuildConfig.VERSION_NAME.toString()
        }
        return binding.root
    }

}