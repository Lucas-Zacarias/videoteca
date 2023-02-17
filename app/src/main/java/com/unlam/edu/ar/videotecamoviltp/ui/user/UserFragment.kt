package com.unlam.edu.ar.videotecamoviltp.ui.user

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.unlam.edu.ar.videotecamoviltp.databinding.FragmentUserBinding
import com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences.Preferences.Companion.getSharedPreferenceUserId
import com.unlam.edu.ar.videotecamoviltp.ui.activities.LogInActivity
import org.koin.android.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPref: SharedPreferences
    private val userViewModel: UserViewModel by viewModel()
    private lateinit var userProfileImage: ImageView
    private lateinit var userName: TextView
    private lateinit var closeButton: ImageButton
    private lateinit var signOffButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSharedPref()
        getViews()
        setUpObserver()
        setListeners()
    }

    private fun setSharedPref() {
        sharedPref = requireContext().getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
    }

    private fun getViews(){
        userProfileImage = binding.ivUserImage
        userName = binding.tvUserName
        closeButton = binding.ibClose
        signOffButton = binding.bSignOff
    }

    private fun setUpObserver(){
        userViewModel.getUserNameById(getCurrentUserId())

        userViewModel.userNameLiveData.observe(viewLifecycleOwner){ name ->
            userName.text = name
        }
    }

    private fun setListeners(){
        closeButton.setOnClickListener {
            closeUserFragment()
        }

        signOffButton.setOnClickListener {
            signOff()
        }
    }

    private fun closeUserFragment() {
        parentFragmentManager
            .beginTransaction()
            .remove(this)
            .commit()
    }

    private fun signOff(){
        sharedPref.edit().clear().apply()

        requireActivity().finish()

        val intent = Intent(activity, LogInActivity::class.java)
        startActivity(intent)
    }

    private fun getCurrentUserId(): Int{
        return getSharedPreferenceUserId(sharedPref)
    }
}