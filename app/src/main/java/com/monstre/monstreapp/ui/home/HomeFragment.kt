package com.monstre.monstreapp.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.monstre.monstreapp.R
import com.monstre.monstreapp.data.Result
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.data.remote.response.GeneralSaturationListResponse
import com.monstre.monstreapp.databinding.FragmentHomeBinding
import com.monstre.monstreapp.ui.ViewModelFactory
import com.monstre.monstreapp.ui.adapter.StressAdapter
import com.monstre.monstreapp.ui.adapter.SuggestionAdapter
import com.monstre.monstreapp.ui.detailarticle.ArticleFragment
import com.monstre.monstreapp.utils.stressList
import com.monstre.monstreapp.utils.stressListMonth
import com.monstre.monstreapp.utils.visibility
import java.text.SimpleDateFormat
import java.util.*

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var pref: DataStore<Preferences>

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        pref = requireContext().dataStore
        setupViewModel()

        val setLayoutManagerHorizontal =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val setLayoutManagerVertical =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.apply {
            rvStress.apply {
                setHasFixedSize(true)
                layoutManager = setLayoutManagerHorizontal
            }
            rvSuggestion.apply {
                setHasFixedSize(true)
                layoutManager = setLayoutManagerVertical

            }

            imageButton.setOnClickListener {
                findNavController().navigate(R.id.action_nav_home_to_nav_profile, null, null)
            }
            tvMonth.setOnClickListener {
                setViewSelectedBagde("Month")
                viewModel.setBadges("Month")
            }
            tvWeek.setOnClickListener {
                setViewSelectedBagde("Week")
                viewModel.setBadges("Week")
            }
            tvYear.setOnClickListener {
                setViewSelectedBagde("Year")
                viewModel.setBadges("Year")
            }


            viewModel.user.observe(viewLifecycleOwner) { user ->
                if (user.token.isNotEmpty()) {
                    Glide.with(imageButton.context)
                        .load("https://monstre-production.herokuapp.com/storage/images/avatars/${user.id}/${user.avatar}")
                        .placeholder(R.drawable.img_profile_avatar)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }
                        })
                        .into(imageButton)

                    binding?.tvHelloUser?.text = "Hi, ${user.name}!"
                    viewModel.badges.observe(viewLifecycleOwner) { badge ->
                        if (badge == "Week") {
                            setViewSelectedBagde("Week")
                            viewModel.getSaturationFullWeek(user.token)
                                .observe(viewLifecycleOwner) { result ->
                                    if (result != null) {
                                        viewModel.badges.observe(viewLifecycleOwner) {
                                            setResultSaturationList(result, badge)
                                        }
                                    }
                                }
                        } else if (badge == "Month") {
                            setViewSelectedBagde("Month")
                            viewModel.getSaturationFullMonth(user.token)
                                .observe(viewLifecycleOwner) { result ->
                                    if (result != null) {
                                        viewModel.badges.observe(viewLifecycleOwner) {
                                            setResultSaturationList(result, badge)
                                        }
                                    }
                                }
                        } else {
                            setViewSelectedBagde("Year")
                        }
                    }

                    viewModel.getArticles(user.token).observe(viewLifecycleOwner) { result ->
                        if (result != null) {
                            when (result) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }
                                is Result.Success -> {
                                    showLoading(false)
                                    binding?.tvNotFoundArticle?.visibility = visibility(false)
                                    if (result.data.type == "message") {
                                        binding.apply {
                                            tvNotFoundArticle.visibility = visibility(true)
                                            tvNotFoundArticle.text = result.data.data[0].desc
                                            tvTitleSuggestion.text = result.data.data[0].title
                                        }
                                    } else {
                                        binding.apply {
                                            val adapter = SuggestionAdapter(result.data.data)
                                            rvSuggestion.adapter = adapter
                                            adapter.onItemClick = {
                                                val bundle =
                                                    bundleOf(ArticleFragment.EXTRA_ARTICLE to it)
                                                view.findNavController()
                                                    .navigate(R.id.articleFragment, bundle)
                                            }

                                        }
                                    }
                                }
                                is Result.Error -> {
                                    showLoading(false)
                                    binding?.tvNotFoundArticle?.visibility = visibility(true)
                                    showMessage(getString(R.string.no_article_found))
                                }
                            }
                        }
                    }

                    viewModel.getSaturation(user.token).observe(viewLifecycleOwner) { result ->
                        if (result != null) {
                            when (result) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }
                                is Result.Success -> {
                                    showLoading(false)
                                    if (result.data.data == null) {
                                        viewModel.getSmartWatchData()
                                            .observe(viewLifecycleOwner) { result ->
                                                when (result) {
                                                    is Result.Loading -> {
                                                        showLoading(true)
                                                    }
                                                    is Result.Success -> {
                                                        postSaturationToday(
                                                            user.token,
                                                            result.data.heartRate.toString(),
                                                            result.data.oxiRate.toString()
                                                        )
                                                        setOxiBpmView(
                                                            result.data.heartRate.toString(),
                                                            result.data.oxiRate.toString(),
                                                        )
                                                        showLoading(false)
                                                    }
                                                    is Result.Error -> {
                                                        showLoading(false)
                                                        showMessage(getString(R.string.something_wrong))
                                                    }
                                                }
                                            }
                                    } else {
                                        binding.apply {
                                            setOxiBpmView(
                                                result.data.data.bpm.toString(),
                                                result.data.data.spo2.toString(),
                                            )
                                            setMoodView(
                                                result.data.data.stressNumber,
                                                result.data.data.desc
                                            )

                                        }

                                    }
                                }
                                is Result.Error -> {
                                    showLoading(false)
                                    showMessage(getString(R.string.something_wrong))
                                }
                            }
                        }
                    }
                }
            }

        }
        return binding?.root
    }

    private fun setOxiBpmView(bpm: String, spo2: String) {
        binding?.apply {
            tvBpmNumber.text =
                bpm
            tvOxygenNumber.text =
                spo2
            circularProgressIndicator.progress =
                spo2.toInt()
            circularProgressIndicator.max = 100
        }
    }

    private fun setMoodView(stressLevel: Int, desc: String) {
        binding?.apply {
            when (desc) {
                "Calm" -> {
                    ivMood.setImageResource(R.drawable.img_emoji_smile)
                }
                "Relax" -> {
                    ivMood.setImageResource(R.drawable.img_emoji_relax)
                }
                "Anxious" -> {
                    ivMood.setImageResource(R.drawable.img_emoji_anxious)
                }
                "Stressed" -> {
                    ivMood.setImageResource(R.drawable.img_emoji_stress)
                }
            }
            tvEmotionField.text = desc
            if (stressLevel < 40) {
                tvMoodField.text = "Good"
            } else if (stressLevel >= 40 || stressLevel < 70) {
                tvMoodField.text = "Netral"
            } else {
                tvMoodField.text = "Bad"
            }
        }
    }

    private fun postSaturationToday(token: String, bpm: String, spo2: String) {
        viewModel.postSaturationToday(token, bpm, spo2).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    setMoodView(result.data.data.stressNumber.toInt(),result.data.data.desc)
                    showLoading(false)
                }
                is Result.Error -> {
                    showLoading(false)
                    showMessage(getString(R.string.something_wrong))
                }
            }
        }
    }

    private fun setViewSelectedBagde(badge: String) {
        binding?.apply {
            tvMonth.setTypeface(null, Typeface.NORMAL)
            tvYear.setTypeface(null, Typeface.NORMAL)
            tvWeek.setTypeface(null, Typeface.NORMAL)
            when (badge) {
                "Month" -> tvMonth.setTypeface(null, Typeface.BOLD)
                "Year" -> tvYear.setTypeface(null, Typeface.BOLD)
                "Week" -> tvMonth.setTypeface(null, Typeface.BOLD)
            }
        }
    }

    private fun setResultSaturationList(
        result: Result<GeneralSaturationListResponse>,
        badge: String
    ) {
        when (result) {
            is Result.Loading -> {
                showLoading(true)
            }
            is Result.Success -> {
                showLoading(false)

                result.data.data.map { data ->
                    val inFormat = SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.ENGLISH
                    )
                    val date = inFormat.parse(data.date)
                    val outFormat =
                        SimpleDateFormat("EEEE", Locale.ENGLISH)

                    val goal =
                        date?.let { outFormat.format(it).subSequence(0, 3) }
                    val index =
                        stressList.indexOfFirst { it.time == goal }
                    val indexMonth = stressListMonth.indexOfFirst { it.time == data.date.subSequence(8, 10) }
                    Log.e("date =====" ,data.date.subSequence(8, 10).toString())
                    stressList[index].level = data.stressNumber
                    stressListMonth[indexMonth].level = data.stressNumber
                }

                binding?.apply {
                    viewModel.badges.observe(viewLifecycleOwner) {
                        if (it == "Week") {
                            tvWeek.setTypeface(null, Typeface.BOLD)
                            tvYear.setTypeface(null, Typeface.NORMAL)
                            tvMonth.setTypeface(null, Typeface.NORMAL)
                            rvStress.adapter = StressAdapter(stressList, badge)
                        } else if (it == "Month") {
                            rvStress.adapter = StressAdapter(stressListMonth, badge)
                        }
                    }

                }

            }
            is Result.Error -> {
                showLoading(false)
                showMessage(getString(R.string.something_wrong))
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(SharedPreference.getInstance(pref), requireContext())
        )[HomeViewModel::class.java]
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showMessage(message: String) {
        if (message != "") {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }


}