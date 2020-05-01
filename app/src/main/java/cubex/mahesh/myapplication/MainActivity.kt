package cubex.mahesh.myapplication

import android.app.ProgressDialog
import android.app.Service
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    fun getImage(view: View) {
      var mt = MyTask(iview)
        mt.execute()
        var s: Service? = null
    }

    class MyTask(var iView:ImageView) : AsyncTask<Unit,Unit,Unit>()
    {
        var pDialog:ProgressDialog? = null
        var isr:InputStream? = null
        override fun onPreExecute() {
            super.onPreExecute()
            pDialog?.setTitle("Message")
            pDialog?.setMessage("Please wait page is loading...")
            pDialog?.show()
        }

        override fun doInBackground(vararg params: Unit?) {
            var _u = URL("https://www.photographyblog.com/uploads/entryImages/_550xAUTO_fit_center-center_90/samyang_af_24mm_f2_8_fe_photos.jpg")
             isr = _u.openStream()
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            pDialog?.dismiss()
            var bmp = BitmapFactory.decodeStream(isr)
            iView.setImageBitmap(bmp)
        }
    }
}
