# OS
Operacinių sistemų laboratorinis darbas

Kompiliavimui naudoti:
![Kaip pasileist](https://raw.githubusercontent.com/IgnasJ/OS/master/compile.png "Kompiliavimas")

Užduotis | Svoris | Terminas
--- | --- | ---
[Virtualios ir realios mašinos projektas](http://kurti.lt/OS/?page=reikalavimai_1) |	0,75 | 2017 02 28
[Virtualios ir realios mašinos projekto realizacija](http://kurti.lt/OS/?page=reikalavimai_2) | 0,75 | 2017 03 25
[Multiprograminės operacinės sistemos projektas](http://kurti.lt/OS/?page=reikalavimai_3) | 1 | 2017 04 29
[Multiprograminės operacinės sistemos projekto realizacija](http://kurti.lt/OS/?page=reikalavimai_4) | 1,5 | 2017 05 27

# Projektuojama paketinė OS.

Virtualios mašinos procesoriaus komandos operuoja su duomenimis, esančiais registruose ir ar atmintyje. Yra komandos duomenų persiuntimui iš atminties į registrus ir atvirkščiai, aritmetinės (sudėties, atimties, palyginimo), sąlyginio ir besąlyginio valdymo perdavimo, įvedimo, išvedimo, darbo su bendra atminties sritimi (prieinama visoms vartotojo programoms; komandos leidžia į ją rašyti ir skaityti; sritis apsaugoma semaforais) ir programos pabaigos komandos. Registrai yra tokie: komandų skaitiklis, bent du bendrosios paskirties registrai, požymių registras (požymius formuoja aritmetinės, o į juos reaguoja sąlyginio valdymo perdavimo komandos). Atminties dydis yra 16 blokų po 16 žodžių (žodžio ilgį pasirinkite patys).
Realios mašinos procesorius gali dirbti dviem režimais: vartotojo ir supervizoriaus. Virtualios mašinos atmintis atvaizduojama į vartotojo atmintį naudojant puslapių transliaciją. Yra taimeris, kas tam tikrą laiko intervalą generuojantis pertraukimus. Įvedimui naudojamas virtualių „flash atmintinių“ nuskaitymo įrenginys, išvedimui - spausdintuvas. Yra išorinės atminties įrenginys - kietasis diskas.
Vartotojas užduočių paketą pateikia „prijungęs“ atmintinę. Sistema perkelia visas joje esančias užduotis į diską, patikrindama jų sintaksę, ir, tuo pat metu, jei tik yra reikiamų resursų, pradeda jas vykdyti.