package org.d3if4202.tambalin.jurnal08

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import org.d3if4202.tambalin.jurnal08.database.DiaryDatabase
import org.d3if4202.tambalin.jurnal08.databinding.FragmentAddDiaryBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentAddDiary : Fragment() {
    lateinit var binding: FragmentAddDiaryBinding
    lateinit var viewModelFactory: DiaryViewModelFactory
    lateinit var diaryViewModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_diary, container, false)
        setHasOptionsMenu(true)
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao

        viewModelFactory = DiaryViewModelFactory(dataSource, application)
        diaryViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_tambahAdd -> {
                diaryViewModel.tambahkanDiary(binding.etAddDiary.text.toString())
                findNavController().navigate(R.id.action_fragmentAddDiary_to_mainFragment)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
