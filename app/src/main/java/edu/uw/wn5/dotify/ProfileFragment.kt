package edu.uw.wn5.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import edu.uw.wn5.dotify.Data.DataRepository
import edu.uw.wn5.dotify.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import coil.load



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 * Changing fragments' elements in reponse to the Json file.
 * IT would change the profile picture
 * It would also change the username, and display first+last name
 */
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val myAPP: DotifyApplication by lazy { requireActivity().application as DotifyApplication }
    private val dataRepository by lazy {myAPP.dataRepository  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        with(binding) {
            swipe.setOnRefreshListener {
                lifecycleScope.launch {
                    kotlin.runCatching {
                        val allData = dataRepository.fetchUser()
                        binding.rvProfilePicture.load(allData.profilePicURL)
                        binding.rvUserName.text = allData.username
                        binding.rvIceCream.text =
                            "Full Name: " + allData.firstName + " " + allData.lastName
                        rvProfilePicture.visibility = View.VISIBLE
                        rvUserName.visibility = View.VISIBLE
                        rvIceCream.visibility = View.VISIBLE
                        rvError.visibility = View.GONE
                    }.onFailure {
                        binding.rvProfilePicture.visibility = View.GONE
                        binding.rvEmail.visibility = View.GONE
                        binding.rvIceCream.visibility = View.GONE
                        binding.rvUserName.visibility = View.GONE
                        binding.rvError.text = it.message
                        binding.rvError.visibility = View.VISIBLE
                    }
                }
                swipe.isRefreshing = false
        }
    }
        return binding.root
}

}