package com.mikirinkode.kotakfilmlatihan.utils

import com.mikirinkode.kotakfilmlatihan.data.model.GenresItem
import com.mikirinkode.kotakfilmlatihan.data.model.MovieEntity
import com.mikirinkode.kotakfilmlatihan.data.model.TvShowEntity
import com.mikirinkode.kotakfilmlatihan.data.source.remote.response.*


object DataDummy {

    fun generateRemoteDummyMovies(): MovieListResponse {
        return MovieListResponse(
            listOf(
                MovieItems(
                    399579,
                    "Alita: Battle Angel",
                    "Alita: Battle Angel",
                    "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                    "2019-01-31",
                    "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                    "https://www.themoviedb.org/t/p/original/vPDuinnoNqWiLVzNNyNj2zdB5VS.jpg",
                    7.2
                ),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
                MovieItems(0, "", "", "", "", "", "", 0.0),
            )
        )
    }

    fun generateRemoteDummyMovieDetail(): MovieDetailResponse {
        return MovieDetailResponse(
            399579,
            "Alita: Battle Angel ",
            "2019-01-31",
            "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "An angel falls. A warrior rises.",
            listOf(
                GenresItem(28, "Action"),
                GenresItem(878, "Science Fiction"),
                GenresItem(12, "Adventure")
            ),
            122,
            7.2,
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
            "https://www.themoviedb.org/t/p/original/vPDuinnoNqWiLVzNNyNj2zdB5VS.jpg",
        )
    }

    fun generateRemoteDummyTvShows(): TvShowListResponse {
        return TvShowListResponse(
            listOf(
                TvItems(
                    79501,
                    "Doom Patrol",
                    "Doom Patrol",
                    "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                    "2019-02-15",
                    "https://www.themoviedb.org/t/p/original/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
                    "https://www.themoviedb.org/t/p/original/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg",
                    7.7
                ),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
                TvItems(0, "", "", "", "", "", "", 0.0),
            )
        )
    }

    fun generateRemoteDummyTvDetail(): TvShowDetailResponse{
        return TvShowDetailResponse(
            79501,
            "Doom Patrol",
            "2019-02-15",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "",
            listOf(GenresItem(	10765, "Sci-Fi & Fantasy"), GenresItem(18, "Drama")),
            listOf(49),
            7.7,
            "https://www.themoviedb.org/t/p/original/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
            "https://www.themoviedb.org/t/p/original/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg",
        )
    }

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                399579,
                "Alita: Battle Angel ",
                "2019-01-31",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "An angel falls. A warrior rises.",
                "Action, Science Fiction, Adventure",
                122,
                7.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "https://www.themoviedb.org/t/p/original/vPDuinnoNqWiLVzNNyNj2zdB5VS.jpg",
                false
            )
        )

        movies.add(
            MovieEntity(
                2,
                "Bohemian Rhapshody",
                "October 24, 2018",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Fearless lives forever",
                "Music, Drama, History",
                135,
                8.0,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg",
                "https://www.themoviedb.org/t/p/original/93xA62uLd5CwMOAs37eQ7vPc1iV.jpg"
            )
        )

        movies.add(
            MovieEntity(
                3,
                "Cold Pursuit",
                "February 7, 2019",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Meet Nels Coxman. Citizen of the Year.",
                "Action, Crime, Thriller",
                119,
                5.7,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
                "https://www.themoviedb.org/t/p/original/3nRAYvw47ymAC7isQg12av1Dgeg.jpg"
            )
        )

        movies.add(
            MovieEntity(
                4,
                "Creed II",
                "November 21, 2018",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "There's More to Lose than a Title",
                "Drama",
                130,
                6.9,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
                "https://www.themoviedb.org/t/p/original/8yqLPNwNCtpOPc3XkOlkSMnghzw.jpg"
            )
        )


        movies.add(
            MovieEntity(
                5,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "November 14, 2018",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "Fate of One. Future of All.",
                "Adventure, Fantasy, Drama",
                134,
                6.9,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg",
                "https://www.themoviedb.org/t/p/original/tbaqzcRM8X1iPjtYlk7S7GpGZwr.jpg"
            )
        )


        movies.add(
            MovieEntity(
                6,
                "Master Z: Ip Man Legacy",
                "December 20, 2018",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "",
                "Action",
                107,
                6.2,
                "https://www.themoviedb.org/t/p/original/6VxEvOF7QDovsG6ro9OVyjH07LF.jpg",
                "https://www.themoviedb.org/t/p/original/grtVFGJ4ts0nDAPpc1JWbBoVKTu.jpg"
            )
        )


        movies.add(
            MovieEntity(
                7,
                "Mortal Engines",
                "November 27, 2018",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "Some scars never heal",
                "Adventure, Science Fiction",
                129,
                6.2,
                "https://www.themoviedb.org/t/p/original/gLhYg9NIvIPKVRTtvzCWnp1qJWG.jpg",
                "https://www.themoviedb.org/t/p/original/jnOuttTfG9KKpmOZtprC4pA1AlZ.jpg"
            )
        )



        movies.add(
            MovieEntity(
                8,
                "Ralph Breaks the Internet",
                "November 20, 2018",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "Who Broke the Internet?",
                "Family, Animation, Comedy, Adventure",
                112,
                7.2,
                "https://www.themoviedb.org/t/p/original/lvfIaThG5HA8THf76nghKinjjji.jpg",
                "https://www.themoviedb.org/t/p/original/x4W3fxK0Fes8WgVZcuJP8EoYKX2.jpg"
            )
        )


        movies.add(
            MovieEntity(
                9,
                "Spider-Man: Into the Spider-Verse",
                "December 6, 2018",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "More than one wears the mask.",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                117,
                8.4,
                "https://www.themoviedb.org/t/p/original/3cZn1k8x0bikrDKEy9ZKJ6Vdj30.jpg",
                "https://www.themoviedb.org/t/p/original/jqwM0nhOLEFI1HHBabwr80Od3TC.jpg"
            )
        )

        movies.add(
            MovieEntity(
                10,
                "T-34",
                "December 27, 2018",
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "Fast And Furious On Tanks",
                "War, Action, Drama, History",
                139,
                7.0,
                "https://www.themoviedb.org/t/p/original/rbqzJno5ClPUjlKTmweKsb9BKHV.jpg",
                "https://www.themoviedb.org/t/p/original/daJjtHlqvph7R1Uw1DFUIF0FJe4.jpg"
            )
        )


        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                79501,
                "Doom Patrol",
                "2019-02-15",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "",
                "Sci-Fi & Fantasy, Drama",
                49,
                7.7,
                "https://www.themoviedb.org/t/p/original/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
                "https://www.themoviedb.org/t/p/original/sAzw6I1G9JUxm86KokIDdQeWtaq.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                202,
                "Dragon Ball",
                "February 26, 1986",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                25,
                8.2,
                "https://www.themoviedb.org/t/p/original/hIR57n83XTnQhQYiZMml13ApgDX.jpg",
                "https://www.themoviedb.org/t/p/original/yXggMemopUDHwPgmi6X9Wh2BQra.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                203,
                "Gotham",
                "September 22, 2014",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Before Batman, there was Gotham.",
                "Drama, Crime, Sci-Fi & Fantasy",
                43,
                7.6,
                "https://www.themoviedb.org/t/p/original/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
                "https://www.themoviedb.org/t/p/original/z3WFpLmM86Mtq3emm4Tgcm6NYRV.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                204,
                "Hanna",
                "March 28, 2019",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "",
                "Action & Adventure, Drama",
                50,
                7.6,
                "https://www.themoviedb.org/t/p/original/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg",
                "https://www.themoviedb.org/t/p/original/ofjZbud67zO2wxQ48VgMVnkECQu.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                205,
                "Marvel's Iron Fist",
                "March 17, 2017",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                55,
                6.6,
                "https://www.themoviedb.org/t/p/original/4l6KD9HhtD6nCDEfg10Lp6C6zah.jpg",
                "https://www.themoviedb.org/t/p/original/aVGOpcBLAT8tpjqxDLyqQX5LEt3.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                206,
                "Naruto Shippūden",
                "February 15, 2007",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                25,
                8.6,
                "https://www.themoviedb.org/t/p/original/zAYRe2bJxpWTVrwwmBc00VFkAf4.jpg",
                "https://www.themoviedb.org/t/p/original/dTFnU3EQB79aDM4HnUj06Y9Xbq1.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                207,
                "NCIS",
                "September 23, 2003",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "",
                "Crime, Action & Adventure, Drama",
                45,
                7.5,
                "https://www.themoviedb.org/t/p/original/eoj15m14Zpf2bUWXqNIs7itZK9w.jpg",
                "https://www.themoviedb.org/t/p/original/ms8XxpJwTPYaUcbwhO2kJS6SGVM.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                208,
                "Shameless",
                "January 9, 2011",
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "The Gallaghers. Absolutely, Wildly, Unapologetically... Shameless",
                "Drama, Comedy",
                57,
                8.0,
                "https://www.themoviedb.org/t/p/original/iRyQTp2L437k6zfFCvZSOWaxQFA.jpg",
                "https://www.themoviedb.org/t/p/original/zjOj2gnDJYFdYt6R7FtuHn7yrPr.jpg"
            )
        )

        tvShows.add(
            TvShowEntity(
                209,
                "Supernatural",
                "September 13, 2005",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way. ",
                "Between darkness and deliverance",
                "Drama, Mystery, Sci-Fi & Fantasy",
                45,
                8.2,
                "https://www.themoviedb.org/t/p/original/rp7yEfhHLiLG1YxEclj1urc5bTR.jpg",
                "https://www.themoviedb.org/t/p/original/jmdb1L2ioHz5eC0yQJpiSylNk97.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                210,
                "The Walking Dead",
                "October 31, 2010",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Fight the dead. Fear the living.",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                42,
                8.1,
                "https://www.themoviedb.org/t/p/original/cmUYK9EJuLivvBlCmIqxgoSXRiF.jpg",
                "https://www.themoviedb.org/t/p/original/5iX0BTcbWQWD0LPUz1fBotc6niC.jpg"
            )
        )
        return tvShows
    }
}

