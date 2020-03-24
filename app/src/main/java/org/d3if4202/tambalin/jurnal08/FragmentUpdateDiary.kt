package org.d3if4202.tambalin.jurnal08

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.d3if4202.tambalin.jurnal08.databinding.FragmentUpdateDiaryBinding

/**
 * A simple [Fragment] subclass.
 */
class FragmentUpdateDiary : Fragment() {
    lateinit var binding: FragmentUpdateDiaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_update_diary, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}
