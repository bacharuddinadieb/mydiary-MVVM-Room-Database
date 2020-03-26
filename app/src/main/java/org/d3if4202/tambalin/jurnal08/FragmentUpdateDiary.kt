package org.d3if4202.tambalin.jurnal08

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import org.d3if4202.tambalin.jurnal08.database.DiaryDatabase
import org.d3if4202.tambalin.jurnal08.databinding.FragmentUpdateDiaryBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentUpdateDiary : Fragment() {
    lateinit var binding: FragmentUpdateDiaryBinding
    lateinit var viewModelFactory: DiaryViewModelFactory
    lateinit var diaryViewModel: DiaryViewModel
    var idDiary: Long = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_update_diary, container, false)
        setHasOptionsMenu(true)
        val application = requireNotNull(this.activity).application
        val dataSource = DiaryDatabase.getInstance(application).diaryDatabaseDao
        val args = arguments?.let { FragmentUpdateDiaryArgs.fromBundle(it) }
        idDiary = args!!.diaryKey

        viewModelFactory = DiaryViewModelFactory(dataSource, application)
        diaryViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DiaryViewModel::class.java)

        Log.i("asdqwe", "${args.diaryKey} + ${args.diaryTextKey}")
        binding.etUpdateDiary.setText(args.diaryTextKey)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_tambahUpdate -> {
                diaryViewModel.updateDiary(idDiary, binding.etUpdateDiary.text.toString())
                Toast.makeText(activity, "Berhasil memperbarui diary", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_fragmentUpdateDiary_to_mainFragment)
                return true
            }
            R.id.menu_hapusUpdate -> {
                diaryViewModel.hapusDiary(idDiary)
                Toast.makeText(activity, "Berhasil menghapus diary", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_fragmentUpdateDiary_to_mainFragment)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
