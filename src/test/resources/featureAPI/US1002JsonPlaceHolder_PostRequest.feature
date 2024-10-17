Feature: US1002 kullanici JPH endpoitine Post Request gönderir

  @api2
  Scenario: TC01 Kullanici Post request sonucu dönen response i test eder


    Given Kullanici "jPHBaseUrl" base URL'ini kullanir
    Then Path parametreleri icin "posts" kullanir
    And POST request icin "Ahmet","Merhaba",10,70 bilgileri ile request body olusturur
    And jPH server a POST request gonderir ve testleri yapmak icin response degerini kaydeder
    Then jPH response'da status degerinin 201
    And jPH response'da content type degerinin "application/json; charset=utf-8"
    And jPH respons daki "Connection" header degerinin "keep-alive"
    And jPH responseBody'sindeki attributeleri test etmek icin response i JsonPath objesine cast eder
    Then response attribute degerlerinin "Ahmet","Merhaba",10 ve 101 oldugunu test eder