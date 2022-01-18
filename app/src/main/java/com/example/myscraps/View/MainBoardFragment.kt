package com.example.myscraps.View


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myscraps.Model.Scraps
import com.example.myscraps.R
import com.example.myscraps.ScrapsAdapter
import com.example.myscraps.ViewModel.ScrapsViewModel
import kotlinx.android.synthetic.main.fragment_main_board.view.*
import kotlin.system.exitProcess


class MainBoardFragment : Fragment(), ScrapsAdapter.DeleteScrapInterface,ScrapsAdapter.ItemClickInterface{

    private lateinit var modelView: ScrapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_board, container, false)

        //recyclerView init
        val adapter = ScrapsAdapter(this, this)
        var recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        modelView = ViewModelProvider(this).get(ScrapsViewModel::class.java)

        modelView.readAllData.observe(viewLifecycleOwner, Observer { unit ->
            adapter.setData(unit)
        })


        view.floatingActionButton.setOnClickListener {
            //findNavController().navigate(R.id.action_mainBoardFragment_to_addScrapsFragment)
            val pop = AddScrapsFragment()
            val fm = this.fragmentManager
            if (fm != null) {
                pop.show(fm, "name")
            }
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onDelete(scraps: Scraps) {
        val dialogWondow = AlertDialog.Builder(context, R.style.AlertDialogStyle)

        dialogWondow.setPositiveButton("YES") { _, _ ->
            modelView.deleteScraps(scraps)
        }
        dialogWondow.setNegativeButton("NO") { _, _ ->

        }

        dialogWondow.setTitle("УДАЛЯЕМ ЗАДАЧУ ${scraps.name}?")
        dialogWondow.setMessage("ВЫ УВЕРЕНЫ?")
        dialogWondow.create().show()
    }

    override fun onClicked(scraps: Scraps) {
        val pop = UpdateFragment()

        val args = Bundle()

        args.putInt("ID", scraps.id)
        args.putString("NAME",scraps.name)
        args.putString("DESC",scraps.description)
        args.putString("DATE",scraps.taskDate)

        pop.arguments = args

        val fm = this.fragmentManager
        if (fm != null) {
            pop.show(fm, "scraps")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_status_navigation, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //get item id to handle item clicks
        val id = item.itemId

        //handle item clicks
        if (id == R.id.nav_exit) {
           exitProcess(0)
        }
        return super.onOptionsItemSelected(item)
    }


}