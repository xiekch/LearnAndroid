package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.postDelayed
import com.example.myapplication.R
import com.example.myapplication.ui.recyclerView.RecyclerIndexActivity
import com.example.myapplication.ui.ui.WhiteThemeActivity
import kotlinx.android.synthetic.main.activity_layout_index.button1
import kotlinx.android.synthetic.main.activity_layout_index.button2
import kotlinx.android.synthetic.main.activity_layout_index.button3
import kotlinx.android.synthetic.main.activity_layout_index.button4
import kotlinx.android.synthetic.main.activity_layout_index.button5
import kotlinx.android.synthetic.main.activity_layout_index.button6
import kotlinx.android.synthetic.main.activity_layout_index.button8
import kotlinx.android.synthetic.main.activity_layout_index.button9
import kotlinx.android.synthetic.main.activity_layout_index.buttonBlackTheme
import kotlinx.android.synthetic.main.activity_layout_index.buttonCanvas
import kotlinx.android.synthetic.main.activity_layout_index.buttonChangeTranslation
import kotlinx.android.synthetic.main.activity_layout_index.buttonDrawerLayout
import kotlinx.android.synthetic.main.activity_layout_index.buttonEnterFullScreen
import kotlinx.android.synthetic.main.activity_layout_index.buttonExitFullScreen
import kotlinx.android.synthetic.main.activity_layout_index.buttonFullScreen
import kotlinx.android.synthetic.main.activity_layout_index.buttonGestureDetector
import kotlinx.android.synthetic.main.activity_layout_index.buttonGoBackHome
import kotlinx.android.synthetic.main.activity_layout_index.buttonInflater
import kotlinx.android.synthetic.main.activity_layout_index.buttonKeyboard
import kotlinx.android.synthetic.main.activity_layout_index.buttonMyButton
import kotlinx.android.synthetic.main.activity_layout_index.buttonNavigation
import kotlinx.android.synthetic.main.activity_layout_index.buttonPopupWindow
import kotlinx.android.synthetic.main.activity_layout_index.buttonRelativeLayout
import kotlinx.android.synthetic.main.activity_layout_index.buttonViewPager2
import kotlinx.android.synthetic.main.activity_layout_index.buttonWhiteTheme
import kotlinx.android.synthetic.main.activity_layout_index.button_collapsing_toolbar
import kotlinx.android.synthetic.main.activity_layout_index.button_list_view
import kotlinx.android.synthetic.main.activity_layout_index.button_recycler_view

class UIIndexActivity : AppCompatActivity(), View.OnClickListener, OnTouchListener {
    private var popupWindow: PopupWindow? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_index)

        initListener()
    }

    private fun initListener() {
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        buttonMyButton.setOnClickListener(this)
        buttonMyButton.setOnTouchListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        button_collapsing_toolbar.setOnClickListener(this)
        button_list_view.setOnClickListener(this)
        button_recycler_view.setOnClickListener(this)
        buttonKeyboard.setOnClickListener(this)
        buttonFullScreen.setOnClickListener(this)
        buttonDrawerLayout.setOnClickListener(this)
        buttonGestureDetector.setOnClickListener(this)
        buttonNavigation.setOnClickListener(this)
        buttonViewPager2.setOnClickListener(this)
        buttonRelativeLayout.setOnClickListener(this)
        buttonInflater.setOnClickListener(this)
        buttonEnterFullScreen.setOnClickListener(this)
        buttonExitFullScreen.setOnClickListener(this)
        buttonBlackTheme.setOnClickListener(this)
        buttonWhiteTheme.setOnClickListener(this)
        buttonChangeTranslation.setOnClickListener(this)
        buttonCanvas.setOnClickListener(this)
        buttonPopupWindow.setOnClickListener(this)
        buttonGoBackHome.setOnClickListener(this)
    }

    override fun onClick(v: View) {
//        Toast.makeText(LayoutIndexActivity.this, "click", Toast.LENGTH_SHORT).show();
        var intent: Intent? = null
        when (v.id) {
            R.id.button1 -> {
                intent = Intent(this@UIIndexActivity, SysuActivity::class.java)
                startActivity(intent)
            }
            R.id.button2 -> {
                intent = Intent(this@UIIndexActivity, WebViewActivity::class.java)
                intent.putExtra(EXTRA_TYPE, 0)
                startActivity(intent)
            }
            R.id.button3 -> {
                intent = Intent(this@UIIndexActivity, WebViewActivity::class.java)
                intent.putExtra(EXTRA_TYPE, 1)
                startActivity(intent)
            }
            R.id.button4 -> {
                val toast = Toast(applicationContext)
                val view = LayoutInflater.from(this@UIIndexActivity)
                    .inflate(R.layout.customized_toast_layout, null)
                toast.view = view
                toast.show()
            }
            R.id.button5 -> {
                intent = Intent(this@UIIndexActivity, AlertDialogActivity::class.java)
                startActivity(intent)
            }
            R.id.button6 -> {
                intent = Intent(this@UIIndexActivity, ProgressBarActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonMyButton -> {
                Toast.makeText(this, "my button onClick", Toast.LENGTH_SHORT).show()
            }
            R.id.button8 -> {
                intent = Intent(this@UIIndexActivity, ActionBarActivity::class.java)
                startActivity(intent)
            }
            R.id.button9 -> {
                intent = Intent(this@UIIndexActivity, ToolbarActivity::class.java)
                startActivity(intent)
            }
            R.id.button_list_view -> {
                intent = Intent(this@UIIndexActivity, ListViewActivity::class.java)
                startActivity(intent)
            }
            R.id.button_recycler_view -> startActivity(
                Intent(
                    this,
                    RecyclerIndexActivity::class.java
                )
            )
            R.id.button_collapsing_toolbar -> {
                intent = Intent(this@UIIndexActivity, CollapsingToolbarActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonKeyboard -> startActivity(Intent(this, KeyboardActivity::class.java))
            R.id.buttonFullScreen -> startActivity(Intent(this, FullscreenActivity::class.java))
            R.id.buttonDrawerLayout -> startActivity(Intent(this, DrawerLayoutActivity::class.java))
            R.id.buttonGestureDetector -> startActivity(Intent(this, GestureActivity::class.java))
            R.id.buttonNavigation -> startActivity(Intent(this, NavigationActivity::class.java))
            R.id.buttonViewPager2 -> startActivity(Intent(this, ViewPagerActivity::class.java))
            R.id.buttonRelativeLayout -> startActivity(
                Intent(this, RelativeLayoutActivity::class.java)
            )
            R.id.buttonInflater -> startActivity(Intent(this, InflaterActivity::class.java))
            R.id.buttonEnterFullScreen -> {
                window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN.or(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN))
            }
            R.id.buttonExitFullScreen -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
            R.id.buttonBlackTheme -> {
                startActivity(Intent(this, BlackThemeActivity::class.java))
            }
            R.id.buttonWhiteTheme -> {
                startActivity(Intent(this, WhiteThemeActivity::class.java))
            }
            R.id.buttonChangeTranslation -> {
                startActivity(Intent(this, ChangeTranslationActivity::class.java))
            }
            R.id.buttonCanvas -> {
                startActivity(Intent(this, CanvasActivity::class.java))
            }
            R.id.buttonPopupWindow -> {
                if (popupWindow == null) {
                    val view = LayoutInflater.from(this).inflate(R.layout.activity_dialog, null)
                    view.background = ColorDrawable(Color.LTGRAY)
                    popupWindow = PopupWindow(
                        view,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    popupWindow?.isOutsideTouchable = true
                }

                if (popupWindow?.isShowing == true) {
                    popupWindow?.dismiss()
                } else {
                    window.decorView.postDelayed(2000) {
                        popupWindow?.showAtLocation(
                            window.decorView,
                            Gravity.START,
                            0,
                            0
                        )
                    }
                }
            }
            R.id.buttonGoBackHome -> {
                val homeIntent = Intent(Intent.ACTION_MAIN)
                homeIntent.addCategory(Intent.CATEGORY_HOME)
                startActivity(homeIntent)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (v.id) {
            R.id.buttonMyButton -> {
                // listener is prior to callback MyButton.onTouchEvent
                Toast.makeText(this@UIIndexActivity, "onTouch ${event.action}", Toast.LENGTH_SHORT)
                    .show()
                Log.i("MyButton Listener", "onTouch ${event.action}")
            }
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.i("activity", "onTouchEvent")
        return super.onTouchEvent(event)
    }

    companion object {
        private const val TAG = "UIIndexActivity"
        const val EXTRA_TYPE = "com.example.myapplication.layout.type"
    }
}