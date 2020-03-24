package org.d3if4202.tambalin.jurnal08

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        val adapter = DiaryAdapter()
        binding.rvDiary.adapter = adapter
        diaryViewModel.semuaDataDiary.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_hapusSemua -> {
                diaryViewModel.hapusSemuaDiary()
                Toast.makeText(activity, "Berhasil hapus semua diary", Toast.LENGTH_LONG).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
