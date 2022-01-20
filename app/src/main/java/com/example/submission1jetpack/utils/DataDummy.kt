package com.example.submission1jetpack.utils

import com.example.submission1jetpack.data.MovieEntity
import com.example.submission1jetpack.data.TvShowEntity
import com.example.submission1jetpack.data.source.remote.response.MovieResponse
import com.example.submission1jetpack.data.source.remote.response.TvShowResponse

object DataDummy {

    fun generateMovieData(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "399579",
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "02/14/2019",
                "2h 2m",
                "https://www.themoviedb.org/t/p/w1280/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "297802",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "12/26/2018",
                "2h 23m",
                "https://www.themoviedb.org/t/p/w1280/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "299536",
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "04/27/2018",
                "2h 29m",
                "https://www.themoviedb.org/t/p/w1280/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "324857",
                "Spider-Man: Into the Spider-Verse",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "12/14/2018",
                "1h 57m",
                "https://www.themoviedb.org/t/p/w1280/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "166428",
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "01/09/2019",
                "1h 44m",
                "https://www.themoviedb.org/t/p/w1280/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "424694",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "30/10/2018",
                "2h 15m",
                "https://www.themoviedb.org/t/p/w1280/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "404368",
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "21/11/2018",
                "1h 52m",
                "https://www.themoviedb.org/t/p/w1280/44cb3fCGKUaSxxIjI2ejrgeYfye.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "438650",
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "08/02/2019",
                "1h 59m",
                "https://www.themoviedb.org/t/p/w1280/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "452832",
                "Serenity",
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                "25/01/2019",
                "1h 42m",
                "https://www.themoviedb.org/t/p/w1280/hgWAcic93phg4DOuQ8NrsgQWiqu.jpg"
            )
        )
        movies.add(
            MovieEntity(
                "505954",
                "T-34",
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "01/01/2019",
                "",
                "https://www.themoviedb.org/t/p/w1280/jqBIHiSglRfNxjh1zodHLa9E7zW.jpg"
            )
        )

        return movies
    }

    fun generateTvShowsData(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                "456",
                "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                "1989",
                "22m",
                "https://www.themoviedb.org/t/p/w1280/tubgEpjTUA7t0kejVMBsNBZDarZ.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                "2",
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "1999",
                "22m",
                "R.drawable.poster_family_guy"
            )
        )
        tvShows.add(
            TvShowEntity(
                "3",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014",
                "44m",
                "R.drawable.poster_flash"
            )
        )
        tvShows.add(
            TvShowEntity(
                "4",
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "1986",
                "25m",
                "R.drawable.poster_dragon_ball"
            )
        )
        tvShows.add(
            TvShowEntity(
                "5",
                "Naruto Shippūden",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "2007",
                "25m",
                "R.drawable.poster_naruto_shipudden"
            )
        )
        tvShows.add(
            TvShowEntity(
                "6",
                "The Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "2012",
                "42m",
                "R.drawable.poster_arrow"
            )
        )
        tvShows.add(
            TvShowEntity(
                "7",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017",
                "45m",
                "R.drawable.poster_riverdale"
            )
        )
        tvShows.add(
            TvShowEntity(
                "8",
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "2014",
                "43m",
                "R.drawable.poster_gotham"
            )
        )
        tvShows.add(
            TvShowEntity(
                "9",
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "2003",
                "45m",
                "R.drawable.poster_ncis"
            )
        )
        tvShows.add(
            TvShowEntity(
                "10",
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "2019",
                "50m",
                "R.drawable.poster_hanna"
            )
        )

        return tvShows
    }

    fun generateRemoteDummyMovies(): List<MovieResponse> {
        val movies = ArrayList<MovieResponse>()

        movies.add(
            MovieResponse(
                id = 399579,
                title = "Alita: Battle Angel",
                overview = "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                releaseDate = "02/14/2019",
                runtime = 122,
                posterPath = "https://www.themoviedb.org/t/p/w1280/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 297802,
                title = "Aquaman",
                overview = "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                releaseDate = "12/26/2018",
                runtime = 143,
                posterPath = "https://www.themoviedb.org/t/p/w1280/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 299536,
                title = "Avengers: Infinity War",
                overview = "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                releaseDate = "04/27/2018",
                runtime = 149,
                posterPath = "https://www.themoviedb.org/t/p/w1280/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 324857,
                title = "Spider-Man: Into the Spider-Verse",
                overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                releaseDate = "12/14/2018",
                runtime = 117,
                posterPath = "https://www.themoviedb.org/t/p/w1280/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 166428,
                title = "How to Train Your Dragon: The Hidden World",
                overview = "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                releaseDate = "01/09/2019",
                runtime = 104,
                posterPath = "https://www.themoviedb.org/t/p/w1280/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 424694,
                title = "Bohemian Rhapsody",
                overview = "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                releaseDate = "30/10/2018",
                runtime = 135,
                posterPath = "https://www.themoviedb.org/t/p/w1280/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 404368,
                title = "Ralph Breaks the Internet",
                overview = "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                releaseDate = "21/11/2018",
                runtime = 112,
                posterPath = "https://www.themoviedb.org/t/p/w1280/44cb3fCGKUaSxxIjI2ejrgeYfye.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 438650,
                title = "Cold Pursuit",
                overview = "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                releaseDate = "08/02/2019",
                runtime = 119,
                posterPath = "https://www.themoviedb.org/t/p/w1280/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 452832,
                title = "Serenity",
                overview = "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                releaseDate = "25/01/2019",
                runtime = 102,
                posterPath = "https://www.themoviedb.org/t/p/w1280/hgWAcic93phg4DOuQ8NrsgQWiqu.jpg"
            )
        )
        movies.add(
            MovieResponse(
                id = 505954,
                title = "T-34",
                overview = "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                releaseDate = "01/01/2019",
                runtime = 130,
                posterPath = "https://www.themoviedb.org/t/p/w1280/jqBIHiSglRfNxjh1zodHLa9E7zW.jpg"
            )
        )


        return movies
    }

    fun generateRemoteDummyTvShows(): List<TvShowResponse> {
        val tvShows = ArrayList<TvShowResponse>()

        tvShows.add(
            TvShowResponse(
                id = 456,
                name = "The Simpsons",
                overview = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                firstAirDate = "1989",
                episodeRunTime = listOf(22),
                posterPath = "https://www.themoviedb.org/t/p/w1280/tubgEpjTUA7t0kejVMBsNBZDarZ.jpg"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 2,
                name = "Family Guy",
                overview = "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                firstAirDate = "1999",
                episodeRunTime = listOf(22),
                posterPath = "R.drawable.poster_family_guy"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 3,
                name = "The Flash",
                overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                firstAirDate = "2014",
                episodeRunTime = listOf(44),
                posterPath = "R.drawable.poster_flash"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 4,
                name = "Dragon Ball",
                overview = "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                firstAirDate = "1986",
                episodeRunTime = listOf(25),
                posterPath = "R.drawable.poster_dragon_ball"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 5,
                name = "Naruto Shippūden",
                overview = "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                firstAirDate = "2007",
                episodeRunTime = listOf(25),
                posterPath = "R.drawable.poster_naruto_shipudden"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 6,
                name = "The Arrow",
                overview = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                firstAirDate = "2012",
                episodeRunTime = listOf(42),
                posterPath = "R.drawable.poster_arrow"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 7,
                name = "Riverdale",
                overview = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                firstAirDate = "2017",
                episodeRunTime = listOf(45),
                posterPath = "R.drawable.poster_riverdale"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 8,
                name = "Gotham",
                overview = "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                firstAirDate = "2014",
                episodeRunTime = listOf(43),
                posterPath = "R.drawable.poster_gotham"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 9,
                name = "NCIS",
                overview = "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                firstAirDate = "2003",
                episodeRunTime = listOf(45),
                posterPath = "R.drawable.poster_ncis"
            )
        )
        tvShows.add(
            TvShowResponse(
                id = 10,
                name = "Hanna",
                overview = "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                firstAirDate = "2019",
                episodeRunTime = listOf(50),
                posterPath = "R.drawable.poster_hanna"
            )
        )

        return tvShows
    }
}