package app.sivakomaragiri.acromine.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import app.sivakomaragiri.acromine.R
import app.sivakomaragiri.acromine.databinding.ActivityMainBinding
import app.sivakomaragiri.acromine.netwok.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var adapter: LongFormParentAdapter? = null
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        initViews()
    }

    private fun initViews() {
        setupRecyclerView()
        binding.searchBtn.setOnClickListener {
            val searchText = binding.searchTextEt.text
            if(searchText.isNullOrBlank().not()) {
                adapter?.updateData(emptyList())
                binding.searchStringTv.text = getString(R.string.searching_for, searchText)
                binding.progressBar.visibility = View.VISIBLE
                viewModel.getLongForm(searchText.toString())
            }
        }
        viewModel.longFormResponse.observe(this) { response ->
            binding.progressBar.visibility = View.GONE
            when (response) {
                is NetworkResult.Success -> {
                    binding.resultsCount.text = getString(R.string.result_count, (response.data?.lfs?.size ?: 0).toString())
                    response.data?.lfs?.let {
                        adapter?.updateData(it)
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        adapter = LongFormParentAdapter()
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}