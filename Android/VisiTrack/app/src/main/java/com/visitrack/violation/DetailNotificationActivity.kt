package com.visitrack.violation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Violation
import com.visitrack.databinding.ActivityDetailNotificationBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class DetailNotificationActivity : AppCompatActivity() {

    private val viewModel: DetailNotificationViewModel by viewModel()
    private lateinit var binding: ActivityDetailNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_detail_visitrack)))

        val data = intent.getStringExtra(EXTRA_ID)

        if (data != null) {
            getDetail(data)
        }

        binding.btnFinished.setOnClickListener{
            if (data != null) {
                viewModel.getUpdateViolationNotification(data, 1).observe(this, { success ->
                    when(success){
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, getString(R.string.violation_updated), Toast.LENGTH_SHORT).show()
                            getDetail(data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, getString(R.string.violation_not_updated), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
        binding.btnError.setOnClickListener {
            if (data != null) {
                viewModel.getUpdateViolationNotification(data, 2).observe(this, { success ->
                    when(success){
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, getString(R.string.violation_updated), Toast.LENGTH_SHORT).show()
                            getDetail(data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, getString(R.string.violation_not_updated), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    private fun getDetail (id: String){
        viewModel.getNotificationDetail(id).observe(this, { violation ->
            when(violation) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val data = violation.data
                    Glide.with(this@DetailNotificationActivity)
                        .load((data?.imageUrl))
                        .into(binding.ivNotification)
                    with(binding) {
                        tvId.text = id
                        tvType.text = data?.typeViolation
                        tvCamera.text = data?.camera.toString()
                        tvDate.text = data?.dateViolation
                        tvTime.text = data?.timeViolation
                        when(data?.statusViolation) {
                            0 -> tvStatus.text = getString(R.string.status_default)
                            1 -> tvStatus.text = getString(R.string.status_done)
                            2 -> tvStatus.text = getString(R.string.status_false)
                        }
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, getString(R.string.no_data), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.visitrackDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }

    companion object {
        const val EXTRA_ID = "extraId"
    }
}