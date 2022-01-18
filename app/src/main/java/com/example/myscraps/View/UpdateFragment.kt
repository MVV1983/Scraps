package com.example.myscraps.View

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.myscraps.Model.Scraps
import com.example.myscraps.R
import com.example.myscraps.ViewModel.ScrapsViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : DialogFragment() {

    lateinit var name: String
    lateinit var desc: String
    lateinit var updateDateTask: String
    lateinit var changeableName: EditText
    lateinit var changeableDescription: EditText
    var tmpId: Int = 0


    private lateinit var modelView: ScrapsViewModel


    companion object {
        private const val ID = "ID"
        private const val NAME = "NAME"
        private const val DESC = "DESC"
        private const val DATE = "DATE"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        modelView = ViewModelProvider(this).get(ScrapsViewModel::class.java)


        changeableName = view?.findViewById(R.id.update_name)!!
        changeableName.hint = arguments?.getString(NAME).toString()
        changeableDescription = view.findViewById(R.id.update_description)!!
        changeableDescription.hint = arguments?.getString(DESC).toString()

        view.cancelUpdateTaskButton.setOnClickListener {
            dismiss()
        }

        view.saveScrapsButton.setOnClickListener {
            updateDataToDatabase()
        }

        view.setOnClickListener {
            closeKeyboard(view)
        }

        return view
    }


    private fun updateDataToDatabase() {

        name = update_name.text.toString()
        desc = update_description.text.toString()
        tmpId = arguments?.getInt(ID)!!

        updateDateTask = arguments?.getString(DATE).toString()

        if (inputCheck(name, desc)) {
            //if not empty
            val user = Scraps(tmpId, name, desc, updateDateTask)
            //update data in database
            modelView.updateScraps(user)
            Toast.makeText(requireContext(), "Запись обновлена", Toast.LENGTH_LONG).show()
            dismiss()
        } else {
            Toast.makeText(requireContext(), "Невозможно сохранить, внесите данные в поля!!!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, description: String): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(description))
    }

    private fun closeKeyboard(view: View) {
        val imm: InputMethodManager = (activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}