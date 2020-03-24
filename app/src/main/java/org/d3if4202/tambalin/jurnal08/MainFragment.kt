package org.d3if4202.tambalin.jurnal08

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import org.d3if4202.tambalin.jurnal08.database.DiaryDatabase
import org.d3if4202.tambalin.jurnal08.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var viewModelFactory: DiaryViewModelFactory
    lateinit var diaryViewModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao

        viewModelFactory = DiaryViewModelFactory(dataSource, application)
        diaryViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)
        binding.lifecycleOwner = this
        binding.diaryViewModel = diaryViewModel

        binding.fabTambahDiary.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_fragmentAddDiary)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
