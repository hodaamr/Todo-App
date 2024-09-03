package com.route.todoappc40gsat.fragments

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

import androidx.fragment.app.Fragment
import com.route.todoappc40gsat.R
import com.route.todoappc40gsat.databinding.FragmentSettingsBinding
import java.util.Locale

class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    lateinit var locale: Locale


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLanguageList()
        initModesList()

    }

    private fun initLanguageList() {
        val languages = resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, languages)
        binding.languagesList.setAdapter(adapter)

        binding.languagesList.setOnItemClickListener { adapterView, view, i, l ->
            val languageSelected = adapterView.getItemAtPosition(i).toString()

            if (languageSelected.equals("English") || languageSelected.equals("الانجليزية")) {
                setLocale("en")
                recreateActivity()
            } else if(languageSelected.equals("Arabic") || languageSelected.equals("العربية") ) {
                setLocale("ar")
                recreateActivity()
            }

        }

    }


    private fun initModesList() {
        val modes = resources.getStringArray(R.array.modes)
        val adapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, modes)
        binding.modeList.setAdapter(adapter)

        binding.modeList.setOnItemClickListener { adapterView, view, i, l ->
            val modeSelected = adapterView.getItemAtPosition(i)

            if (modeSelected.equals("Light")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                recreateActivity()
            } else if(modeSelected.equals("Dark") ) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                recreateActivity()
            }
        }


    }

    private fun setLocale(languageCode: String) {
        locale = Locale(languageCode)
        Locale.setDefault(locale)
        var configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    fun Fragment.recreateActivity() {
        activity?.let {
            val intent = Intent(it, it::class.java)
            it.startActivity(intent)
            it.finish()
        }
    }
}
