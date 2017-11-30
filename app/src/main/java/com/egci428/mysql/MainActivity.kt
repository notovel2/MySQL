package com.egci428.mysql

import android.app.ListActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import java.util.*

class MainActivity : ListActivity(){
    private var datasource: CommentDataSource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        datasource = CommentDataSource(this)
        datasource!!.open()
        val values = datasource!!.allComments
        val adapter = ArrayAdapter<Comment>(this,android.R.layout.simple_list_item_1,values)
        setListAdapter(adapter)

    }

    override fun onResume() {
        datasource!!.open()
        super.onResume()
    }

    override fun onPause() {
        datasource!!.close()
        super.onPause()
    }

    fun onClick(view: View) {
        val adapter = getListAdapter() as ArrayAdapter<Comment>
        var comment: Comment? = null
        when (view.getId()) {
            R.id.add -> {
                val comments = arrayOf("Cool", "Very nice", "Hate it")
                val nextInt = Random().nextInt(3)
                // save the new comment to the database
                comment = datasource!!.createComment(comments[nextInt])
                adapter.add(comment)
            }
            R.id.delete -> if (getListAdapter().getCount() > 0) {
                comment = getListAdapter().getItem(0) as Comment
                datasource!!.deleteComment(comment!!)
                adapter.remove(comment)
            }
        }
        adapter.notifyDataSetChanged()
    }
}
