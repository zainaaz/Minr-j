# Minr-j
# Mål
Skapa ett textbaserat Minröj-spel i Java som körs i terminalen.

Visa spelplanen i terminalen som ett rutnät med bokstäver (rader) och siffror (kolumner).

Slumpmässigt placera bomber på spelplanen.

Låta spelaren skriva in koordinater (t.ex. b2) för att undersöka en ruta.

Om spelaren väljer en ruta med bomb - visa Game Over och markera bomben.

Om spelaren öppnar alla säkra rutor - visa vinstmeddelande.

Visa antal angränsande bomber för öppnade rutor.

Automatiskt öppna angränsande rutor om en ruta har 0 angränsande bomber.

Låta spelaren välja dynamisk storlek på spelplanen.

# Funktioner

# Måste (implementerat i koden)

Visa spelplan i terminalen med bokstäver och siffror.

Slumpmässig placering av bomber.

Spelaren kan skriva in koordinater (t.ex. b2) för att öppna rutor.

Game Over om spelaren väljer en bomb.

Vinstmeddelande om alla säkra rutor öppnas.

Input-validering för att säkerställa korrekt inmatning.

# Önskas (implementerat i koden)

Visa antal angränsande bomber för öppna rutor.

Automatiskt öppna angränsande rutor om antal angränsande bomber = 0.

# Design (spelidé)

Spelmekanik

Spelplanen genereras (t.ex. 6x6).

Bomber slumpas ut.

Spelaren skriver in koordinater (ex: b3).

Om Bomb - Game Over.

Om inte bomb - visa antal bomber i närheten.

Fortsätt tills alla säkra rutor öppnats - “Du vann!”



