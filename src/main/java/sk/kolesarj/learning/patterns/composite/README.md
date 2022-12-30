# Composite
Predstavme si, že máme dva typy objektov: `produkty` a `krabice`. Krabica môže 
obsahovať niekoľko produktov ale aj menšie krabice. Tieto menšie krabice takztiež
môžu obsahovať menšie produkty a krabice atď...
Povedzme, že chceme vytvoriť `objednávkový systém`. Objednávky by mohli obsahovať
produkty bez balenia ale aj "krabice v krabiciach".. Ako by sme určili cenu
takejto objednávky?

Môžme odbaliť každú krabicu a vypočítať cenu. To by bolo jednoduché v reálnom svete
ale nie v programe. Preto použijeme **Composite pattern**.

![composite diagram](https://refactoring.guru/images/patterns/diagrams/composite/structure-en.png)

![composite-diagram.png](..%2F..%2F..%2F..%2F..%2F..%2Fresources%2Fcomposite-diagram.png)