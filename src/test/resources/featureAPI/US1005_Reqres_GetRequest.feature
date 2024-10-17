# https://reqres.in/api/users/2 URL'sine bir GET isteği gönderin.
# Gelen yanıtın durum kodunun 200 olduğunu doğrulayın.
# Yanıtın JSON body'sinde yer alan data.id değerinin 2 olduğunu doğrulayın.
# Yanıt süresinin 2 saniyeden kısa olduğunu kontrol edin.
@api5
  Feature: US1005 Kullanici Reqres endpointine GET request gönderir
    Scenario: TC05 Kullanici donen Response in expected oldugunu test eder

    Given Kullanici "regresUrl" base URL ini kullanir
    Then Path parametrelerine "users/2" kullanir
    And Kullanici endpointe bir GET request gondererek Response degeri kaydeder
    Then Donen Response in status degerinin 200
    And   Donen Response in JSON bodysinde yer alan id degerin 2 oldugunu
    And Response in yanit suresinin 2 sn den kisa oldugunu test eder




