package com.rahulsoni0.ncard.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialContainerTransform
import com.rahulsoni0.ncard.R
import com.rahulsoni0.ncard.activities.MainActivity
import com.rahulsoni0.ncard.databinding.BottomSheetBinding
import com.rahulsoni0.ncard.databinding.FragmentSaveBinding
import com.rahulsoni0.ncard.model.CardModel
import com.rahulsoni0.ncard.utils.hideKeyboard
import com.rahulsoni0.ncard.viewModel.CardActivityViewModel
import kotlinx.android.synthetic.main.card_item_layout.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*


class ManagerFragment : Fragment(R.layout.fragment_save) {

    private lateinit var navController: NavController
    private lateinit var contentBinding: FragmentSaveBinding
    private var cards: CardModel? = null
    private var color = -1
    private val cardActivityViewModel: CardActivityViewModel by activityViewModels()
    private val currentDate = SimpleDateFormat.getInstance().format(Date())
    private val job = CoroutineScope(Dispatchers.Main)
    private val args: ManagerFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragment
            scrimColor = Color.TRANSPARENT
            duration = 300L
        }

        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentBinding = FragmentSaveBinding.bind(view)

        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity


        contentBinding.backBtn.setOnClickListener {
            requireView().hideKeyboard()
            navController.popBackStack()
        }

        contentBinding.saveCard.setOnClickListener {
            saveCard()
        }

        try {
            contentBinding.edtCardContent.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    contentBinding.bottomBar.visibility = View.VISIBLE
                    contentBinding.edtCardContent.setStylesBar(contentBinding.styleBar)
                } else contentBinding.bottomBar.visibility = View.GONE
            }
        } catch (e: Throwable) {
            Log.d("TAG", "onViewCreated: ")
        }


        contentBinding.fabPickColor.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(
                requireContext(), R.style.BottomSheetDialogTheme
            )

            val bottomSheetView: View = layoutInflater.inflate(
                R.layout.bottom_sheet, null,
            )
            with(bottomSheetDialog) {
                setContentView(bottomSheetView)
                show()
            }


            val bottomSheetBinding = BottomSheetBinding.bind(bottomSheetView)
            bottomSheetBinding.apply {
                colorPicker.apply {
                    setSelectedColor(color)
                    setOnColorSelectedListener { value ->
                        color = value
                        contentBinding.apply {
                            cardItemLayoutParent.setCardBackgroundColor(color)
                            toolbarFragmentCardContent.setBackgroundColor(color)
                            bottomBar.setBackgroundColor(color)
                            activity.window.statusBarColor = color
                        }

                        bottomSheetBinding.bottomSheetParent.setCardBackgroundColor(color)

                    }
                }
                bottomSheetParent.setCardBackgroundColor(color)
            }
            bottomSheetView.post {
                bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun saveCard() {
        if (contentBinding.edtCardContent.text.toString().isEmpty() ||
            contentBinding.edtCardTitle.text.toString().isEmpty()
        ) {

            Toast.makeText(activity, "Something is Empty", Toast.LENGTH_SHORT).show()
        } else {
            cards = args.card

            when (cards) {
                null -> {
                    cardActivityViewModel.saveCard(
                        CardModel(
                            0,
                            contentBinding.edtCardTitle.text.toString(),
                            contentBinding.edtCardContent.getMD(),
                            currentDate,
                            color
                        )
                    )
                    navController.navigate(ManagerFragmentDirections.actionManagerFragmentToNoteFragment())
                }
                else -> {

                    //update or card

                }
            }


        }


    }


}