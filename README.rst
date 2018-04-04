fuud?
========

App + server lahendus, mis toob kõikide TTÜ söögikohtade päevapakkumised ja menüü mugavalt ühte kohta kokku.

Liikmed
--------

- Robert Männik (robert.mannik)
- Raimond Lume (ralume)
- Janek Valga (javalg)

Funktsionaalsus
---------------

**Server**

- sissetuleva info (päevapakkumised, menüüd) *parse*’imine
- vastava info väljastamine API kaudu GET funktsionaalsusega
- väljastatav info *json* kujul
- kasutab Spring *framework*i
- Söögikohad
    - Rahva Toit (SOC ja LIB)
    - Daily (Peamaja, ICT, U04, Küberneetika maja)
    - Pööning
    - BitStop
    - Drumsticks?
    
Üldine struktuur kuidas java programmid väljastavad andmeid:
ArrayList<HashMap>
[    
    {
    provider="söögikoht";
    name_est="toidu nimi";
    name_eng="nimi inglise keeles";
    price= "hind";
    },
    {
    ...
    }
]

**Rakendus**

- Android rakendus
- Saab serverilt info
- Sorteeritud päevapakkumiste kuvamine
    - Söögikoha alusel
    - Hinna alusel
- Material design
- Toiduratingud (upvote-downvote laadne)
- Kasutajasüsteem?
    - Facebook
    - Google
    - Uni-ID?
    - Viimane variant e-mail
- Kommentaarid?


Ekraanivaated
-------------

**Avakuva**
Suured nupud söögikoha valimiseks
Nupp üldvaate juurde

**Söögikoha vaade**
Kõik vastava söögikoha pakkumised, vaikimisi hinna järgi kasvavalt
Võimalus filtreerida *vegan*-toite

**Üldvaade**
Kõikide söögikohtade pakkumised, vaikimisi sorteeritud hinna järgi kasvavalt
Võimalus pakkumisi filtreerida söögikohtade järgi (checkmarks)
Vegantoidu *tag* - kuvatakse ainult vegantoidud

**Seaded**
Light/Dark theme valik


Plaan
-----

- \4. nädal: projektiplaaniga alustamine, tiimi komplekteerimine
- \5. nädal: ideede genereerimine, mõne ekraanivisandi koostamine
- \6. nädal: projektiplaani esitamine
- \7. nädal: söögikohtadega kontakt, Springi ja Android DEV studioga tutvumine
- \8. nädal: frameworkide ja töökeskkondade ülesseadmine
- \9. nädal: serveri funktsionaalsus - info parse’imine ja väljastamine
- \10. nädal: rakenduse kesta loomine, kaardipõhise funktsionaalsuse arendamine
- \11. nädal: arenduse jätkumine, võimalik lisafunktsionaalsuste lisamine
- \12. nädal: Esitamine
- \13. nädal: parandused/täiendused
- \14. nädal: esimene release-candidate
- \15. nädal: parandused/täiendused
- \16. nädal: Lõplik esitamine

Kasutatav tehnoloogia
----------------------

Android rakendus +
Spring raamistikuga server


Punktisoov
----------

10 punkti
