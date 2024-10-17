Feature: US1004 kullanici JPH endpoitine Post Request gönderir

@api4
  Scenario: TC04 Kullanici Post request sonucu dönen response i test eder


    Given Kullanici "jPHBaseUrl" base URL'ini kullanir
    And Path parametreleri icin "posts" kullanir
    And POST request icin "Mehmet","Merhaba",10,77 bilgileri ile request body olusturur
    And  Test icin  "Mehmet", "Merhaba" 10 ve 77 degerleri ile expected response body olusturur
    And jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder
    And jPH responseBody'sindeki attributeleri test etmek icin response i JsonPath objesine cast eder
    And expected response body ile actual response'daki attribute degerlerinin ayni oldugunu test eder
    And jPH respons daki "Connection" header degerinin "keep-alive"
    And jPH response'da status degerinin 201
