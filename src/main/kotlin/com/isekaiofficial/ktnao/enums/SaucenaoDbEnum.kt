package com.isekaiofficial.ktnao.enums

import com.isekaiofficial.ktnao.json.DanbooruResult
import com.isekaiofficial.ktnao.json.DrawrResult
import com.isekaiofficial.ktnao.json.PixivResult
import com.isekaiofficial.ktnao.json.common.SaucenaoResult

/**
 * todo impl more result classes
 */
enum class SaucenaoDbEnum(val index: Int, val indexName: String, val resultClass: Class<out SaucenaoResult>? = null) {
    INDEX_HMAGS(0, "index_hmags"),

    // INDEX_RESERVED1(1, "index_reserved"),
    INDEX_HCG(2, "index_hcg"),
    INDEX_DDBOBJECTS(3, "index_ddbobjects"),
    INDEX_DDBSAMPLES(4, "index_ddbsamples"),
    INDEX_PIXIV(5, "index_pixiv", PixivResult::class.java), // *
    INDEX_PIXIVHISTORICAL(6, "index_pixivhistorical"), // *

    // INDEX_RESERVED2(7, "index_reserved"),
    INDEX_SEIGAILLUST(8, "index_seigaillust"), // *
    INDEX_DANBOORU(9, "index_danbooru", DanbooruResult::class.java),
    INDEX_DRAWR(10, "index_drawr", DrawrResult::class.java), // *
    INDEX_NIJIE(11, "index_nijie"), // *
    INDEX_YANDERE(12, "index_yandere"),
    INDEX_ANIMEOP(13, "index_animeop"),

    // INDEX_RESERVED3(14, "index_reserved"),
    INDEX_SHUTTERSTOCK(15, "index_shutterstock"),
    INDEX_FAKKU(16, "index_fakku"),
    INDEX_HMISC(17, "index_hmisc"),
    INDEX_2DMARKET(18, "index_2dmarket"),
    INDEX_MEDIBANG(19, "index_medibang"),
    INDEX_ANIME(20, "index_anime"),
    INDEX_HANIME(21, "index_hanime"),
    INDEX_MOVIES(22, "index_movies"),
    INDEX_SHOWS(23, "index_shows"),
    INDEX_GELBOORU(24, "index_gelbooru"),
    INDEX_KONACHAN(25, "index_konachan"),
    INDEX_SANKAKU(26, "index_sankaku"),
    INDEX_ANIMEPICTURES(27, "index_animepictures"),
    INDEX_E621(28, "index_e621"),
    INDEX_IDOLCOMPLEX(29, "index_idolcomplex"),
    INDEX_BCYILLUST(30, "index_bcyillust"),
    INDEX_BCYCOSPLAY(31, "index_bcycosplay"),
    INDEX_PORTALGRAPHICS(32, "index_portalgraphics"),
    INDEX_DA(33, "index_da"), // *
    INDEX_PAWOO(34, "index_pawoo"),
    INDEX_MADOKAMI(35, "index_madokami"),
    INDEX_MANGADEX(36, "index_mangadex"),
    ;

    companion object {
        fun fromIndex(index: Int): SaucenaoDbEnum? {
            return entries.firstOrNull { it.index == index }
        }
    }
}
