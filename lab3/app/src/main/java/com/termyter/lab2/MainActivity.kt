package com.termyter.lab2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.termyter.lab2.databinding.ActivityMainBinding
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.StateSet.TAG
import androidx.core.graphics.drawable.toDrawable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {

    val URL = "https://anime-facts-rest-api.herokuapp.com/api/v1"
    val FACTS = ""
    var okHttpClient: OkHttpClient = OkHttpClient()

    private val adapter = AnimesAdapter()
    private val pictureGallery = arrayListOf(
        Anime(
            R.drawable.card1,
            "",
            ""),
//        Anime(
//            R.drawable.card2,
//            "Эхо террора",
//            "Японскую столицу терроризирует группа «Сфинкс». Ее представители – два паренька в масках – появляются в соцсетях и открыто предупреждают о готовящихся сюрпризах. Вне экрана они называют друг друга английскими прозвищами Найн и Твелв, имитируя обычных школьников. Картинные и дерзкие теракты ставят на уши полицию, рвутся бомбы, ревут сирены… при этом ребята в масках все больше наглеют и начинают шифровать свои предупреждения, используя мировую классику. Как водится, террористы легко ломают любые системы безопасности, потому из Америки на помощь коллегам прибывает бригада ФБР во главе с гениальной хакершей Файв.",
//        ),
//        Anime(
//            R.drawable.card3,
//            "Твоё имя",
//            "Действие в аниме Твое имя будет происходить в двух городах. Главными героями является парочка обычных людей, которые даже не подозревают, что вскоре судьба свяжет их жизни очень крепко. Они никогда не были знакомы, она – любимая дочь, которая свое свободное время посвящает работе в храме. Он – надежный друг, успешен и трудолюбив на работе, имеет неплохие задатки талантливого художника. Ее жизнь спокойна и размерена в небольшом провинциальном городке, в то время как он живет в огромном мегаполисе, полном суматохи и постоянном бегстве.",
//        ),
//        Anime(
//            R.drawable.card4,
//            "Ванпанчмен",
//            "Мы все привыкли, что комиксы наполнены различными злодеями, безумцами с планом захвата планеты и прочими неприятностями. Город Зет-Сити уже давно привык к такому роду событий, постоянными захватчиками из других миров и прочими бедствиями. Самое важное, чтобы всегда вовремя появился супергерой, которой справится со всеми проблемами и сможет победить всех злодеев. Таким героем является парень по имени Сайтама. Вот только он выбивается из общего фона в жизни типичного города из комикса. Он невысокий, не может вести себя достаточно пафосно и не говорит высокопарных речей. Более того, внешний вид нашего героя состоит из простой одежды, а на голове нет ни одной волосинки.",
//        ),
//        Anime(
//            R.drawable.card5,
//            "Форма голоса",
//            "Каждый человек может совершать ошибки, но не все могут это осознать, потом они испытывают стыд. Сюжет данного аниме расскажет историю о мальчике Шоя Ишида и девочки Шоко Нишимия. У девочки с детства были проблемы со слухом, и ей пришлось из-за этого много страдать. Повествование берет начало в школе, с их знакомства, маленькая Шоко переводится к Шоя. Она изначально не нравится большинству, из-за того, что отличалась от других. Тогда он принимает решение испортить ей жизнь и издевается над ней.",
//        ),
//        Anime(
//            R.drawable.card6,
//            "Тетрадь смерти",
//            "Ягами Лайт – обычный старшеклассник, у которого довольно неплохая жизнь. Ему пророчат блестящее будущее, сверстницы одаривают его вниманием, учеба идет как нельзя лучше, а дома царит мир. Но все меняется, когда в его руки попадает странная черная тетрадь смерти, принадлежащая шинигами Рюку. Тогда парень, ослепленный желанием избавить мир от зла и несправедливости, решает сыграть в Бога и решать, кому жить, а кому нет.",
//        ),
//        Anime(
//            R.drawable.card7,
//            "Дороро",
//            "События разворачиваются в феодальной Японии, когда страна погружается в страшный военный водоворот. На полуострове под названием Ното ожидается появление на свет наследника генерала-самурая. Отважный Дайго Кагемитсу – преданный державе воин, готовый рисковать собственной жизнью ради защиты родины. Он находит метод, способный привести его к реализации заветного желания. Мужчина жертвует могущественным демонам не рожденного младенца. В результате мальчишка рождается с сильнейшими физическими пороками. Коварные не приятели отняли у ребенка многие части тела. Родитель, увидевший уродливого сына, принимает решение избавиться от него.",
//        ),
//        Anime(
//            R.drawable.card8,
//            "Магическая битва",
//            "Юдзи Итадори — сильный юноша, который ведёт заурядную жизнь старшеклассника. Однажды, чтобы спасти друзей от нападения проклятий, он съедает палец Двуликого призрака, который становится частью его души. С этого момента он делит с ним своё тело. Под присмотром Сатору Годзё, одного из сильнейших магов, Итадори поступает в магический техникум, где учат сражаться с проклятиями.",
//        ),
//        Anime(
//            R.drawable.card9,
//            "Баскетбол Куроко",
//            "Все заядлые баскетболисты прекрасно знают знаменитую школу Тэйко, где есть особенный клуб, прославившийся своими грандиозными победами. Но в определенный момент пятерка выдающихся игроков разошлась по разным сторонам. Только немногие были осведомлены, что в известной команде был шестой участник (фантом), который тоже обладал поразительными способностями, но, к сожалению, оставался в тени более успешных коллег.",
//        ),
//        Anime(
//            R.drawable.card10,
//            "Бездомный бог",
//            "Ято бродячий японский бог, недавно лишившийся абсолютно всего. Главный герой спустился на землю и старается сделать всё возможное, чтобы выжить. Оказывается, что каждому божественному созданию необходимы люди, верующие в него. Это прибавляет невероятное количество сил, но для Ято всё складывается слишком печально. У него нет почитателей и в добавок его бросает Мая, являющаяся его оружием. Приходится прятаться от демонов, старающихся убить любого, кто окажется на их пути.",
//        ),
    )
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadRandomFact()

    }

    private fun init(){
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }
        adapter.addPicture(pictureGallery)
    }

    private fun loadRandomFact() {


        val request: Request = Request.Builder().url(URL).build()

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()!!.string()
                try {
                    val jsonResponse = JSONObject(responseBody)
                    val jsonQuestions = jsonResponse.getJSONArray("data")
                    for (i in 0 until jsonQuestions.length()) {
                        val jsonQuestion = jsonQuestions.getJSONObject(i)
                        val text = jsonQuestion.get("anime_name").toString()
                        val img = jsonQuestion.get("anime_img").toString()
//                        val url: URL = URL(img)
//                        val bitmap: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//                        fileoutputestream
//                        pictureGallery.add(Anime( bitmap.compress(Bitmap.CompressFormat.JPEG,100,"rcfbcb"),text,text))
                        pictureGallery.add(Anime(  R.drawable.card1,text,text))
//                        pictureGallery[i].name = text
                    }
                    runOnUiThread {
                        init()

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

        })

    }
}
