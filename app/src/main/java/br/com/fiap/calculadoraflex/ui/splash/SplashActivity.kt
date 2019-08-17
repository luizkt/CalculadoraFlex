package br.com.fiap.calculadoraflex.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import br.com.fiap.calculadoraflex.R
import br.com.fiap.calculadoraflex.ui.form.FormActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDO_SPLASHSCREEN = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        carregar()
    }

    private fun carregar() {
        // Carrega a animação
        val animacao = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        animacao.reset()
        ivLogo.clearAnimation()
        // Roda a animação
        ivLogo.startAnimation(animacao)
        // Chama a proxima tela apos TEMPO_AGUARDO_SPLASHSCREEN segundos
        Handler().postDelayed({
            val proximaTela = Intent(this@SplashActivity, FormActivity::class.java)
            startActivity(proximaTela)
            finish()
        }, TEMPO_AGUARDO_SPLASHSCREEN)
    }
}
