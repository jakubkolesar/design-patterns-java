# Decorator
Namiesto toho aby sme upravovali hlavný objekt (a porušovali OCP), tak vytvoríme 
Decorator, pomocou ktorého objekt upravíme.

Napríklad máme triedu `square` ktorá implementuje interface `shape`, ktorej 
chceme pridať farbu. Vytvoríme decorator triedu `coloredShape` v ktorej to 
zrealizujeme.