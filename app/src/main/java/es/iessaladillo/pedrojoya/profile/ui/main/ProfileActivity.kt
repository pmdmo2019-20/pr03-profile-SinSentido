package es.iessaladillo.pedrojoya.profile.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
//import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import es.iessaladillo.pedrojoya.profile.utils.*
import kotlinx.android.synthetic.main.profile_activity.*
import java.io.Serializable

//import kotlinx.android.synthetic.main.profile_avatar.*
//import kotlinx.android.synthetic.main.profile_form.*

const val EXTRA_AVATAR = "EXTRA_AVATAR"
const val RC_AVATAR = 1

class ProfileActivity : AppCompatActivity() {
    var avatar: Avatar = Database.queryDefaultAvatar()

    private lateinit var txtName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPhoneNumber: EditText
    private lateinit var txtAddress: EditText
    private lateinit var txtWeb: EditText

    private lateinit var imgAvatar: ImageView
    private lateinit var lblAvatar: TextView

    private lateinit var imgEmail: ImageView
    private lateinit var imgPhonenumber: ImageView
    private lateinit var imgAddress: ImageView
    private lateinit var imgWeb: ImageView

    private lateinit var viewModel: ProfileActivityViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        setupViews()
        viewModel = ViewModelProvider(this).get(ProfileActivityViewModel::class.java)

        if (savedInstanceState != null) {
            avatar = Database.queryAllAvatars()[savedInstanceState.getInt("AVATAR")-1]
        }

        //Set avatar properties
        lblAvatar.text = avatar.name
        imgAvatar.setImageResource(avatar.imageResId)
        imgAvatar.setOnClickListener { navigateToAvatarActivity(avatar.id) }

        //intents
        imgEmail.setOnClickListener { startActivity(newEmailIntent(txtEmail.text.toString())) }
        imgPhonenumber.setOnClickListener { startActivity(newDialIntent(txtPhoneNumber.text.toString())) }
        imgAddress.setOnClickListener { startActivity(newSearchInMapIntent(txtAddress.text.toString())) }
        imgWeb.setOnClickListener { startActivity(newViewUriIntent(Uri.parse(txtWeb.text.toString()))) }
    }

    private fun setupViews(){
        //EditText
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPhoneNumber = findViewById(R.id.txtPhonenumber)
        txtAddress = findViewById(R.id.txtAddress)
        txtWeb = findViewById(R.id.txtWeb)

        //Avatar Image
        imgAvatar = findViewById(R.id.imgAvatar)
        lblAvatar = findViewById(R.id.lblAvatar)

        //Intents
        imgEmail = findViewById(R.id.imgEmail)
        imgPhonenumber = findViewById(R.id.imgPhonenumber)
        imgAddress = findViewById(R.id.imgAddress)
        imgWeb = findViewById(R.id.imgWeb)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        super.onActivityResult(requestCode, resultCode, intent)

        if (resultCode == RESULT_OK && requestCode == RC_AVATAR && intent != null) {
            avatar = Database.queryAllAvatars()[intent.getIntExtra(AvatarActivity.EXTRA_AVATAR, 0)]
            imgAvatar.setImageResource(avatar.imageResId)
            lblAvatar.text = avatar.name
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("AVATAR", avatar.id)

        super.onSaveInstanceState(outState)
    }

    private fun navigateToAvatarActivity(avatarId: Int){
        val intent = AvatarActivity.newIntent(this, avatarId-1)
        startActivityForResult(intent, RC_AVATAR)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSave) {
            save()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        if(txtName.text.toString() == ""){ //If invalid name
            txtName.error = getString(R.string.profile_invalid_name)
            txtName.requestFocus()
        }
        else if(!txtEmail.text.toString().isValidEmail()){ //If invalid email
            txtEmail.error = getString(R.string.profile_invalid_email)
            txtEmail.requestFocus()
        }
        else if(!txtPhoneNumber.text.toString().isValidPhone()){ //If invalid phone number
            txtPhoneNumber.error = getString(R.string.profile_invalid_phonenumber)
            txtPhoneNumber.requestFocus()
        }
        else if(txtAddress.text.toString() == ""){ //If invalid address
            txtAddress.error = getString(R.string.profile_invalid_address)
            txtAddress.requestFocus()
        }
        else if(!txtWeb.text.toString().isValidUrl()){ //If invalid web
            txtWeb.error = getString(R.string.profile_invalid_web)
            txtWeb.requestFocus()
        }
        else{ //If all fields are valid
            Toast.makeText(this, getText(R.string.userSaved), Toast.LENGTH_SHORT).show()
        }
    }

}
