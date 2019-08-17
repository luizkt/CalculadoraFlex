package br.com.fiap.calculadoraflexlkt.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import br.com.fiap.calculadoraflexlkt.R
import br.com.fiap.calculadoraflexlkt.ui.form.FormActivity
import br.com.fiap.calculadoraflexlkt.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDO_SPLASHSCREEN = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isFirstOpen = preferences.getBoolean("open_first", true)

        if(isFirstOpen) {
            showLogin()
        }
        else {
            markAppAlreadyOpen(preferences)
            showSplash()
        }
    }

    private fun markAppAlreadyOpen(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("open_first", false)
        editor.apply()
    }

    private fun showLogin() {
        val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextScreen)
        finish()
    }

    private fun showSplash() {
        // Carrega a animação
        val animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        animacao.reset()
        ivLogo.clearAnimation()
        // Roda a animação
        ivLogo.startAnimation(animacao)
        // Chama a proxima tela apos TEMPO_AGUARDO_SPLASHSCREEN segundos
        Handler().postDelayed({
            val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(nextScreen)
            finish()
        }, TEMPO_AGUARDO_SPLASHSCREEN)
    }
}
