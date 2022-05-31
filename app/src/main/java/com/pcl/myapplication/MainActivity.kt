package com.pcl.myapplication

import android.content.res.AssetManager
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pcl.myapplication.adapter.SelectOrganDeptAdapter
import com.pcl.myapplication.bean.Depts
import com.pcl.myapplication.bean.Response
import com.pcl.myapplication.utils.CollectionUtils
import com.pcl.myapplication.utils.JsonUtil
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private var data: Depts? = null

    private var selectedDept : Depts.Children? = null

    private lateinit var recyclerView : RecyclerView

    private lateinit var toolbar : Toolbar

    private lateinit var btnConfirm : TextView

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_select_depts, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        btnConfirm = findViewById(R.id.btnConfirm)

        //模拟网络请求数据
        val localData = loadLocalData()
        if (localData?.code == 0) {
            data = localData.data
        }

        //设置界面数据
        data?.let {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = SelectOrganDeptAdapter(this, it.children, it.children)
        }

        //获取选中的数据
        btnConfirm.setOnClickListener {
            getSelectedDept(data?.children)
            Toast.makeText(applicationContext,selectedDept?.name +  " | " + selectedDept?.id,Toast.LENGTH_SHORT).show()

        }
    }

    private fun getSelectedDept(list: List<Depts.Children>?) {
        list?.forEach {
            if (it.isChecked){
                selectedDept = it
                return@forEach
            }
            if (CollectionUtils.isNotEmpty(it.children)) {
                getSelectedDept(it.children)
            }
        }
    }

    private fun loadLocalData(): Response? {
        val sb = StringBuilder()
        val assetManager: AssetManager = this.assets
        try {
            //使用IO流读取json文件内容
            val bufferedReader = BufferedReader(
                InputStreamReader(
                    assetManager.open("depts.json"),
                    Charset.forName("UTF-8")
                )
            )
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            return JsonUtil.parseObject(sb.toString(), Response::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}