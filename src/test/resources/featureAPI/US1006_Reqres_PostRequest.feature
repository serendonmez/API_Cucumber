# https://reqres.in/api/users URL'sine
#   header değeri "Content-Type", "application/json" olan bir POST isteği gönderin.
#   İstek için JSON formatında şu bilgileri içeren bir body kullanın: { "name": "morpheus", "job": "leader" }
#   Gelen yanıtın durum kodunun 201 olduğunu doğrulayın.
#   Yanıtın JSON body'sinde name alanının morpheus olduğunu kontrol edin.

@wip1
  Feature: US1006 Kullanici verilen endpointe POST request yapabilmeli
    Scenario: TC06 Kullanici Post request sonucu donen response expected ile ayni oldugunu test eder


      Given Kullanici "regresUrl" base URL ini kullanir
      Then Path parametrelerine "users" kullanir
      When Kullanici request Body yi "name" icin "morpheus", "job" icin "leader" olusturur
      And Kullanici endpointe header değeri "Content-Type", "application/json" olan bir POST isteği gondererek Response degeri kaydeder
      Then Donen Response in status degerinin 201
      And ResponseBody deki "name" attribute unun "morpheus" oldugunu test eder


