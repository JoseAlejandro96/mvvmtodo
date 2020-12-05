package com.star.project.mvvmtodo20.ui.task

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.star.project.mvvmtodo20.R
import com.star.project.mvvmtodo20.databinding.FragmentTasksBinding
import com.star.project.mvvmtodo20.util.onQueryTextChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : Fragment(R.layout.fragment_tasks) {

    private lateinit var _binding: FragmentTasksBinding
    private val binding get() = _binding
    private val viewModel: TaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTasksBinding.bind(view)

        val taskAdapter = TaskAdapter()

        binding.apply {
            recyclerViewTasks.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        viewModel.tasks.observe(viewLifecycleOwner){
            taskAdapter.submitList(it)
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_task, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChange {
            viewModel.searchQuery.value = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_sort_by_name ->{

                true
            }
            R.id.action_sort_by_date_created ->{

                true
            }
            R.id.action_hide_completed_task ->{
                item.isChecked = !item.isChecked

                true
            }
            R.id.action_delete_all_completed_tasks ->{

                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }
}