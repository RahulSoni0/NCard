package com.rahulsoni0.ncard.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.transition.MaterialElevationScale
import com.rahulsoni0.ncard.R
import com.rahulsoni0.ncard.activities.MainActivity
import com.rahulsoni0.ncard.databinding.FragmentNoteBinding
import com.rahulsoni0.ncard.utils.hideKeyboard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoteFragment : Fragment(R.layout.fragment_note) {

    private lateinit var noteBinding: FragmentNoteBinding
//    private val cardActivityViewModel: CardActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        exitTransition = MaterialElevationScale(false).apply {
            duration = 350
        }
        enterTransition = MaterialElevationScale(true).apply {
            duration = 350
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteBinding = FragmentNoteBinding.bind(view)
        val activity = activity as MainActivity
        val navController = Navigation.findNavController(view)
        requireView().hideKeyboard()
        CoroutineScope(Dispatchers.Main).launch {
            delay(10)
//            activity.window.statusBarColor = Color.WHITE
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = Color.parseColor("#9E9D9D")
        }

        noteBinding.addCardFab.setOnClickListener {
            noteBinding.appBarLayout.visibility = View.INVISIBLE
            navController.navigate(NoteFragmentDirections.actionNoteFragmentToManagerFragment())
        }
        noteBinding.innerFab.setOnClickListener {
            noteBinding.appBarLayout.visibility = View.INVISIBLE
            navController.navigate(NoteFragmentDirections.actionNoteFragmentToManagerFragment())
        }
        noteBinding.tvFab.setOnClickListener {
            noteBinding.appBarLayout.visibility = View.INVISIBLE
            navController.navigate(NoteFragmentDirections.actionNoteFragmentToManagerFragment())
        }


    }


}