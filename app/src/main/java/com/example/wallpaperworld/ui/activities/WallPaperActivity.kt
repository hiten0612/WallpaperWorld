package com.example.wallpaperworld.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.wallpaperworld.listeners.Listeners
import com.example.wallpaperworld.adapters.WallPaperListAdapter
import com.example.wallpaperworld.databinding.ActivityWallPaperBinding
import com.example.wallpaperworld.models.Photo
import com.example.wallpaperworld.viewmodels.WallPaperViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WallPaperActivity : AppCompatActivity(), Listeners {

    private lateinit var binding: ActivityWallPaperBinding
    private var mAdapter: WallPaperListAdapter? = null
    private var wList = ArrayList<Photo>()
    private val viewModel by viewModels<WallPaperViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWallPaperBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
            Animatoo.animateSlideRight(this);

        }
        val imageUrl = intent.extras?.getString("catName")
        viewModel.getImages("563492ad6f91700001000001518da21d798f4f4b86f3b3ebb2e72ebb", imageUrl!!)

        setObserver()

//
//        wList.add("https://cdn.pixabay.com/photo/2019/03/15/09/49/girl-4056684_960_720.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2020/12/15/16/25/clock-5834193__340.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2020/09/18/19/31/laptop-5582775_960_720.jpg")
//        wList.add("https://media.istockphoto.com/photos/woman-kayaking-in-fjord-in-norway-picture-id1059380230?b=1&k=6&m=1059380230&s=170667a&w=0&h=kA_A_XrhZJjw2bo5jIJ7089-VktFK0h0I4OWDqaac0c=")
//        wList.add("https://cdn.pixabay.com/photo/2019/11/05/00/53/cellular-4602489_960_720.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2017/02/12/10/29/christmas-2059698_960_720.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2020/01/29/17/09/snowboard-4803050_960_720.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2020/02/06/20/01/university-library-4825366_960_720.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2020/11/22/17/28/cat-5767334_960_720.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2020/12/13/16/22/snow-5828736_960_720.jpg")
//        wList.add("https://cdn.pixabay.com/photo/2020/12/09/09/27/women-5816861_960_720.jpg")

//        mAdapter?.addList(wList)
//        mAdapter = WallPaperListAdapter(wList, this)
//        binding.rvWallpaper.adapter = mAdapter

    }


    override fun onBackPressed() {
        finish()
        Animatoo.animateSlideRight(this)
    }

    override fun onItemClick(position: Int) {

        val passImage = wList[position].src.original
        val intent = Intent(this@WallPaperActivity, FullScreenActivity::class.java)
        intent.putExtra("wallImage", passImage)
        startActivity(intent)
        Animatoo.animateSlideLeft(this);

    }

    private fun setObserver() {

        viewModel.response.observe(this) {
            wList = it?.data?.photos as ArrayList<Photo>
            mAdapter?.addList(it.data.photos as ArrayList<Photo>)
            mAdapter = WallPaperListAdapter(it.data.photos as ArrayList<Photo>, this)
            binding.rvWallpaper.adapter = mAdapter

        }
    }
}