package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.ui.main.ProfileActivity
import kotlinx.android.synthetic.main.avatar_activity.*

class AvatarActivity : AppCompatActivity() {
    var selectedAvatar: Int = 0

    private lateinit var imgAvatar1: ImageView
    private lateinit var imgAvatar2: ImageView
    private lateinit var imgAvatar3: ImageView
    private lateinit var imgAvatar4: ImageView
    private lateinit var imgAvatar5: ImageView
    private lateinit var imgAvatar6: ImageView
    private lateinit var imgAvatar7: ImageView
    private lateinit var imgAvatar8: ImageView
    private lateinit var imgAvatar9: ImageView

    private lateinit var chkAvatar1: CheckBox
    private lateinit var chkAvatar2: CheckBox
    private lateinit var chkAvatar3: CheckBox
    private lateinit var chkAvatar4: CheckBox
    private lateinit var chkAvatar5: CheckBox
    private lateinit var chkAvatar6: CheckBox
    private lateinit var chkAvatar7: CheckBox
    private lateinit var chkAvatar8: CheckBox
    private lateinit var chkAvatar9: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_activity)
        getIntentData()
        setupViews()
        setImages()

        //select the default avatar
        selectAvatar(selectedAvatar)

        //avatar images listeners
        imgAvatar1.setOnClickListener { selectAvatar(0) }
        imgAvatar2.setOnClickListener { selectAvatar(1) }
        imgAvatar3.setOnClickListener { selectAvatar(2) }
        imgAvatar4.setOnClickListener { selectAvatar(3) }
        imgAvatar5.setOnClickListener { selectAvatar(4) }
        imgAvatar6.setOnClickListener { selectAvatar(5) }
        imgAvatar7.setOnClickListener { selectAvatar(6) }
        imgAvatar8.setOnClickListener { selectAvatar(7) }
        imgAvatar9.setOnClickListener { selectAvatar(8) }

        //checkbox listeners
        chkAvatar1.setOnClickListener{ selectAvatar(0)}
        chkAvatar2.setOnClickListener{ selectAvatar(1)}
        chkAvatar3.setOnClickListener{ selectAvatar(2)}
        chkAvatar4.setOnClickListener{ selectAvatar(3)}
        chkAvatar5.setOnClickListener{ selectAvatar(4)}
        chkAvatar6.setOnClickListener{ selectAvatar(5)}
        chkAvatar7.setOnClickListener{ selectAvatar(6)}
        chkAvatar8.setOnClickListener{ selectAvatar(7)}
        chkAvatar9.setOnClickListener{ selectAvatar(8)}
    }

    private fun setupViews(){
        imgAvatar1 = findViewById(R.id.imgAvatar1)
        imgAvatar2 = findViewById(R.id.imgAvatar2)
        imgAvatar3 = findViewById(R.id.imgAvatar3)
        imgAvatar4 = findViewById(R.id.imgAvatar4)
        imgAvatar5 = findViewById(R.id.imgAvatar5)
        imgAvatar6 = findViewById(R.id.imgAvatar6)
        imgAvatar7 = findViewById(R.id.imgAvatar7)
        imgAvatar8 = findViewById(R.id.imgAvatar8)
        imgAvatar9 = findViewById(R.id.imgAvatar9)

        chkAvatar1 = findViewById(R.id.chkAvatar1)
        chkAvatar2 = findViewById(R.id.chkAvatar2)
        chkAvatar3 = findViewById(R.id.chkAvatar3)
        chkAvatar4 = findViewById(R.id.chkAvatar4)
        chkAvatar5 = findViewById(R.id.chkAvatar5)
        chkAvatar6 = findViewById(R.id.chkAvatar6)
        chkAvatar7 = findViewById(R.id.chkAvatar7)
        chkAvatar8 = findViewById(R.id.chkAvatar8)
        chkAvatar9 = findViewById(R.id.chkAvatar9)
    }

    private fun setImages(){
        imgAvatar1.setImageResource(Database.queryAllAvatars()[0].imageResId)
        imgAvatar2.setImageResource(Database.queryAllAvatars()[1].imageResId)
        imgAvatar3.setImageResource(Database.queryAllAvatars()[2].imageResId)
        imgAvatar4.setImageResource(Database.queryAllAvatars()[3].imageResId)
        imgAvatar5.setImageResource(Database.queryAllAvatars()[4].imageResId)
        imgAvatar6.setImageResource(Database.queryAllAvatars()[5].imageResId)
        imgAvatar7.setImageResource(Database.queryAllAvatars()[6].imageResId)
        imgAvatar8.setImageResource(Database.queryAllAvatars()[7].imageResId)
        imgAvatar9.setImageResource(Database.queryAllAvatars()[8].imageResId)

        //labels
        lblAvatar1.text = Database.queryAllAvatars()[0].name
        lblAvatar2.text = Database.queryAllAvatars()[1].name
        lblAvatar3.text = Database.queryAllAvatars()[2].name
        lblAvatar4.text = Database.queryAllAvatars()[3].name
        lblAvatar5.text = Database.queryAllAvatars()[4].name
        lblAvatar6.text = Database.queryAllAvatars()[5].name
        lblAvatar7.text = Database.queryAllAvatars()[6].name
        lblAvatar8.text = Database.queryAllAvatars()[7].name
        lblAvatar9.text = Database.queryAllAvatars()[8].name

    }

    private fun getIntentData(){
        selectedAvatar = intent.getIntExtra(EXTRA_AVATAR, 0)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.avatar_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSelect) {
            setActivityResult(selectedAvatar)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun selectAvatar(id: Int){
        uncheckAvatar(selectedAvatar)
        when(id){
            0 -> chkAvatar1.isChecked = true
            1 -> chkAvatar2.isChecked = true
            2 -> chkAvatar3.isChecked = true
            3 -> chkAvatar4.isChecked = true
            4 -> chkAvatar5.isChecked = true
            5 -> chkAvatar6.isChecked = true
            6 -> chkAvatar7.isChecked = true
            7 -> chkAvatar8.isChecked = true
            8 -> chkAvatar9.isChecked = true
        }
        selectedAvatar = id
    }

    private fun uncheckAvatar(id: Int){
        when(id){
            0 -> chkAvatar1.isChecked = false
            1 -> chkAvatar2.isChecked = false
            2 -> chkAvatar3.isChecked = false
            3 -> chkAvatar4.isChecked = false
            4 -> chkAvatar5.isChecked = false
            5 -> chkAvatar6.isChecked = false
            6 -> chkAvatar7.isChecked = false
            7 -> chkAvatar8.isChecked = false
            8 -> chkAvatar9.isChecked = false
        }
    }

    companion object {
        const val EXTRA_AVATAR = "EXTRA_AVATAR"

        fun newIntent(context: Context, id: Int): Intent =

            Intent(context, AvatarActivity::class.java).putExtra(EXTRA_AVATAR, id)
    }

    private fun setActivityResult(id: Int){
        val result = Intent().putExtra(EXTRA_AVATAR, id)
        setResult(Activity.RESULT_OK, result)
    }



}


