package com.route.todoappc40gsat

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.route.todoappc40gsat.databinding.ActivityMainBinding
import com.route.todoappc40gsat.fragments.AddTaskBottomSheet
import com.route.todoappc40gsat.fragments.SettingsFragment
import com.route.todoappc40gsat.fragments.TodoListFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var todoListFragment: TodoListFragment
    lateinit var settingsFragment: SettingsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoListFragment = TodoListFragment()
        settingsFragment = SettingsFragment()
        binding.todoBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_list -> pushFragment(todoListFragment)
                R.id.navigation_settings -> pushFragment(settingsFragment)
            }
            return@setOnItemSelectedListener true
        }
        binding.todoBottomNavigationView.selectedItemId = R.id.navigation_list
        binding.fabAdd.setOnClickListener {
            val bottomSheet = AddTaskBottomSheet()
            bottomSheet.onTaskAddedListener = object : OnTaskAddedListener {
                override fun onTaskAdded() {
                    if (todoListFragment.isVisible)
                        todoListFragment.getAllTasksFromDataBase()
                }
            }
            bottomSheet.show(supportFragmentManager, null)
        }
    }

    fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.todoFragmentContainer.id, fragment)
            .commit()
    }
}