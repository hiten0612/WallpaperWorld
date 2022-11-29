package com.example.wallpaperworld.ui.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

//        cList.add("https://cdn.pixabay.com/photo/2019/03/15/09/49/girl-4056684_960_720.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2020/12/15/16/25/clock-5834193__340.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2020/09/18/19/31/laptop-5582775_960_720.jpg")
//        cList.add("https://media.istockphoto.com/photos/woman-kayaking-in-fjord-in-norway-picture-id1059380230?b=1&k=6&m=1059380230&s=170667a&w=0&h=kA_A_XrhZJjw2bo5jIJ7089-VktFK0h0I4OWDqaac0c=")
//        cList.add("https://cdn.pixabay.com/photo/2019/11/05/00/53/cellular-4602489_960_720.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2017/02/12/10/29/christmas-2059698_960_720.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2020/01/29/17/09/snowboard-4803050_960_720.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2020/02/06/20/01/university-library-4825366_960_720.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2020/11/22/17/28/cat-5767334_960_720.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2020/12/13/16/22/snow-5828736_960_720.jpg")
//        cList.add("https://cdn.pixabay.com/photo/2020/12/09/09/27/women-5816861_960_720.jpg")

        cList.add(CategoryModel(1, "https://cdn.pixabay.com/photo/2020/11/22/17/28/cat-5767334_960_720.jpg", "animals"))
        cList.add(CategoryModel(2, "https://images.pexels.com/photos/3375116/pexels-photo-3375116.jpeg?auto=compress&cs=tinysrgb&w=1600", "nature"))
        cList.add(CategoryModel(3, "https://images.pexels.com/photos/2116475/pexels-photo-2116475.jpeg?auto=compress&cs=tinysrgb&w=1600", "bike"))
        cList.add(CategoryModel(4, "https://images.pexels.com/photos/1638324/pexels-photo-1638324.jpeg?auto=compress&cs=tinysrgb&w=1600", "gym"))
        cList.add(CategoryModel(5, "https://images.pexels.com/photos/36846/bald-eagle-adler-bird-of-prey-raptor.jpg?auto=compress&cs=tinysrgb&w=1600", "birds"))
        cList.add(CategoryModel(6, "https://images.pexels.com/photos/219998/pexels-photo-219998.jpeg?auto=compress&cs=tinysrgb&w=1600", "beach"))
        cList.add(CategoryModel(7, "https://images.pexels.com/photos/3568520/pexels-photo-3568520.jpeg?auto=compress&cs=tinysrgb&w=1600", "technology"))
        cList.add(CategoryModel(8, "https://images.pexels.com/photos/1317943/pexels-photo-1317943.jpeg?auto=compress&cs=tinysrgb&w=1600", "alone"))
        cList.add(CategoryModel(9, "https://images.pexels.com/photos/2700332/pexels-photo-2700332.jpeg?auto=compress&cs=tinysrgb&w=1600", "fantasy"))
        cList.add(CategoryModel(10, "https://images.pexels.com/photos/736230/pexels-photo-736230.jpeg?auto=compress&cs=tinysrgb&w=1600", "flowers"))
        cList.add(CategoryModel(11, "https://images.pexels.com/photos/1633578/pexels-photo-1633578.jpeg?auto=compress&cs=tinysrgb&w=1600", "food"))
        cList.add(CategoryModel(12, "https://images.pexels.com/photos/208701/pexels-photo-208701.jpeg?auto=compress&cs=tinysrgb&w=1600", "vacation"))
        cList.add(CategoryModel(13, "https://images.pexels.com/photos/206770/pexels-photo-206770.jpeg?auto=compress&cs=tinysrgb&w=1600", "macro"))
        cList.add(CategoryModel(14, "https://images.pexels.com/photos/1001990/pexels-photo-1001990.jpeg?auto=compress&cs=tinysrgb&w=1600", "vector"))
        cList.add(CategoryModel(15, "https://images.pexels.com/photos/3444345/pexels-photo-3444345.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "christmas"))
        cList.add(CategoryModel(16, "https://images.pexels.com/photos/1337247/pexels-photo-1337247.jpeg?auto=compress&cs=tinysrgb&w=1600", "game"))
        cList.add(CategoryModel(17, "https://images.pexels.com/photos/1368382/pexels-photo-1368382.jpeg?auto=compress&cs=tinysrgb&w=1600", "fire"))
        cList.add(CategoryModel(18, "https://images.pexels.com/photos/47367/full-moon-moon-bright-sky-47367.jpeg?auto=compress&cs=tinysrgb&w=1600", "space"))
        cList.add(CategoryModel(19, "https://images.pexels.com/photos/36717/amazing-animal-beautiful-beautifull.jpg?auto=compress&cs=tinysrgb&w=1600", "sunset"))
        cList.add(CategoryModel(20, "https://images.pexels.com/photos/1266810/pexels-photo-1266810.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "sunrise"))
        cList.add(CategoryModel(21, "https://images.pexels.com/photos/723023/pexels-photo-723023.jpeg?auto=compress&cs=tinysrgb&w=1600", "caves"))
        cList.add(CategoryModel(22, "https://images.pexels.com/photos/6488410/pexels-photo-6488410.jpeg?auto=compress&cs=tinysrgb&w=1600", "temple"))


        mAdapter?.addList(cList)
        mAdapter = CategoryAdapter(cList, this)
        binding.rvCategory.adapter = mAdapter


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
