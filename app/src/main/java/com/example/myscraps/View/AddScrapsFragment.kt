package com.example.myscraps.View

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.myscraps.Model.Scraps
import com.example.myscraps.R
import com.example.myscraps.ViewModel.ScrapsViewModel
import kotlinx.android.synthetic.main.fragment_add_scraps.*
import kotlinx.android.synthetic.main.fragment_add_scraps.view.*
import java.text.SimpleDateFormat
import java.util.*


class AddScrapsFragment : DialogFragment() {

    lateinit var name: String
    lateinit var desc: String
    lateinit var taskDate: String
    private lateinit var modelView: ScrapsViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.background_border);
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_scraps, container, false)

        modelView = ViewModelProvider(this).get(ScrapsViewModel::class.java)

        view.addScrapsButton.setOnClickListener {
            //addScrapsButton.setBackgroundResource(R.drawable.button_border_black)
            //addScrapsButton.setTextColor(Color.parseColor("#FFFFFF"))
            insertDataToDatabase()
            //view.findNavController().navigate(R.id.action_addScrapsFragment_to_mainBoardFragment)
            dismiss()//for dialogfragment if i used dialog format
        }
        view.cancelNewTaskButton.setOnClickListener {
            dismiss()
        }

        view.setOnClickListener {
            closeKeyboard(view)
        }

        return view
    }


    private fun insertDataToDatabase() {
        name = input_name.text.toString()
        desc = input_description.text.toString()

        val dateNow = getCurrentDate()//Calendar.getInstance().time

        taskDate = dateNow


        if (inputCheck(name, desc)) {
            //if not empty
            val user = Scraps(0, name, desc,taskDate)
            //add to database
            modelView.addScraps(user)
            Toast.makeText(requireContext(), "Запись в БД внесена", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Ни одной записи в БД не добавлено", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(name: String, description: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(description))
    }

    private fun closeKeyboard(view: View) {
        val imm: InputMethodManager = (activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getCurrentDate():String{
        val sdf = SimpleDateFormat("dd-MM-yyyy' Time 'HH:mm:ss")
        return sdf.format(Date())
    }

}