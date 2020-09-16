package lit.amida.lfsskilllistrealm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val memo = read()
        if(memo != null){
            editTitle.setText(memo.title)
            editContent.setText(memo.content)
        }

        button.setOnClickListener {
            save(editTitle.text.toString(), editContent.text.toString())
        }
    }

    fun read(): Memo?{
        return realm.where(Memo::class.java).findFirst()
    }

    fun save(title: String, content:String){
        val memo = read()

        realm.executeTransaction{
            if(memo != null){
                memo.title = title
                memo.content = content
            }else{
                val newMemo = it.createObject(Memo::class.java)
                newMemo.title = title
                newMemo.content = content
            }

            Snackbar.make(container, "saved!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}