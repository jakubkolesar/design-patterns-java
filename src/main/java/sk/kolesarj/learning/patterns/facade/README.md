# Facade
Predstavme si, že máme dom. Dom má veľkú elektrickú sieť, ako aj odpadovú sieť atď.
Nechceme tieto komplexné záležitosti "vešať na nos" kupcovi a preto použijeme
Facade design pattern.

Alebo keď chceme použiť konverter videa, nechceme všetky enkódovania, kompresie atď 
robiť manuálne.. Chceli by sme metódu `convertVideo(String url, Formal format)`, 
ktorá všekto spraví za nás...

![Facade diagram](https://refactoring.guru/images/patterns/diagrams/facade/example.png)