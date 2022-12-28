Pre niektoré komponenty dáva zmysel, aby ich inštancia bola v kóde
iba raz (database repository). 
* každému posúvame rovnakú inštanciu objektu
* chceme zakázať vytváranie ďalších kópií/inštancií
* komponent ktorý je inštanciovaný len raz

![singleton diagram](https://refactoring.guru/images/patterns/diagrams/singleton/structure-en.png)