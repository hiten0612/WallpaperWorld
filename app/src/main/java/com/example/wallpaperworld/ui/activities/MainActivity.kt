package com.example.wallpaperworld.ui.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.wallpaperworld.listeners.Listeners
import com.example.wallpaperworld.adapters.CategoryAdapter
import com.example.wallpaperworld.databinding.ActivityMainBinding
import com.example.wallpaperworld.models.CategoryModel


class MainActivity : AppCompatActivity(), Listeners {

    private lateinit var binding: ActivityMainBinding
    private var mAdapter: CategoryAdapter? = null
    private var cList = ArrayList<CategoryModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cList.add(CategoryModel(1, "https://cdn.pixabay.com/photo/2020/11/22/17/28/cat-5767334_960_720.jpg", "animals"))
        cList.add(CategoryModel(2, "https://images.pexels.com/photos/3375116/pexels-photo-3375116.jpeg?auto=compress&cs=tinysrgb&w=1600", "nature"))
        cList.add(CategoryModel(3, "https://images.pexels.com/photos/2116475/pexels-photo-2116475.jpeg?auto=compress&cs=tinysrgb&w=1600", "bike"))
        cList.add(CategoryModel(4, "https://images.pexels.com/photos/1638324/pexels-photo-1638324.jpeg?auto=compress&cs=tinysrgb&w=1600", "gym"))
        cList.add(CategoryModel(5, "https://images.pexels.com/photos/36846/bald-eagle-adler-bird-of-prey-raptor.jpg?auto=compress&cs=tinysrgb&w=1600", "birds"))
        cList.add(CategoryModel(6, "https://images.pexels.com/photos/219998/pexels-photo-219998.jpeg?auto=compress&cs=tinysrgb&w=1600", "beach"))
        cList.add(CategoryModel(7, "https://images.pexels.com/photos/3568520/pexels-photo-3568520.jpeg?auto=compress&cs=tinysrgb&w=1600", "technology"))
        cList.add(CategoryModel(8, "https://images.pexels.com/photos/1535162/pexels-photo-1535162.jpeg?auto=compress&cs=tinysrgb&w=1600", "iphone wallpaper"))
        cList.add(CategoryModel(9, "https://images.pexels.com/photos/2700332/pexels-photo-2700332.jpeg?auto=compress&cs=tinysrgb&w=1600", "fantasy"))
        cList.add(CategoryModel(10, "https://images.pexels.com/photos/736230/pexels-photo-736230.jpeg?auto=compress&cs=tinysrgb&w=1600", "flowers"))
        cList.add(CategoryModel(11, "https://images.pexels.com/photos/1633578/pexels-photo-1633578.jpeg?auto=compress&cs=tinysrgb&w=1600", "food"))
        cList.add(CategoryModel(12, "https://images.pexels.com/photos/208701/pexels-photo-208701.jpeg?auto=compress&cs=tinysrgb&w=1600", "vacation"))
        cList.add(CategoryModel(13, "https://images.pexels.com/photos/206770/pexels-photo-206770.jpeg?auto=compress&cs=tinysrgb&w=1600", "macro"))
        cList.add(CategoryModel(14, "https://images.pexels.com/photos/1001990/pexels-photo-1001990.jpeg?auto=compress&cs=tinysrgb&w=1600", "vector"))
        cList.add(CategoryModel(15, "https://images.pexels.com/photos/3444345/pexels-photo-3444345.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "christmas"))
        cList.add(CategoryModel(16, "https://images.pexels.com/photos/1337247/pexels-photo-1337247.jpeg?auto=compress&cs=tinysrgb&w=1600", "game"))
        cList.add(CategoryModel(17, "https://images.pexels.com/photos/1368382/pexels-photo-1368382.jpeg?auto=compress&cs=tinysrgb&w=1600", "fire"))
        cList.add(CategoryModel(18, "https://images.pexels.com/photos/1317943/pexels-photo-1317943.jpeg?auto=compress&cs=tinysrgb&w=1600", "alone"))
        cList.add(CategoryModel(19, "https://images.pexels.com/photos/36717/amazing-animal-beautiful-beautifull.jpg?auto=compress&cs=tinysrgb&w=1600", "sunset"))
        cList.add(CategoryModel(20, "https://images.pexels.com/photos/1266810/pexels-photo-1266810.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "sunrise"))
        cList.add(CategoryModel(21, "https://images.pexels.com/photos/723023/pexels-photo-723023.jpeg?auto=compress&cs=tinysrgb&w=1600", "caves"))
        cList.add(CategoryModel(22, "https://images.pexels.com/photos/6488410/pexels-photo-6488410.jpeg?auto=compress&cs=tinysrgb&w=1600", "temple"))
        cList.add(CategoryModel(23, "https://images.pexels.com/photos/3622517/pexels-photo-3622517.jpeg?auto=compress&cs=tinysrgb&w=1600", "creative"))
        cList.add(CategoryModel(24, "https://images.pexels.com/photos/1592384/pexels-photo-1592384.jpeg?auto=compress&cs=tinysrgb&w=1600", "car"))
        cList.add(CategoryModel(25, "https://images.pexels.com/photos/3045825/pexels-photo-3045825.jpeg?auto=compress&cs=tinysrgb&w=1600", "aesthetic"))
        cList.add(CategoryModel(26, "https://images.pexels.com/photos/325117/pexels-photo-325117.jpeg?auto=compress&cs=tinysrgb&w=1600", "clouds"))
        cList.add(CategoryModel(27, "https://images.pexels.com/photos/1742927/pexels-photo-1742927.jpeg?auto=compress&cs=tinysrgb&w=1600", "street"))
        cList.add(CategoryModel(28, "https://images.pexels.com/photos/2613015/pexels-photo-2613015.jpeg?auto=compress&cs=tinysrgb&w=1600", "fort"))
        cList.add(CategoryModel(29, "https://images.pexels.com/photos/1266810/pexels-photo-1266810.jpeg?auto=compress&cs=tinysrgb&w=1600", "mountains"))
        cList.add(CategoryModel(30, "https://images.pexels.com/photos/2189700/pexels-photo-2189700.jpeg?auto=compress&cs=tinysrgb&w=1600", "river"))
        cList.add(CategoryModel(31, "https://images.pexels.com/photos/1731660/pexels-photo-1731660.jpeg?auto=compress&cs=tinysrgb&w=1600", "desert"))
        cList.add(CategoryModel(32, "https://images.pexels.com/photos/47367/full-moon-moon-bright-sky-47367.jpeg?auto=compress&cs=tinysrgb&w=1600", "space"))
        cList.add(CategoryModel(33, "https://images.pexels.com/photos/2362004/pexels-photo-2362004.jpeg?auto=compress&cs=tinysrgb&w=1600", "night city"))
        cList.add(CategoryModel(34, "https://images.pexels.com/photos/11279107/pexels-photo-11279107.jpeg?auto=compress&cs=tinysrgb&w=1600", "disneyland"))
        cList.add(CategoryModel(35, "https://images.pexels.com/photos/2480685/pexels-photo-2480685.jpeg?auto=compress&cs=tinysrgb&w=1600", "castle"))
        cList.add(CategoryModel(36, "https://images.pexels.com/photos/1459534/pexels-photo-1459534.jpeg?auto=compress&cs=tinysrgb&w=1600", "forest"))
        cList.add(CategoryModel(37, "https://images.pexels.com/photos/1998479/pexels-photo-1998479.jpeg?auto=compress&cs=tinysrgb&w=1600", "abstract painting"))
        cList.add(CategoryModel(38, "https://images.pexels.com/photos/6022627/pexels-photo-6022627.jpeg?auto=compress&cs=tinysrgb&w=1600", "fairy tale "))
        cList.add(CategoryModel(39, "https://images.pexels.com/photos/4172845/pexels-photo-4172845.jpeg?auto=compress&cs=tinysrgb&w=1600", "adventure"))
        cList.add(CategoryModel(40, "https://images.pexels.com/photos/3362702/pexels-photo-3362702.jpeg?auto=compress&cs=tinysrgb&w=1600", "neon"))
        cList.add(CategoryModel(41, "https://images.pexels.com/photos/675313/pexels-photo-675313.jpeg?auto=compress&cs=tinysrgb&w=1600", "insects"))
        cList.add(CategoryModel(42, "https://images.pexels.com/photos/675313/pexels-photo-675313.jpeg?auto=compress&cs=tinysrgb&w=1600", "peace"))







        mAdapter?.addList(cList)
        mAdapter = CategoryAdapter(cList, this)
        binding.rvCategory.adapter = mAdapter
        binding.rvCategory.itemAnimator = null

    }

    override fun onItemClick(position: Int) {
     val name = cList[position].catName
        Log.e(TAG, "onItemClick: $name ", )
        val intent = Intent(this@MainActivity, WallPaperActivity::class.java)
        intent.putExtra("catName",name)
        startActivity(intent)
        Animatoo.animateSlideLeft(this);


    }


}
